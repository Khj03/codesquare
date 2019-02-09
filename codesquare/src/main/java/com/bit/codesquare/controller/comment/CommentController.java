package com.bit.codesquare.controller.comment;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bit.codesquare.controller.board.BoardRestController;
import com.bit.codesquare.dto.comment.Comment;
import com.bit.codesquare.dto.comment.LectureReview;
import com.bit.codesquare.mapper.comment.CommentMapper;
import com.bit.codesquare.util.CodesquareUtil;

@Controller
@RequestMapping(value= {"learn/Comment","Board/Comment"})
public class CommentController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardRestController.class);
	
	LectureReview lReview;
	Comment comment;
	List<LectureReview> lrList;
	@Autowired
	CommentMapper cMapper;
	
	@Value("${user.thumbnail.directory}")
	String userThumbPath;
	
	CodesquareUtil cUtil=new CodesquareUtil();
	
	@GetMapping("review/list")
	@ResponseBody
	public List<LectureReview> reviewList(@RequestParam int boardId)throws Exception{
		logger.info("불러오기:"+boardId);
		lrList=cMapper.getLectureReview(boardId);
		for(LectureReview lr:lrList) {
			String path= userThumbPath;
			path+=cUtil.getPath(lr.getUserId(),lr.getThumbnail());
			lr.setThumbnail(path);
		}
		logger.info("불러오기도 완료.");
		return lrList;
	}
	
	@RequestMapping(value="insert" , method=RequestMethod.POST)
	@ResponseBody
	private int insertReview(@ModelAttribute LectureReview lReview)throws Exception {

		int result=cMapper.insertReview(lReview);
		cMapper.updateLikePlus(lReview);
		
		logger.info("result:"+result);	
		return result;
	}
	
	@RequestMapping(value="delete" , method=RequestMethod.GET)
	@ResponseBody
	private int deleteReview(@RequestParam int id)throws Exception {
		logger.info("id"+id);
		int result=cMapper.deleteReview(id);	
		logger.info("result:"+result);	
		return result;
	}
	
	@RequestMapping(value="update" , method=RequestMethod.POST)
	@ResponseBody
	private int updateReview(@RequestParam int id,@RequestParam String content)throws Exception {
		lReview =new LectureReview();
		lReview.setId(id);
		lReview.setContent(content);
		int result=cMapper.updateReview(lReview);
		logger.info("result:"+result);
		return result;
	}
	
	@RequestMapping(value="qna/list" , method=RequestMethod.GET)
	@ResponseBody
	private List<Comment> qnaCommentList(@RequestParam int boardId)throws Exception {
		List<Comment> cList=cMapper.getQNACommentList(boardId);
		for(Comment c:cList) {
			String path= userThumbPath;
			path+=cUtil.getPath(c.getUserId(),c.getThumbnail());
			logger.info("thumbnail:"+path);
			c.setThumbnail(path);
		}
		return cList;
	}
	
	@RequestMapping(value="qna/insert" , method=RequestMethod.POST)
	@ResponseBody
	private int insertQNAComment(@ModelAttribute Comment comment)throws Exception {
		int result = cMapper.insertQNAComment(comment);
		logger.info("입력결과:"+result);
		return result;
	}
	/**
	 * 
	 * @param id : 댓글 번호
	 * @return: int형으로 대댓글이 없는 댓글일경우 삭제후 1 반환, 대댓글이 있는 댓글일 경우 deleteStatus가 1로 바뀌며 내용만 삭제된 댓글이라고 변경. 
	 * @throws Exception
	 */
	@RequestMapping(value="qna/delete" , method=RequestMethod.GET)
	@ResponseBody
	private int deleteQNAComment(@RequestParam int id)throws Exception {
		//해당 코멘트의 대댓글이 있는지 검사
		int searchCCResult=cMapper.searchChildComment(id);
		//해당 코멘트이 대댓글인지+ 해당 부모댓글의 대댓글의 갯수 검사
		int searchPCResult=cMapper.searchParentComment(id);
		int result=0;
		
		if(searchPCResult!=0) {
			cMapper.deleteQNAComment(searchPCResult);
		}		
		if(searchCCResult==0) {
			result = cMapper.deleteQNAComment(id);
			logger.info("삭제된결과는"+result+"입니다");
			return result;
		}else {
			comment = new Comment();
			comment.setId(id);
			comment.setContent("삭제된 댓글입니다.");
			comment.setDeleteStatus(1);
			result=cMapper.updateQNAComment(comment);
			return 2;
		}
	}
	
	@RequestMapping(value="qna/update" , method=RequestMethod.POST)
	@ResponseBody
	private int updateQNAComment(@RequestParam int id,@RequestParam String content)throws Exception {
		comment = new Comment();
		comment.setId(id);
		comment.setContent(content);
		int result=cMapper.updateQNAComment(comment);
		logger.info("입력결과:"+result);
		return result;
	}
	
}


















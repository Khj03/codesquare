package com.bit.codesquare.controller.notice;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bit.codesquare.dto.notice.Notice;
import com.bit.codesquare.dto.notice.Producer;
import com.bit.codesquare.mapper.notice.NoticeMapper;

import lombok.extern.java.Log;

@Controller
@Log
public class WebSocketController {

	@Autowired
	Producer producer;

	@Autowired
	NoticeMapper noticeMapper; 

	Notice notice;
	
	Map<String, Object> map ;
	
	@RequestMapping("/send/{topic}")
	public String sender(@PathVariable String topic, @RequestParam(value="qq") String qq, @RequestParam(value="aline") String aline) throws Exception{
		producer.sendMessageTo(topic, qq);
		return "index";
	}
 
	@RequestMapping("/send/message")
	@ResponseBody
	public List<Notice> gettoSelect(@RequestParam(value="qq") String qq, @RequestParam(value="aline") String aline) throws Exception{

		//log.info(qq);
		//log.info(aline);
		noticeMapper.insert(qq, aline); 
		return null;
	}
	
	/*@RequestMapping(value = "/notice", method = RequestMethod.GET)
	public String nameView(Model model) throws Exception {
		model.addAttribute("noticeList", noticeMapper.nameView());
		System.out.println("출력");
		return "notice/notice";
	}*/
	
	
	//ajax 알림리스트 DB뿌리기
	@RequestMapping(value="noticeview" , method=RequestMethod.GET)
	@ResponseBody
	private List<Notice> getNoteContent(@RequestParam(value="messages") String messages)throws Exception {
		log.info("들어옴");
		map= new HashMap<String, Object>();
		map.put("messages: ", messages);
		//log.info(qq);
		//map.put("aline", aline);
		//String noteContent2=noticeMapper.getLectureHandWriting2(map);
		List<Notice> noteContent2=noticeMapper.getLectureHandWriting2();
		log.info("noteconntent2: "+noteContent2);
		
		return noteContent2;
	}
	
	
	
	
}


package com.bit.codesquare.controller.notice;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
public class Noticeontroller{
   // @Autowired
    //NoticeMapper noticeMapper; 
    
	@MessageMapping("/notice")
		public String handle(String notice) {
			return "[ " + notice + " ]";
		}
	
	
	/*@RequestMapping(value = "/notice", method = RequestMethod.GET)
    public String nameView(Model model) throws Exception{
    	model.addAttribute("board", noticeMapper.nameView());
        System.out.println("출력");
        return "/notice/notice";
    }*/
	
	
	/*@RequestMapping(value = "/notice", method = RequestMethod.GET)
    public String nameView2() throws Exception{
        return "/notice/notice";
    }*/
	
	
}

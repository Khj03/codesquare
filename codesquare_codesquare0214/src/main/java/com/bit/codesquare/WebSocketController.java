package com.bit.codesquare;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebSocketController {

	@Autowired
	Producer producer;
	
	@RequestMapping("/send/{topic}")
	public String sender(@PathVariable String topic, @RequestParam(value="qq") String message, @RequestParam(value="aline") String aline) {
		producer.sendMessageTo(topic, message);
		//db noticeMapper.insert(qq, aline);
		System.err.println("aline  :"+aline);
		return "index";
	}

}


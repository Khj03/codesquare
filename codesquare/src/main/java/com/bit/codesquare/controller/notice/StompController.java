package com.bit.codesquare.controller.notice;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.bit.codesquare.dto.notice.Greeting;
import com.bit.codesquare.dto.notice.HelloMessage;
import com.bit.codesquare.dto.notice.User;
import com.bit.codesquare.dto.notice.UserResponse;

@Controller
public class StompController {
	 
	@MessageMapping("/hello")
	@SendTo("/topic/greetings")
	public Greeting greeting(HelloMessage message) throws Exception {
		  
	        return new Greeting (message.getName() + " : " , message.getContent());
	}
	
	  @MessageMapping("/user")
	    @SendTo("/topic/user")
	    public UserResponse getUser(User user) {

	        return new UserResponse("Hi " + user.getName());
	    }
	
	
}
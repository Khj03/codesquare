package com.bit.codesquare.dto.notice;

import lombok.Data;

//클라이언트에서 서버로 전송할 때 리턴타입
@Data
public class HelloMessage {

     String name;
     String content;
    
    public HelloMessage() {
    	
    }
    
    
	public HelloMessage(String name, String content) {
		this.name = name;
		this.content = content;
	}
 
    
}
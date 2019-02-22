package com.bit.codesquare.dto.notice;

import lombok.Data;

//서버에서 클라이언트에게 전송하는 이름과 대화내용의 클래스
@Data
public class Greeting {
	 
	  String name;
      String content; 
	
      public Greeting() {
    	  
      }

	public Greeting(String name, String content) {
		this.name = name;
		this.content = content;
	}
    
}

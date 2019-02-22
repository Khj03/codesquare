package com.bit.codesquare.controller.notice;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;


//웹소켓방식
@Configuration
@EnableWebSocket
public class CodesquareWebSocketConfig implements WebSocketConfigurer {
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(new ReplyEchoHandler(), "/notice").setAllowedOrigins("*").withSockJS();
		// /notice path가 들어오면 ReplyEchoHandler()가 처리함
	}
}

/*스프링부트에서는 WebSocketConfigurer를 받아야한다*/
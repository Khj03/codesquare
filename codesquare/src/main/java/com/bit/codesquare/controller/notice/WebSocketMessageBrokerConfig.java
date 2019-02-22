package com.bit.codesquare.controller.notice;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketMessageBrokerConfig implements WebSocketMessageBrokerConfigurer {

	//Endpoint
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/hello").setAllowedOrigins("*").withSockJS();
    }
	
	//MessageBroker
	@Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
		config.enableSimpleBroker("/topic");	//topic방식
		config.setApplicationDestinationPrefixes("/app");
    }

}
package com.wing.mainApp.controller;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;


public class ChatHandler extends TextWebSocketHandler {
	
	public ChatHandler()
	{
		super();
	
		
	}
	
	 
@Override
public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
	// TODO Auto-generated method stub
   
   
	super.afterConnectionClosed(session, status);
}

@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		// TODO Auto-generated method stub
	
      
	//	super.handleTextMessage(session, message);
	}

@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// TODO Auto-generated method stub
	
	
		super.afterConnectionEstablished(session);
	}

}

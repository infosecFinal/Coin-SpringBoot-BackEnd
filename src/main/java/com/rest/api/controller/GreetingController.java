package com.rest.api.controller;

import com.rest.api.VO.GreetingVO;
import com.rest.api.VO.HelloMessageVO;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Controller
public class GreetingController {
	private static final SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");
	@MessageMapping("/hello")
	@SendTo("/topic/greetings")
	public GreetingVO greeting(HelloMessageVO message){
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return new GreetingVO(message.getName()+": " + HtmlUtils.htmlEscape(message.getMsg()), sdf.format(timestamp));
	}
}
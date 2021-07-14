package com.rest.api.controller;

import com.rest.api.VO.Greeting;
import com.rest.api.VO.HelloMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GreetingController {

	@MessageMapping("/hello")
	@SendTo("/topic/greetings")
	public Greeting greeting(HelloMessage message){
		return new Greeting(HtmlUtils.htmlEscape(message.getName())+": " + HtmlUtils.htmlEscape(message.getMsg()));
	}

}

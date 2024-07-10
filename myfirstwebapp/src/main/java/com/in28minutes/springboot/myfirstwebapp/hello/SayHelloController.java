package com.in28minutes.springboot.myfirstwebapp.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class SayHelloController {
	
	
	@RequestMapping("say-hello")
	//@ResponseBody
	public String sayHello() {
		return "Hellllloooooooooooo hiii i am the idk";
	}
	
	@RequestMapping("say-hello-html")
	//@ResponseBody
	public String sayHelloHtml() {
		StringBuffer sb = new StringBuffer();
		sb.append("<html>");
		sb.append("<head>");
		sb.append("<title>HTML SpringBoot</title>");
		sb.append("</head>");
		sb.append("<body>");
		sb.append("Hi . This is an HTML hard coded inside powerful spring boot.!");
		sb.append("</body>");
		sb.append("</html>");
		
	return sb.toString(); 
	}
	
	@RequestMapping("say-hello-jsp")
	public String sayHelloJsp() {
		return "sayHello";
	}
	
	@RequestMapping("say-jsp-boot")
	public String jspboot() {
		return "jspboot";
	}
}

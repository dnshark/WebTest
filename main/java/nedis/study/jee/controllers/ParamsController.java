package nedis.study.jee.controllers;

import nedis.study.jee.entities.Account;
import nedis.study.jee.forms.LoginForm;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author nedis
 * @version 1.0
 */
@Controller
public class ParamsController {

	@RequestMapping(value="/pets/{petId}")
	public void function1(@PathVariable String petId){}
	
	@RequestMapping(value="/test1") // /test1?petId=qwerty
	public void function2(@RequestParam("petId") String petId){}
	
	@RequestMapping(value="/test2") 
	public void function3(@RequestBody Account account){}
	
	@RequestMapping(value="/test3") 
	public void function4(@ModelAttribute("loginForm") LoginForm form){}
	
	@RequestMapping("/test4")
	public void function5(@CookieValue("JSESSIONID") String cookie) {}
	
	@RequestMapping("/displayHeaderInfo")
	public void displayHeaderInfo(@RequestHeader("Accept-Encoding") String encoding, 
								  @RequestHeader("Keep-Alive") long keepAlive) {}
	
	@RequestMapping(value="/test6") 
	public String test6(){
		return "test6";
	}
	
	@RequestMapping(value="/test7") 
	public @ResponseBody String test7(){
		return "Hello world";
	}
	
	@RequestMapping(value="/test8") 
	public String test8(){
		return "redirect:/login";
	}
}

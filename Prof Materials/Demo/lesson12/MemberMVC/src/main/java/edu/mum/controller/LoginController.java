package edu.mum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import edu.mum.domain.Member;
import edu.mum.domain.UserCredentials;
import edu.mum.service.MemberService;
import edu.mum.service.UserCredentialsService;
import edu.mum.service.impl.UserCredentialsServiceImpl;

@Controller
@SessionAttributes("member")
public class LoginController {

	@Autowired
	UserCredentialsService credentialsService;

	@Autowired
	MemberService memberService;

	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String login() {
 		return "login";
	}
 
	
	@RequestMapping(value="/postLogin", method = RequestMethod.POST)
	public String PostLogin(UserCredentials credentials, Model model) {

		// KLUDGE to set Credentials...for REST Security Needed to do Login
		UserCredentials userCredentials = 
				((UserCredentialsServiceImpl)
				credentialsService).getUserCredentials();
		if (userCredentials == null) userCredentials = new UserCredentials();
		
		userCredentials.setPassword("admin");
		userCredentials.setUsername("admin");
		((UserCredentialsServiceImpl)
				credentialsService).setUserCredentials(userCredentials);
		
		UserCredentials validCredentials = credentialsService.findOne(credentials.getUsername());
 
		if (validCredentials == null)
			return  "login";
 
		///DEMO JSON bidirectional issue solution
		Member member = validCredentials.getMember();
 		member = memberService.findOne(member.getId());
 		
		model.addAttribute("member", validCredentials.getMember());
 		return "redirect:/welcome";
	}
 
	@RequestMapping(value="/loginfailed", method = RequestMethod.GET)
	public String loginerror(Model model) {
 
		model.addAttribute("error", "true");
		return "login";
 
	}
 
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logout(Model model, SessionStatus status) {
		status.setComplete();
 		return "redirect:/welcome";
 	}
}

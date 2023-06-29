package com.ddb.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ddb.entity.User;
import com.ddb.service.LoginService;

/**
*@author 段道博
*@date 2021年1月18日下午4:34:32
*
*/

@Controller
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@RequestMapping("/login")
	public String login(String name, String passwd, 
			Model model, HttpSession session) {
		
		User user = new User();
		user.setName(name);
		user.setPasswd(passwd);
		
		User user_login = loginService.loginUser(user);
		
		if (user_login != null) {
			//将用户对象添加到Session
			session.setAttribute("USER_SESSION", user_login);
			return "redirect:study/index";
		}else {
			model.addAttribute("msg", "账号或密码错误，请重新输入！");
			return "login";
		}
	}
	
	@RequestMapping("/join")
	@ResponseBody
	public String sign(User user){
		int res = loginService.insertUser(user);
		
		if (res == 1) {
			return "OK";
		}else if (res == -2) {
			return "nullError";
		}else if (res == -3) {
			return "nameFormatError";
		}else if (res == 0) {
			return "nameError";
		}else {
			return "passwdError";
		}
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		//清除session
		session.invalidate();
		
		//重定向到登录页面的跳转方法
		return "redirect:login";
	}

}

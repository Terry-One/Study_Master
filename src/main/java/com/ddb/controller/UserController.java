package com.ddb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ddb.entity.User;
import com.ddb.service.LoginService;

/**
*@author 段道博
*@date 2021年1月24日下午8:53:24
*
*/

@Controller
@RequestMapping("/study")
public class UserController {
	
	@Autowired
	private LoginService loginService;
	
	@RequestMapping("/user")
	public String info(String name,Model model){
		
		User user = loginService.getUser(name);
		
		model.addAttribute("user", user);
		
		return "user";
	}
	
	@RequestMapping("/getuser")
	@ResponseBody
	public User get_user(String name){
		
		User user = loginService.getUser(name);
		
		return user;
	}
	
	@RequestMapping("/user.data")
	@ResponseBody
	public String update_info(User user){
		
		int res = loginService.updateUser(user);
		
		if (res == 1) {
			return "OK";
		}else {
			return "ERROR";
		}
		
	}

}

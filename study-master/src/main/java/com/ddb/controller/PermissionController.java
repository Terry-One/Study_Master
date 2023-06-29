package com.ddb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ddb.entity.User;
import com.ddb.service.LoginService;

/**
*@author �ε���
*@date 2021��1��24������9:02:15
*
*/

@Controller
@RequestMapping("/study")
public class PermissionController {
	
	@Autowired
	private LoginService loginService;
	
	@RequestMapping("/manager")
	public String manager_user(Model model) {
		List<User> allUser = loginService.getAllUser();
		
		model.addAttribute("users", allUser);
		
		return "manager";
	}
	
	@RequestMapping("/users.data")
	@ResponseBody
	public String update_info(User user){
		
		int res = loginService.updatePermission(user);
		
		if (res == 1) {
			return "OK";
		}else {
			return "ERROR";
		}
		
	}

}

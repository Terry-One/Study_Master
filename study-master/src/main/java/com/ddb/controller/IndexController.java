package com.ddb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
*@author �ε���
*@date 2021��1��19������11:00:29
*
*/

@Controller
@RequestMapping("/study")
public class IndexController {
	
	@RequestMapping("/index")
	public String index() {
		
		return "index";
	}

}

package com.ddb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
*@author 段道博
*@date 2021年1月19日上午11:00:29
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

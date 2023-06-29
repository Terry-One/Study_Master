package com.ddb.service;

import java.util.List;

import com.ddb.entity.User;

/**
*@author 段道博
*@date 2021年1月18日下午3:06:17
*
*/

public interface LoginService {
	
	//	登录用户
	User loginUser(User user);
	
	//	注册用户
	int insertUser(User user);
	
	//	注册用户时，验证用户名是否已存在
	//	List<User> checkName(String name);
	
	int updateUser(User user);
	
	User getUser(String name);
	
	List<User> getAllUser();
	
	int updatePermission(User user);
	
}

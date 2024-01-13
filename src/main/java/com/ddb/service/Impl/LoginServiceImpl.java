package com.ddb.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddb.entity.User;
import com.ddb.mapper.LoginMapper;
import com.ddb.service.LoginService;
import com.ddb.util.MD5Util;

/**
*@author 段道博
*@date 2021年1月18日下午3:09:33
*
*/

@Service
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private LoginMapper loginMapper;

	public User loginUser(User user) {
		String login_pwd = MD5Util.getMD5Value(user.getPasswd());
		
		user.setPasswd(login_pwd);
		
		return loginMapper.loginUser(user);
	}

	public int insertUser(User user) {
		
		//	若用户名和密码为空，则返回-2
		if (user.getName().length() == 0 || user.getPasswd().length() == 0) {
			return -2;
		}
		
		
		int user_len = user.getPasswd().length();
		
		//	将密码进行加密
		String login_pwd = MD5Util.getMD5Value(user.getPasswd());
		
		user.setPasswd(login_pwd);

		//	验证数据库中是否已经存在该用户名
		List<User> check_name = loginMapper.checkName(user.getName());
		
		//	若用户已存在，则返回0
		if (check_name.size() > 0) {
			return 0;
		}
		
		//	若用户姓名字符数超过8或者小于5，则返回-3
		if (user.getName().length() > 8 || user.getName().length() < 5) {
			return -3;
		}
		
		//	若密码长度小于8，则返回-1
		if (user_len != 8) {			
			return -1;
		}
		
		
		return loginMapper.insertUser(user);
	}

	public int updateUser(User user) {
		int res = loginMapper.updateUser(user);
		
		
		return res;
	}

	public User getUser(String name) {
		
		return loginMapper.getUser(name);
	}

	public List<User> getAllUser() {
		List<User> allUser = loginMapper.getAllUser();
		
		return allUser;
	}

	public int updatePermission(User user) {
		int res = loginMapper.updatePermission(user);
		
		return res;
	}
}

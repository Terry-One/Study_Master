package com.ddb.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddb.entity.User;
import com.ddb.mapper.LoginMapper;
import com.ddb.service.LoginService;
import com.ddb.util.MD5Util;

/**
*@author �ε���
*@date 2021��1��18������3:09:33
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
		
		//	���û���������Ϊ�գ��򷵻�-2
		if (user.getName().length() == 0 || user.getPasswd().length() == 0) {
			return -2;
		}
		
		
		int user_len = user.getPasswd().length();
		
		//	��������м���
		String login_pwd = MD5Util.getMD5Value(user.getPasswd());
		
		user.setPasswd(login_pwd);

		//	��֤���ݿ����Ƿ��Ѿ����ڸ��û���
		List<User> check_name = loginMapper.checkName(user.getName());
		
		//	���û��Ѵ��ڣ��򷵻�0
		if (check_name.size() > 0) {
			return 0;
		}
		
		//	���û������ַ�������8����С��5���򷵻�-3
		if (user.getName().length() > 8 || user.getName().length() < 5) {
			return -3;
		}
		
		//	�����볤��С��8���򷵻�-1
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

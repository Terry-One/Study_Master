package com.ddb.service;

import java.util.List;

import com.ddb.entity.User;

/**
*@author �ε���
*@date 2021��1��18������3:06:17
*
*/

public interface LoginService {
	
	//	��¼�û�
	User loginUser(User user);
	
	//	ע���û�
	int insertUser(User user);
	
	//	ע���û�ʱ����֤�û����Ƿ��Ѵ���
	//	List<User> checkName(String name);
	
	int updateUser(User user);
	
	User getUser(String name);
	
	List<User> getAllUser();
	
	int updatePermission(User user);
	
}

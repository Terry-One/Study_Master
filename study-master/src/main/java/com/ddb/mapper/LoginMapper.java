package com.ddb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ddb.entity.User;

/**
*@author �ε���
*@date 2021��1��18������2:31:01
*
*/

@Mapper
public interface LoginMapper {
	
	//	��¼�û�
	User loginUser(User user);
	
	//	ע���û�
	int insertUser(User user);
	
	//	ע���û�ʱ����֤�û����Ƿ��Ѵ���
	List<User> checkName(String name);
	
	//	�����û���Ϣ����
	int updateUser(User user);
	
	//	�û���Ϣ��ѯ
	User getUser(String name);
	
	//	��ȡ�����û���Ϣ
	List<User> getAllUser();
	
	//	�޸��û�Ȩ��
	int updatePermission(User user);
}

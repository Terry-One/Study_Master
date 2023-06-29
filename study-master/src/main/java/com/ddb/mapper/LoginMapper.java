package com.ddb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ddb.entity.User;

/**
*@author 段道博
*@date 2021年1月18日下午2:31:01
*
*/

@Mapper
public interface LoginMapper {
	
	//	登录用户
	User loginUser(User user);
	
	//	注册用户
	int insertUser(User user);
	
	//	注册用户时，验证用户名是否已存在
	List<User> checkName(String name);
	
	//	更新用户信息资料
	int updateUser(User user);
	
	//	用户信息查询
	User getUser(String name);
	
	//	获取所有用户信息
	List<User> getAllUser();
	
	//	修改用户权限
	int updatePermission(User user);
}

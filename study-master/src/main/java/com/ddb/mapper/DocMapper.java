package com.ddb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ddb.entity.Doc;

/**
*@author 段道博
*@date 2021年1月20日下午5:57:59
*
*/

@Mapper
public interface DocMapper {
	
	//所有文件
	List<Doc> getAllDoc(String name);
	
	//上传文件的文件信息
	int insertDoc(Doc doc);

}

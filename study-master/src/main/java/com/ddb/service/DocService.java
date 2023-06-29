package com.ddb.service;

import java.util.List;

import com.ddb.entity.Doc;

/**
*@author 段道博
*@date 2021年1月20日下午6:02:20
*
*/
public interface DocService {
	
	//所有文件
	List<Doc> getAllDoc(String name);
	
	//上传文件的文件信息
	int insertDoc(Doc doc);

}

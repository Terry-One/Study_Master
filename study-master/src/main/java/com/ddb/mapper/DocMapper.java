package com.ddb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ddb.entity.Doc;

/**
*@author �ε���
*@date 2021��1��20������5:57:59
*
*/

@Mapper
public interface DocMapper {
	
	//�����ļ�
	List<Doc> getAllDoc(String name);
	
	//�ϴ��ļ����ļ���Ϣ
	int insertDoc(Doc doc);

}

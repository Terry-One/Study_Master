package com.ddb.service;

import java.util.List;

import com.ddb.entity.Doc;

/**
*@author �ε���
*@date 2021��1��20������6:02:20
*
*/
public interface DocService {
	
	//�����ļ�
	List<Doc> getAllDoc(String name);
	
	//�ϴ��ļ����ļ���Ϣ
	int insertDoc(Doc doc);

}

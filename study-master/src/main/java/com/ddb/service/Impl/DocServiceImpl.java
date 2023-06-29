package com.ddb.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddb.entity.Doc;
import com.ddb.mapper.DocMapper;
import com.ddb.service.DocService;

/**
*@author 段道博
*@date 2021年1月20日下午6:03:16
*
*/

@Service
public class DocServiceImpl implements DocService {
	
	@Autowired
	private DocMapper docMapper;

	@Override
	public List<Doc> getAllDoc(String name) {

		return docMapper.getAllDoc(name);
	}

	@Override
	public int insertDoc(Doc doc) {

		return docMapper.insertDoc(doc);
	}

}

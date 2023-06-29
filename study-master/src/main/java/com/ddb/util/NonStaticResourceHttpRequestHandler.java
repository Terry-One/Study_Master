package com.ddb.util;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

/**
*@author �ε���
*@date 2021��1��19������3:20:14
*
*/

@Component
public class NonStaticResourceHttpRequestHandler extends ResourceHttpRequestHandler {
	
	public final static String ATTR_FILE = "NON-STATIC-FILE";
	
	@Override
	protected Resource getResource(HttpServletRequest request) {
		final File file_path = (File) request.getAttribute(ATTR_FILE);
		
		return new FileSystemResource(file_path);
	}

}

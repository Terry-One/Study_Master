package com.ddb.util;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

/**
*@author 段道博
*@date 2021年1月19日下午3:20:14
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

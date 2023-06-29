package com.ddb.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ddb.entity.Software;
import com.ddb.service.SoftwareService;

/**
 * @author �ε���
 * @date 2021��2��2������3:34:17
 *
 */

@Controller
@RequestMapping("/study")
public class SoftwareController {
	
	@Autowired
	private SoftwareService softwareService;
	
	@RequestMapping("/software/software-info")
	public String software() {
		return "software";
	}
	
	@RequestMapping("/software/software.data")
	@ResponseBody
	public List<Software> getData(@RequestParam(value = "page", required = false) Integer page, 
			@RequestParam(value = "limit", required = false) Integer limit,
				@RequestParam(value = "name", required = false) String name) {
		// ���崫���ҳֵ
		int pages = 0;
		System.out.println(page + limit + name);
		if (page.equals("")) {
			pages = 0;
		}else {
			pages = (page - 1) * limit;
		}
		if (name == null || name.length() == 0) {
			name = "%%";
		}else {
			name = "%" + name + "%";
		}
		List<Software> allSoftware = softwareService.getAllSoftware(pages, limit, name);
		
		return allSoftware;
	}
	
	@RequestMapping("/software/upload")
	@ResponseBody
	public int upload(MultipartFile files, String textarea, HttpSession session){
		Software software = new Software();
		
		if (files.isEmpty() != true && textarea.trim() != null && textarea.trim().length() != 0) {
			// ��ȡ�ļ���
			String file_name = files.getOriginalFilename();
			String realPath = session.getServletContext().getRealPath("upload/video");
			File file = new File(realPath);
			if (!file.getParentFile().exists()) {
				System.out.println("�ļ��в����ڣ�");
				file.getParentFile().mkdir();
			}
			software.setSoftware_name(file_name);
			software.setDir_path(realPath);
			software.setSoftware_remark(textarea);
			File f = new File(realPath, file_name);
			try {
				//�����ļ�
				files.transferTo(f);
				softwareService.insertSoftware(software);
				System.out.println("�ϴ��ɹ���");
				return 1;
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("�ϴ�ʧ�ܣ�");
				return 0;
			}
		}else {
			System.out.println("�ϴ��ļ�Ϊ�գ�");
			return -1;
		}
	}
	
	@RequestMapping(value = "/software/download", method = RequestMethod.GET)
	@ResponseBody
	public String download(HttpServletResponse response, Integer software_id) throws UnsupportedEncodingException{
		
		Software software = softwareService.getSoftware(software_id);
		
		String file = software.getDir_path() + "\\" + software.getSoftware_name();
		
		System.out.println(file);
		
		File f = new File(file);
		System.out.println(f.getName());
		
		if (f.exists()) {
			// �����ļ�����
            response.setHeader("content-type", "application/octet-stream");
            response.setContentType("application/octet-stream");
            // �����ļ���������ʾ����
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(f.getName(), "UTF-8"));
            // ʵ���ļ�����
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(f);
                System.out.println(fis);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                System.out.println(i);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    os.flush();
                    i = bis.read(buffer);
                }
                return "OK";
            } catch (Exception e) {
                System.out.println("Download the song failed!");
                return "ERROR";
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
		}
		return "";
		
	}
	
	@RequestMapping("software/deleteById")
	@ResponseBody
	public String deleteVideo(Integer id) {
		Software software = softwareService.getSoftware(id);
		
		software.setStatus(0);
		int res = softwareService.updateSoftware(software);
		
		if (res == 1) {
			return "OK";
		}else {
			return "ERROR";
		}
		
	}
	
	
}

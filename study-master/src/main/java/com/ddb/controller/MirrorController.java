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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ddb.entity.Mirror;
import com.ddb.service.MirrorService;

/**
 * @author 段道博
 * @date 2021年1月19日下午3:34:17
 *
 */

@Controller
@RequestMapping("/study")
public class MirrorController {
	
	@Autowired
	private MirrorService mirrorService;
	
	@RequestMapping("/mirror/mirror-info")
	public String mirror(String name, Model model) {
		if (name == null) {
			name = "%%";
		}else {
			name = "%" + name + "%";
		}
		
		List<Mirror> allMirror = mirrorService.getAllMirror(name);
		
		model.addAttribute("all_mirror", allMirror);
		return "mirror";
	}
	
	@RequestMapping("/mirror/upload")
	@ResponseBody
	public int upload(MultipartFile files, String textarea, HttpSession session){
		Mirror mirror = new Mirror();
		
		if (files.isEmpty() != true && textarea.trim() != null && textarea.trim().length() != 0) {
			// 获取文件名
			String file_name = files.getOriginalFilename();
			String realPath = session.getServletContext().getRealPath("upload/video");
			File file = new File(realPath);
			if (!file.getParentFile().exists()) {
				System.out.println("文件夹不存在！");
				file.getParentFile().mkdir();
			}
			mirror.setMirror_name(file_name);
			mirror.setDir_path(realPath);
			mirror.setMirror_remark(textarea);
			File f = new File(realPath, file_name);
			try {
				//保存文件
				files.transferTo(f);
				mirrorService.insertMirror(mirror);
				System.out.println("上传成功！");
				return 1;
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("上传失败！");
				return 0;
			}
		}else {
			System.out.println("上传文件为空！");
			return -1;
		}
	}
	
	@RequestMapping(value = "/mirror/download", method = RequestMethod.GET)
	@ResponseBody
	public String download(HttpServletResponse response, Integer mirror_id) throws UnsupportedEncodingException{
		
		Mirror mirror = mirrorService.getMirror(mirror_id);
		String file = mirror.getDir_path() + "\\" + mirror.getMirror_name();
		
		System.out.println(file);
		
		File f = new File(file);
		System.out.println(f.getName());
		
		if (f.exists()) {
			// 配置文件下载
            response.setHeader("content-type", "application/octet-stream");
            response.setContentType("application/octet-stream");
            // 下载文件能正常显示中文
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(f.getName(), "UTF-8"));
            // 实现文件下载
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
	
	@RequestMapping("mirror/deleteById")
	@ResponseBody
	public String deleteMirror(Integer id) {
		
		Mirror mirror = mirrorService.getMirror(id);
		mirror.setStatus(0);
		int res = mirrorService.updateMirror(mirror);
		
		if (res == 1) {
			return "OK";
		}else {
			return "ERROR";
		}
		
	}
	
	
}

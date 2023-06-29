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

import com.ddb.entity.Video;
import com.ddb.service.VideoService;

/**
 * @author �ε���
 * @date 2021��1��19������3:34:17
 *
 */

@Controller
@RequestMapping("/study")
public class VideoController {
	
	@Autowired
	private VideoService videoService;
	
	@RequestMapping("/video/video-info")
	public String video(String name, Model model) {
		if (name == null) {
			name = "%%";
		}else {
			name = "%" + name + "%";
		}
		
		List<Video> allVideo = videoService.getAllVideo(name);
		
		model.addAttribute("all_video", allVideo);
		return "video";
	}
	
	@RequestMapping("/video/upload")
	@ResponseBody
	public int upload(MultipartFile files, String textarea, HttpSession session){
		Video video = new Video();
		
		if (files.isEmpty() != true && textarea.trim() != null && textarea.trim().length() != 0) {
			// ��ȡ�ļ���
			String file_name = files.getOriginalFilename();
			String realPath = session.getServletContext().getRealPath("upload/video");
			File file = new File(realPath);
			if (!file.getParentFile().exists()) {
				System.out.println("�ļ��в����ڣ�");
				file.getParentFile().mkdir();
			}
			video.setVideo_name(file_name);
			video.setDir_path(realPath);
			video.setVideo_remark(textarea);
			File f = new File(realPath, file_name);
			try {
				//�����ļ�
				files.transferTo(f);
				videoService.insertVideo(video);
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
	
	@RequestMapping(value = "/video/download", method = RequestMethod.GET)
	@ResponseBody
	public String download(HttpServletResponse response, Integer video_id) throws UnsupportedEncodingException{
		
		Video video = videoService.getVideo(video_id);
		
		String file = video.getDir_path() + "\\" + video.getVideo_name();
		
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
	
	@RequestMapping("video/deleteById")
	@ResponseBody
	public String deleteVideo(Integer id) {
		Video video = videoService.getVideo(id);
		video.setStatus(0);
		int res = videoService.updateVideo(video);
		
		if (res == 1) {
			return "OK";
		}else {
			return "ERROR";
		}
		
	}
	
	
}

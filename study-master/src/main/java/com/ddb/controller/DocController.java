package com.ddb.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.jodconverter.DocumentConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ddb.entity.Doc;
import com.ddb.entity.ImgInfo;
import com.ddb.service.DocService;

/**
 * @author �ε���
 * @date 2021��1��19������3:34:17
 *
 */

@Controller
@RequestMapping("/study")
public class DocController {
	
	@Autowired
	private DocService docService;
	
	@RequestMapping("/doc/doc-info")
	public String video(String name, Model model) {
		if (name == null) {
			name = "%%";
		}else {
			name = "%" + name + "%";
		}
		
		List<Doc> allDoc = docService.getAllDoc(name);
		
		model.addAttribute("all_doc", allDoc);
		return "doc";
	}
	
	@RequestMapping("/doc/upload")
	@ResponseBody
	public int upload(MultipartFile files, String textarea, HttpSession session){
		Doc doc = new Doc();
		if (files.isEmpty() != true && textarea.trim() != null && textarea.trim().length() != 0) {
			// ��ȡ�ļ���
			String file_name = files.getOriginalFilename();
			String realPath = session.getServletContext().getRealPath("upload/doc/");
			doc.setDoc_name(file_name);
			doc.setDir_path(realPath);
			doc.setDoc_remark(textarea);
			File f = new File(realPath, file_name);
			try {
				//�����ļ�
				files.transferTo(f);
				docService.insertDoc(doc);
				System.out.println("�ϴ��ɹ���");
				return 1;
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("�ϴ�ʧ�ܣ�");
				return 0;
			}
		}else {
			System.out.println("�ϴ��ļ����߱�עΪ�գ�");
			return -1;
		}
	}
	
	@RequestMapping(value = "/doc/download", method = RequestMethod.GET)
	public void download(String openStyle, Integer doc_id, HttpServletResponse response) throws UnsupportedEncodingException{
		
		//��ȡ�򿪷�ʽ
        openStyle = openStyle == null ? "attachment" : "inline";
        System.out.println(openStyle);
        
		Doc doc = docService.getDoc(doc_id);
		
		String file = doc.getDir_path() + "\\" + doc.getDoc_name();
		
		
		File f = new File(file);
		
		if (f.exists()) {
			/*// �����ļ�����
            response.setHeader("content-type", "application/octet-stream");
            response.setContentType("application/octet-stream");*/
            // �����ļ���������ʾ����
            response.setHeader("content-disposition", openStyle + ";filename=" + URLEncoder.encode(f.getName(), "UTF-8"));
            // ʵ���ļ�����
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(f);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    os.flush();
                    i = bis.read(buffer);
                }
            } catch (Exception e) {
                System.out.println("Download the song failed!");
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
	}
	
	@RequestMapping("doc/deleteById")
	@ResponseBody
	public String deleteDoc(Integer id) {
		Doc doc = docService.getDoc(id);
		doc.setStatus(0);
		int res = docService.updateDoc(doc);
		
		if (res == 1) {
			return "OK";
		}else {
			return "ERROR";
		}
		
	}
	
	@RequestMapping("doc/doc-edit")
	public String docEdit(){
		
		return "doc-edit";
	}
	
	@RequestMapping("doc/doc-save")
	@ResponseBody
	public ImgInfo setImgUrl(MultipartFile file, HttpServletResponse response, HttpServletRequest request)
			throws Exception {
		byte[] bytes = file.getBytes();
		String path = request.getServletContext().getRealPath("/upload/doc/image/");
		File imgFile = new File(path);
		if (!imgFile.exists()) {
			imgFile.mkdirs();
		}
		String fileName = file.getOriginalFilename();// �ļ�����
		try (FileOutputStream fos = new FileOutputStream(new File(path + fileName))) {
			fos.write(bytes);
		} catch (Exception e) {
			e.printStackTrace();
		}
 
		String value = "http://localhost:10080/upload/doc/image/" + fileName;
		String[] values = { value };
 
		ImgInfo imgInfo = new ImgInfo();
		imgInfo.setError(0);
		imgInfo.setUrl(values);
 
		return imgInfo;
	}
	
	@Autowired
    private DocumentConverter converter;  //����ת��
 
    @ResponseBody
    @RequestMapping("doc/doc-preview")
    public void toPdfFile(HttpServletResponse response, Integer doc_id) {
    	Doc doc = docService.getDoc(doc_id);
    	String f = doc.getDir_path() + "\\" + doc.getDoc_name();
    	
    	
        File file = new File(f);//��Ҫת�����ļ�
        try {
            File newFile = new File("C:/tmp");//ת��֮���ļ����ɵĵ�ַ
            if (!newFile.exists()) {
                newFile.mkdirs();
            }
            String savePath="C:/tmp/"; //pdf�ļ����ɱ����·��
            String fileName="JCccc"+UUID.randomUUID().toString().replaceAll("-","").substring(0,6);
            String fileType=".pdf"; //pdf�ļ���׺
            String newFileMix=savePath+fileName+fileType;  //��������ƴ������,����������������ļ��������������·����
 
            //�ļ�ת��
            converter.convert(file).to(new File(newFileMix)).execute();
            //ʹ��response,��pdf�ļ������ķ�ʽ���͵�ǰ���������
            ServletOutputStream outputStream = response.getOutputStream();
            InputStream in = new FileInputStream(new File(newFileMix));// ��ȡ�ļ�
            int i = IOUtils.copy(in, outputStream);   // copy������,iΪ�ֽ���
            in.close();
            outputStream.close();
            System.out.println("���ѹر�,��Ԥ��,���ļ��ֽڴ�С��"+i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
}

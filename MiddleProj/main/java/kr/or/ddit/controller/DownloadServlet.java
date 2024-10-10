package kr.or.ddit.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


/*
@WebServlet("/download.do")
public class DownloadServlet extends HttpServlet{

	MemberService memberService = MemberServiceImpl.getInstacne();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	// 디비에서 정보 가져오기.
		
		int fileNo = Integer.parseInt(req.getParameter("fileNo"));
		
		AttachFileVo fileVo = memberService.getFile(fileNo);
		// fileNo pk 값으로 디비에서 정보 가져오기
		
		String path = fileVo.getFile_path();
		
		String orgName = fileVo.getFile_org_name();
		String fileName = fileVo.getFile_name();
		
		//file 다운로드
		//download.do -> 요청
		//디비에서 요청에 해당하는 파일 경로 + 파일 이름

		// 파일 읽어오기
		
		// 파일 다운로드를 위한 응답 헤더 정보 설정하기
		resp.setContentType("application/octet-stream");
		
		resp.setHeader("Content-Disposition", "attachemnt; filename=\""
				        +URLEncoder.encode(fileName,"UTF-8")
				        .replaceAll("\\+", "%20")+"\""
				        );	 // url은 한글이 사용이 안되어서 인코딩 처리와 공백 처리를 해주어야한다.
		
		// 파일 읽어오기
		File file = new File(path+"/"+fileName);
		
		FileInputStream fis = new FileInputStream(file);
		BufferedInputStream bis = new BufferedInputStream(fis);
	
		// 파일 내보내기
		BufferedOutputStream bos = new BufferedOutputStream(resp.getOutputStream());
		
		int data=0;
		while((data= bis.read()) !=-1) {
			bos.write(data);
		}
		bis.close();
		bos.close();
	}//http://localhost:28080/FileIOWeb/download.do?fileNo=1 파일이 다운된다
}
*/
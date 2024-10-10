package kr.or.ddit.util;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import jakarta.servlet.http.Part;
import kr.or.ddit.vo.AttachFileVo;

public class UploadUtil {
	
	static String uploadPath="D:/upload_files";
	
	public static AttachFileVo writeFile(Part part) {
		
		AttachFileVo fileVo = new AttachFileVo();
		
		try {
			
			String orgFileName = getFileName(part);
			
			if(orgFileName != null && !orgFileName.equals("")) {
			UUID uuid = UUID.randomUUID(); //uuid 임시로 지정된 번호
			String fileName = uuid.toString();
			fileVo.setFile_org_name(orgFileName);
			fileVo.setFile_name(fileName);
			//파일크기
			fileVo.setFile_size(part.getSize());
			fileVo.setFile_path(uploadPath);
			String ext = orgFileName.split("\\.")[1];
			fileVo.setFile_ext(ext);
			
			part.write(uploadPath + "/" +fileName);
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileVo;
	}
	
	
	/**
	 * Part 정보를 파싱하며 파일 이름 추출하기
	 * @param part 파일 이름 추출할 Part 객체
	 * @return 파일명 : 파일명이 존재하지 않으면(폼필드인 경우) null 리턴함
	 */
	private static String getFileName(Part part) {
		
		/*
		 * Content-Disposition 헤더에 대하여
		 * 
		 * 파일업로드 사용되는 경우
		 * 
		 * Content-Disposition : form-data
		 * Content-Disposition : form-data; name="필드명"
		 * Content-Disposition : form-data; name="필드명"; filename="파일명"
		 * 
		 */
		for(String content : part.getHeader("Content-Disposition").split(";")) {
			if(content.trim().startsWith("filename")) {
				return content.substring(content.indexOf("=")+1).trim().replace("\"", "");
			}
		}
		
		return null;
	}
}

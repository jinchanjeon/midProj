package kr.or.ddit.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import kr.or.ddit.service.EventServiceImpl;
import kr.or.ddit.service.iEventService;
import kr.or.ddit.util.UploadUtil;
import kr.or.ddit.vo.AttachFileVo;
import kr.or.ddit.vo.EventVo;

@WebServlet("/eventUpload")
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 1,  // 1MB
    maxFileSize = 1024 * 1024 * 10,       // 10MB
    maxRequestSize = 1024 * 1024 * 50     // 50MB
)
public class EventUploadController extends HttpServlet {
    private iEventService eventService = EventServiceImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 게시글 정보 수집
        String boardTitle = req.getParameter("boardTitle");
        String boardContent = req.getParameter("boardContent");
        String boardStart = req.getParameter("boardStart");
        String boardEnd = req.getParameter("boardEnd");
        int memNo = Integer.parseInt(req.getParameter("memNo"));
        
        // EventVo 객체에 게시글 정보 설정
        EventVo eventVo = new EventVo();
        eventVo.setBoardTitle(boardTitle);
        eventVo.setBoardContent(boardContent);
        eventVo.setBoardStart(boardStart);
        eventVo.setBoardEnd(boardEnd);
        eventVo.setMemNo(memNo);

        // 파일 업로드 처리
        Part filePart = req.getPart("file");
        AttachFileVo fileVo = UploadUtil.writeFile(filePart);
        
        if (fileVo != null) {
            // 파일 정보를 EventVo에 설정
            eventVo.setBoardImg(fileVo.getFile_name());
        }

        // 글과 파일 정보 모두 저장
        eventService.eventInsert(eventVo);

        // 리다이렉트 또는 결과 페이지로 이동
        resp.sendRedirect(req.getContextPath() + "/eventList");
    }
}

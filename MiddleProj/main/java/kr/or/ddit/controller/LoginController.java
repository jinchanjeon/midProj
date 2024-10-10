package kr.or.ddit.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.or.ddit.service.MemberServiceImpl;
import kr.or.ddit.service.iMemberService;
import kr.or.ddit.vo.MemberVo;

@WebServlet("/login.do")
public class LoginController extends HttpServlet {

    iMemberService memberService = MemberServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        
        // 로그인 페이지로 접근할 때 이전 페이지 URL 저장
        String referer = req.getHeader("Referer");
        if(referer != null && !referer.contains("login.do")) {
            session.setAttribute("prevPage", referer);
        }

        req.getRequestDispatcher("/WEB-INF/view/Login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 요청 정보 받기
        String userId = req.getParameter("userId");
        String userPw = req.getParameter("userPw");

        // memberVo에 데이터 추가하기
        MemberVo member = new MemberVo();
        member.setMemId(userId);
        member.setMemPw(userPw);

        MemberVo mem = memberService.selectMembyId(userId);
        int memCode = mem.getMemCode();
        int memState = mem.getMemState();
        member.setMemCode(memCode);

        member = memberService.login(member);

        if(member == null) {
            // 로그인 실패
            req.setAttribute("message", "로그인에 실패했습니다.");
            req.getRequestDispatcher("/WEB-INF/view/Login.jsp").forward(req, resp);
        } else if(memState == 2) {
            // 탈퇴회원 로그인 시 경고처리
            req.setAttribute("message", "탈퇴한 회원입니다. 다시 가입해주세요.");
            req.getRequestDispatcher("/WEB-INF/view/Login.jsp").forward(req, resp);
        } else {
            // 로그인 성공
            HttpSession session = req.getSession();
            session.setAttribute("member", member);
            
            // mem_no와 mem_name을 세션에 저장
            session.setAttribute("mem_no", member.getMemNo()); 
            session.setAttribute("mem_name", member.getMemName());  
            
            
            // 이전 페이지로 리디렉션 또는 기본 메인 페이지로 리디렉션
            String prevPage = (String) session.getAttribute("prevPage");
            if (prevPage != null) {
                resp.sendRedirect(prevPage);
                session.removeAttribute("prevPage");
            } else {
                resp.sendRedirect("/MiddleProj/main.do");
            }
        }
    }
}
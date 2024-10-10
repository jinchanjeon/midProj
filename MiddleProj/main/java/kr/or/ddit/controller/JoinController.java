package kr.or.ddit.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import org.apache.commons.beanutils.BeanUtils;

import com.google.gson.Gson;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.service.MemberServiceImpl;
import kr.or.ddit.service.iMemberService;
import kr.or.ddit.vo.MemberVo;
@WebServlet("/join.do")
public class JoinController extends HttpServlet{
	
	iMemberService memberService = MemberServiceImpl.getInstance();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 코드값을 생성해서 넘겨줄 예정
//		req.setAttribute("joinCode", "join"); 
		req.getRequestDispatcher("/WEB-INF/view/Join.jsp").forward(req, resp);
			//회원가입 또는 마이페이지가 나오게끔 하면 될 듯, 경로 안에 회원가입 jsp 만들어야 함
		
		
		
	
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		MemberVo member = new MemberVo();
		
		//urlencoding형식(key=value)
		try {
			BeanUtils.populate(member, req.getParameterMap());
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int result = memberService.join(member); // 왜 int 타입으로 해야하는지?
		
		//System.out.println(">>>>>>>>" + result); 가입 잘 되므로 주석처리
		resp.getWriter().print(result); // 클라이언트에게 추가적으로 보여줄게 있으면 활용
		
	
		
		
	}
	

}

package kr.or.ddit.filter;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import kr.or.ddit.service.BoardServiceImpl;
import kr.or.ddit.service.iBoardService;
import kr.or.ddit.vo.BoardVo;


public class EncodingFilter implements Filter{
	String enc;
	
	//web.xml에 설정해놓은 인코딩 
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		enc = filterConfig.getInitParameter("encode");
	}
	
	iBoardService boardService = BoardServiceImpl.getInstance();
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		
				
		req.setCharacterEncoding(enc);
		resp.setCharacterEncoding(enc);
		chain.doFilter(req, resp);
		
		
	}
	
}

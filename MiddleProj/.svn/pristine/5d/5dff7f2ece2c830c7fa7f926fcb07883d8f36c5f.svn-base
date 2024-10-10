<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Driver"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String userId = request.getParameter("userId");
	String userPw = request.getParameter("userPw");

	//JDBC로 DB에 접근하고 응답 데이터 만들기
	//1. 연결할 DB가 설치되어 있어야 한다
	//2. 자바와 DB를 연결하기 위한 라이브러리 필요(ojdbc.jar)
	//3. class.forName()을 이용해 드라이버 로딩
	Class.forName("oracle.jdbc.driver.OracleDriver");
	//4. DriverManager를 이용ㅇ해 connection객체 생성
	String url = "jdbc:oracle:thin:@112.220.114.131:1521:xe";
	Connection conn = DriverManager.getConnection(url, "team2_202407M", "java");
    //5.sql 구문 실행을 위한 Statement객체 생성
    Statement stmt = conn.createStatement();
    
    String sql = "select mem_id from member" + 
    			 " where mem_id = '"+userId+
    			 "' and mem_pass = '"+userPw+"'";
    //id정보를 전달해 유무 판단하고 결과 받기
    ResultSet rs = stmt.executeQuery(sql);
    //응답정보 생성 - JSON형식으로 작성
    
    if(rs.next()){
    	//값이 조회됐으므로 중복입니다
   %>
    	{"rst":"ok"}
   <% 		
    }
    else{
    	//값이 중복이 아닙니다.
   %>
   	{"rst":"no"}
   	<% 	
    }//if-else 닫기
    
    // 생성했던 객체 닫기
    rs.close();
    stmt.close();
    conn.close();
    
    
%>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.vo.EventVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
<c:set var='contextPath' value='${pageContext.request.contextPath}'></c:set>
<%
	EventVo event = (EventVo)request.getAttribute("event");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
.table .td .th{
	width : 100%;
	text-align : center;
	border: 1px solid white;
	border-color: white;
}
 th{
border: 1px solid #fff;
width: 100px;
color:white;
 }
 td{
border: 1px solid #fff;
width: 50px;
height: 50px;
 }
p{
color : white !important ;
}
img{
    display: flex;
    justify-content: center;
    text-align: center;
}
</style>
</head>
<body>
<%@include file="../../includes/top.jsp" %>
<!-- <section class="upcoming-meetings" id="meetings"> -->
<!--         <div class="container"> -->
<!--           <div class="row"> -->
<!--             <div class="col-lg-12"> -->
<!--               <div class="section-heading"> -->
<!--                 <h2>이벤트</h2> -->
<!--               </div> -->
<!--             </div> -->
<!--         </div> -->
<!--         </div> -->
<!--     </section> -->
    <section class="meetings-page" id="meetings">
        <div class="container">
          <div class="row">
            <div class="col-lg-12">
              <div class="row">
                <div class="col-lg-12">
                  <div class="filters">
                    <ul>
                      <li><a href="/MiddleProj/eventList">EVENT</a></li>
                    <li><a href="meeting-details.html" class="active">시사회</a></li>
                    </ul>
                  </div>
                </div>
<!--     <input type="button" value="뒤로가기" onclick="location.href='eventList'"> -->
	<table border = "">
		<thead>
			<tr>
				<th>게시판 번호</th>
				<th style="color: yellow"><%= event.getBoardNo() %></th>
				<th>제목</th>
				<th colspan="3" ><%= event.getBoardTitle() %></th>
			</tr>
			<tr>
				<th>날짜</th>
				<th><%= event.getBoardWriteDate().toString().split(" ")[0] %></th>
				<th>작성자</th>
				<th><%= event.getWriter() %></th>
				<th>조회수</th>
				<th><%= event.getBoardCnt() %></th>
				<th>종료일</th>
				<th><%=event.getBoardEnd().toString().split(" ")[0] %></th>
			</tr>
		</thead>
		<tbody>
			<tr><font color="white">
				<td colspan="9"  style="text-align: center;"><font color="white">
				<img src="<%= event.getBoardImg() %> " alt="대체텍스트" style="display: block; margin: auto;" >
				<img src="<%= event.getBoardThumb() %> " alt="대체텍스트" style="display: block; margin: auto;" >
				</td>
			</tr>
		</tbody>
	</table>
<%-- 	<a href="/MiddleProj/eventList"><%=event.getBoardNo() %>으로 이동</a> --%>
<!-- 	<a href="/MiddleProj/eventList">목록으로 이동</a> -->
<%-- 	<a href="/MiddleProj/eventUpdate?boardNo=<%= event.getBoardNo()%>">게시글 수정</a> --%>
<!-- 	<a href="/MiddleProj/eventList/boardDelete">게시글 삭제 </a> -->
	
	<button onclick="location.href='/MiddleProj/eventList'">목록</button>
<button onclick="location.href='/MiddleProj/eventUpdate?boardNo=<%= event.getBoardNo() %>'">수정</button>
    <button type="button" onclick="confirmDelete()">삭제</button>
	
	
	
	
<form id="deleteForm" action="/MiddleProj/eventDelete?<%= event.getBoardNo() %>" method="post">
    <input type="hidden" name="boardNo" value="<%= event.getBoardNo() %>">
</form>

<script>
    function confirmDelete() {
        if (confirm("삭제하시겠습니까?")) {
            document.getElementById("deleteForm").submit();
        }
    }
</script>
	<%@ include file = "../../includes/bottom.jsp"%>
</body>
</html>
<!-- // eventUpdate.jsp -->

<%@page import="kr.or.ddit.vo.EventVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	EventVo event = (EventVo)request.getAttribute("event");
%>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, viewport-fit=cover">
	<!-- 클래식 에디터 -->
	<script src="https://cdn.ckeditor.com/ckeditor5/29.1.0/classic/ckeditor.js"></script>
	<title>Home</title>
	
	<!-- 넓이 높이 조절 -->
	<style>
	.ck.ck-editor {
    	max-width: 500px;
	}
	.ck-editor__editable {
	    min-height: 300px;
	}
	</style>
</head>
<body>
<%@include file="../../includes/top.jsp" %>

 <section class="meetings-page" id="meetings">
        <div class="container">
          <div class="row">
            <div class="col-lg-12">
              <div class="row">
                <div class="col-lg-12">
                  <div class="filters">
                    <ul>
                      <li><a href="meetings.html">EVENT</a></li>
                    </ul>
                  </div>
                </div>
                
                
<form action="/MiddleProj/eventUpdate" method="post">
    <input type="hidden" name="boardNo" value=<%= event.getBoardNo() %>>
    <label for="title"><font color="white">Title:</label>
    <input type="text" id="title" name="title" value=<%= event.getBoardTitle() %>>
    
    <textarea id="content" name="content"><%= event.getBoardContent() %></textarea>
    
    <button type="submit">Update</button>
</form>

<script>
    ClassicEditor
        .create(document.querySelector('#content'))
        .catch(error => {
            console.error(error);
        });
</script>

    <%@ include file = "../../includes/bottom.jsp"%>
    

</body>
</html>

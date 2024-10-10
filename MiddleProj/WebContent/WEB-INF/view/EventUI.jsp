<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.vo.EventVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
<c:set var='contextPath' value='${pageContext.request.contextPath}'></c:set>
<%
	List<EventVo> eventList = (List<EventVo>)request.getAttribute("eventList");
%>    
<!DOCTYPE html>
<html lang="en">
<%@ include file = "../../includes/top.jsp"%>
<head>
<style>
.thumb img{
width : 273.6;
height : 180;
object-fit : fill;
}

.down-content{
background-color : green;
}
</style>

</head>
<body>
<!--     <section class="upcoming-meetings" id="meetings"> -->
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
<!--                     </ul> -->
                  </div>
                </div>

                          <div class="col-lg-12">
                  <div class="row grid">
                          <%
                          	for(EventVo event : eventList){
                          %>
                    <div class="col-lg-4 templatemo-item-col all soon">
                      <div class="meeting-item">
                        <div class="thumb">
                          <a href="/MiddleProj/event?boardNo=<%= event.getBoardNo()%>"><img src="<%= event.getBoardThumb() %> " alt="대체텍스트" style="display: block; margin: auto;" ></a>
                        </div>	
                        
                        <div class="down-content">
                          <div class="date">
                            <h6>EVENT</h6>
                          </div>
                          <a href="/MiddleProj/event?boardNo=<%= event.getBoardNo()%>"><h4><%= event.getBoardTitle() %></h4></a>
                         <a><br/><%=event.getBoardStart().toString().split(" ")[0] %> ~ <%=event.getBoardEnd().toString().split(" ")[0] %></a>
                        </div>
                      </div>
                    </div>
                       	<%
                          	}
                          %>
                        </div>
                      </div>
                    </div>
                    <input type="button" value="글쓰기" onclick="location.href='eventInsert'">
<%@ include file = "../../includes/bottom.jsp"%>

</body>
</html>
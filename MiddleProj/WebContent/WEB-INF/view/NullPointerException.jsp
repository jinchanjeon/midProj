<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="kr.or.ddit.movie.Member" %> <!-- 올바른 패키지 이름을 넣어주세요 -->
<html>
<head><title>Member Management</title></head>
<body>
<h2>Member Management</h2>

<%
    List<Member> members = (List<Member>) request.getAttribute("members");
    if (members == null || members.isEmpty()) { // null 체크 및 비어 있는지 체크
%>
        <p>No members found.</p>
<%
    } else {
%>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
        </tr>
        <%
            for (Member member : members) {
        %>
            <tr>
                <td><%= member.getId() %></td>
                <td><%= member.getName() %></td>
                <td><%= member.getEmail() %></td>
            </tr>
        <%
            }
        %>
    </table>
<%
    }
%>
<a href="/manager?action=addMember">Add Member</a>
</body>
</html>

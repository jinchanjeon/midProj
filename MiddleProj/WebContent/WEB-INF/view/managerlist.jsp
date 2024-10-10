<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>매니저 목록</title>
</head>
<body>
    <h1>매니저 목록</h1>
    <a href="/manager/add">매니저 추가</a>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>이름</th>
                <th>이메일</th>
                <th>전화번호</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="manager" items="${managers}">
                <tr>
                    <td>${manager.id}</td>
                    <td>${manager.name}</td>
                    <td>${manager.email}</td>
                    <td>${manager.phone}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="kr.or.ddit.movie.Movie" %>
<html>
<head><title>Movie Management</title></head>
<body>
<h2>Movie Management</h2>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Title</th>
        <th>Genre</th>
    </tr>
    <%
        List<Movie> movies = (List<Movie>) request.getAttribute("movies");
        if (movies == null || movies.isEmpty()) {
    %>
        <tr>
            <td colspan="3">No movies found.</td>
        </tr>
    <%
        } else {
            for (Movie movie : movies) {
    %>
        <tr>
            <td><%= movie.getId() %></td>
            <td><%= movie.getTitle() %></td>
            <td><%= movie.getGenre() %></td>
        </tr>
    <%
            }
        }
    %>
</table>
<a href="/manager?action=addMovie">Add Movie</a>
</body>
</html>

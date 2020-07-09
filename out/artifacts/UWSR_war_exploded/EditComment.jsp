<%@ page import="java.sql.Connection" %>
<%@ page import="Model.Comment" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: dmitr
  Date: 6/23/2020
  Time: 10:51 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit comment</title>
    <link rel="stylesheet" href="css/main.css" type="text/css">
</head>
<body>
<div class="FormEditComment">
    <%
        Comment comment = null;
        try {
            comment = Comment.getCommentFromDb(Integer.parseInt(request.getParameter("Id")));
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    %>
    <form method="post" action="EditComment">
        <span>Your comment:</span>
        <textarea cols="15" rows="5" name="comment"><%=comment.get_CommentText()%></textarea>
        <input type="text" value="<%=request.getParameter("Id")%>" style="display: none;" name="Id">
        <input type="submit" value="Terminate editing">
    </form>
</div>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: dmitr
  Date: 6/22/2020
  Time: 9:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Model.Comment"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.time.LocalDate" %>
<html>
<head>
    <title>Comments</title>
    <link rel="stylesheet" href="css/main.css" type="text/css">
</head>
<body>
<div id="comments">
    <div id="FormComment">
<form method="post" action="AddComment">
    <table>
        <tr>
            <td>Your name:</td>
            <td><input type="text" name="name" width="150px"></td>
        </tr>
        <tr>
            <td>Your comment:</td>
            <td>
                <textarea cols="15" rows="5" name="comment"></textarea>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="text" value="<%=request.getParameter("Id")%>" style="display: none;" name="Id">
                <input type="submit" value="Add a comment!">
            </td>
        </tr>
    </table>
</form>
</div>
    <div id="ListOfComments">
        <%
            ArrayList<Comment> comments = null;
            try {
                comments = Comment.getCommentsFromDb(Integer.parseInt(request.getParameter("Id")));
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            for(Comment comment: comments){
        %>

        <div class="Comment">
            <p>
                <span><b><%=comment.get_author()%></b></span><br>
                <span><i><%=comment.get_date().toString()%></i></span>
                <p><%=comment.get_CommentText()%></p>
                <%
                    Cookie[] cookies = request.getCookies();
                    String UserName = null;
                    String cookieName = "UserName";
                    LocalDate date = LocalDate.now();
                    String p = String.valueOf(date.getMonthValue() + date.getDayOfMonth() + date.getYear());
                    for(Cookie cookie: cookies){
                        if (cookieName.equals(cookie.getName())) {
                            UserName = cookie.getValue();
                            break;
                        }
                    }
                    if (UserName.equals(comment.get_author()) || UserName.equals(p)){
                %>
                    <a href="EditComment.jsp?Id=<%=comment.get_id()%>">Edit comment</a>
                    <a href="EditComment?Id=<%=comment.get_id()%>&action=del">Delete comment</a>
                <%}%>
            </p>
        </div>
        <%}%>
    </div>
</div>
</body>
</html>

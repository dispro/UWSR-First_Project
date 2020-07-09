<%--
  Created by IntelliJ IDEA.
  User: dmitr
  Date: 6/18/2020
  Time: 1:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Model.Link, java.sql.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.time.LocalDate" %>
<!Doctype html>
<html>
  <head>
    <title>UWSR</title>
    <link rel="stylesheet" href="css/main.css" type="text/css">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  </head>
  <body>
  <header>
    <h1>UWSR</h1>
      <div id="Search">
          <form method="post" action="search">
              <input type="text" width="150px" name="find">
              <input type="submit" value="Search">
          </form>
      </div>
  </header>
  <main>
      <div id="FormAddLink">
          <form method="POST" action="AddLink">
              <table>
                  <tr>
                      <td>Title:</td><td><input type="text" width="150px" name="Title"></td>
                  </tr>
                  <tr>
                      <td>Link:</td><td><input type="text" width="150px" name="Ref"></td>
                  </tr>
                  <tr>
                      <td>KeyWords:</td><td><textarea cols="40" rows="5" name="DescriptionLink"></textarea></td>
                  </tr>
                  <tr>
                      <td colspan="2">
                          <input type="submit" value="Add Link" width="150px" align="center"/>
                      </td>
                  </tr>
              </table>
          </form>
      </div>
      <div Id="ListOfLinks">
          <%
              try {
                  Class.forName("com.mysql.cj.jdbc.Driver");
              String url = "jdbc:mysql://localhost:3306/UWSR"+
                      "?verifyServerCertificate=false"+
                      "&useSSL=false"+
                      "&requireSSL=false"+
                      "&useLegacyDatetimeCode=false"+
                      "&amp"+
                      "&serverTimezone=Europe/Moscow";
            Connection connection = DriverManager.getConnection(url, "root", "123456789");
            Statement statement = connection.createStatement();
            ResultSet listOfLinks = statement.executeQuery("SELECT * FROM Links");
            ArrayList<Link> links = new ArrayList<Link>();

            while(listOfLinks.next()){
                Link link = new Link(listOfLinks.getInt(1),listOfLinks.getString(3),listOfLinks.getString(2), listOfLinks.getString(4), listOfLinks.getInt(5), listOfLinks.getInt(6));
                links.add(link);
            }

            for(Link link: links){

          %>
          <div class="Link">
              <a href=<%=link.get_Ref()%>><h2><%=link.get_Title()%></h2></a>
              <p><%=link.get_DescriptionLink()%></p>
              <div class="btns">
                  <a href="AddLink?Id=<%=link.get_id()%>&action=true">+</a>
                  <a href="AddLink?Id=<%=link.get_id()%>&action=false">-</a>
                  <a href="Comment.jsp?Id=<%=link.get_id()%>" target="_blank">Comment</a>
                  <span class="Useful"><%=link.get_IsUseful()%></span>
                  <span class="Useless"><%=link.get_IsUseless()%></span>
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
                      if (UserName != null && UserName.equals(p)){
                  %>
                  <a href="DeleteLink?Id=<%=link.get_id()%>">Delete</a>
                  <%}%>
              </div>
          </div>
      <%}

      }catch(ClassNotFoundException e){
                  e.printStackTrace();
      }%>
      </div>
      <div id="FindedLinks">
          <%    if(request.getAttribute("findedLinks") != null){
                ArrayList<Link> findedLinks = (ArrayList<Link>) request.getAttribute("findedLinks");
              //  if (!findedLinks.isEmpty())
                for(Link link: findedLinks){
                %>
                    <div class="Link">
                        <a href=<%=link.get_Ref()%>><h2><%=link.get_Title()%></h2></a>
                        <p><%=link.get_DescriptionLink()%></p>
                        <div class="btns">
                            <a href="AddLink?Id=<%=link.get_id()%>&action=true">+</a>
                            <a href="AddLink?Id=<%=link.get_id()%>&action=false">-</a>
                            <a href="Comment.jsp?Id=<%=link.get_id()%>" target="_blank">Comment</a>
                            <span class="Useful"><%=link.get_IsUseful()%></span>
                            <span class="Useless"><%=link.get_IsUseless()%></span>
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
                                if (UserName != null && UserName.equals(p)){
                            %>
                            <a href="DeleteLink?Id=<%=link.get_id()%>">Delete</a>
                            <%}%>
                        </div>
                    </div>
                    <%}}%>
      </div>
  </main>
  </body>
</html>

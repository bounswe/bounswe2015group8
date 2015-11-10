<%--
  Created by IntelliJ IDEA.
  User: gokcan
  Date: 08.11.2015
  Time: 17:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
    <head>
        <title>Sign up</title>
        <style>
            form {text-align:center}
            #header{
              background-color:black;
              color:white;
              text-align:center;
              padding:10px;
            }
            #section{
              background-color:skyblue;
              color:black;
              text-align:center;
              padding:40%;
            }
            #footer{
              background-color:black;
              color:white;
              text-align:right;
              padding: 5px;
            }
            div.roundbox {
              border: 2px solid #00f ;
              border-radius: 20px ;
              padding: 20px ;
              background-color: #e4e8f3 ;
              color: #000 ;
              width: 100% ;
              margin-left: auto ;
              margin-right: auto ;
              margin-bottom: 5px;
            }
          </style>
    </head>
    <body>
    <div id="header">
        CURRENT HERITAGES
    </div>

    <script type="text/javascript">
        console.log("${allHeritages}");
    </script>

    <span> Why don't you add a new cultural heritage?
        <button onclick="window.location.href='${contextPath}/heritage'"> Add a cultural heritage!</button>
    </span>
    <br>

    <c:forEach items="${allHeritages}" var="heritage">
        <div class="roundbox">
            <span>Name: ${heritage.name}</span>
            <br>
            <span>Place: ${heritage.place}</span>
            <br>
            <span>Description: ${heritage.description}</span>
            <button style="float:right; margin-right:5%;" onclick="window.location.href='${contextPath}/show_posts/${heritage.id}'">See Posts</button>
            <br>
            <span>Date posted: ${heritage.postDate}</span>
            <button style="float:right; margin-right:5%;" onclick="window.location.href='${contextPath}/post/${heritage.id}'">Add Post</button>
        </div>
    </c:forEach>

    <div id="footer">
         Copyright © lokum
    </div>
    </body>
</html>
<%--
  Created by IntelliJ IDEA.
  User: Goktug
  Date: 13.11.2015
  Time: 19:12
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
    }
  </style>
</head>
<body>
<div id="header">
  Add a comment
</div>

<div id="section">
  <div class="roundbox">
    <form class="form-inline" id="form" action="${contextPath}/post_comment" method="POST" enctype="multipart/form-data">
      <input type="text" name="postId" style="display:none;" value="${postId}"/>
      <div class="form-group" style="height:50%;">
        <label for="content">Content</label>
        <textarea type="text" class="form-control wideInput" id="content" name="content"
                  placeholder="Write Your Comment" style="height:80%;" autocomplete="off"></textarea>
      </div>
      <button type="submit" class="btn btn-default">Send!</button><br>
    </form>
  </div>
</div>

<div id="footer">
  Copyright © lokum
</div>
</body>
</html>
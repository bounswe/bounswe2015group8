<%--
  Created by IntelliJ IDEA.
  User: gokcan
  Date: 03.11.2015
  Time: 01:43
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
  WELCOME TO OUR SIGNUP PAGE
</div>

<div id="section">
  <div class="roundbox">
    <form class="form-inline" id="form" action="${contextPath}/upload_post" method="POST" enctype="multipart/form-data">
      <input type="text" name="content" id="content" class="form-control"> <br>
      <input type="file" name="media" id="media" class="form-control"> <br>
      <button type="submit" class="btn btn-default">POST!</button><br>
    </form>
  </div>
</div>

<div id="footer">
  Copyright © lokum
</div>
</body>
</html>
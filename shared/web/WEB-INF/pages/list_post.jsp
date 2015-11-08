<%--
  Created by IntelliJ IDEA.
  User: gokcan
  Date: 03.11.2015
  Time: 02:34
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
    WELCOME TO OUR SIGNUP PAGE
</div>
<script type="text/javascript">
    console.log("${posts}");
</script>
<c:forEach items="${posts}" var="post">
    <div class="roundbox">
        <span>Title: ${post.title}</span>
        <br>
        <span>Content: ${post.content}</span>
        <br>
        <span>Date posted: ${post.postDate}</span>
    </div>
</c:forEach>


<div id="footer">
    Copyright © lokum
</div>
</body>
</html>
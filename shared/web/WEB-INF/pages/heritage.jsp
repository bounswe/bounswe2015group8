<%--
  Created by IntelliJ IDEA.
  User: gokcan
  Date: 08.11.2015
  Time: 17:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Sign up</title>
    <style>
        form {
            text-align: center
        }

        #header {
            background-color: black;
            color: white;
            text-align: center;
            padding: 10px;
        }

        #section {
            background-color: skyblue;
            color: black;
            text-align: center;
            padding: 40%;
        }

        #footer {
            background-color: black;
            color: white;
            text-align: right;
            padding: 5px;
        }

        div.roundbox {
            border: 2px solid #00f;
            border-radius: 20px;
            padding: 20px;
            background-color: #e4e8f3;
            color: #000;
            width: 100%;
            margin-left: auto;
            margin-right: auto;
        }
    </style>
</head>
<body>
<div id="header">
    ADD SOME CULTURAL HERITAGE!
</div>

<div id="section">
    <div class="roundbox">
        <form class="form-inline" id="form" action="${contextPath}/upload_heritage" method="POST"
              enctype="multipart/form-data">
            Name: <input type="text" name="name" id="name" class="form-control"> <br>
            Place: <input type="text" name="place" id="place" class="form-control"> <br>
            Description: <input valign="top" style="height:50%; width:80%;" type="text" name="description"
                                id="description" class="form-control"> <br>
            <input type="file" name="media" id="media" class="form-control"> <br>
            <button type="submit" class="btn btn-default">POST!</button>
            <br>
        </form>
    </div>
</div>

<div id="footer">
    Copyright © lokum
</div>
</body>
</html>
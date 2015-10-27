<%--
  Created by IntelliJ IDEA.
  User: Berk
  Date: 26.10.2015
  Time: 23:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Login</title>
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
            background-color: #c4e8f3 ;
            color: #000 ;
            width: 100% ;
            margin-left: auto ;
            margin-right: auto ;
        }
    </style>
</head>
<body>
    <div id="header">
        WELCOME TO OUR LOGIN PAGE
    </div>

    <div id="section">
        <div class="roundbox">
            <form action="${contextPath}/login" method="post" class="form-inline" id="form">
                <label for="username"><b>Username: </b></label>
                <input type="text" name="username" id="username" class="form-control"> <br>
                <label for="password"><b>Password: </b></label>
                <input type="password" name="password" id="password" class="form-control"> <br>
                <button type="submit" class="btn btn-default">Log In</button><br>
                <c:if test="${doesUserExist == false}">
                    <span style="color:red;">Your username or password is wrong!</span>
                </c:if>
            </form>
        </div>
    </div>

    <div id="footer">
        Copyright © lokum
    </div>
</body>
</html>

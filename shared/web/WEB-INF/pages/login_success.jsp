<%--
  Created by IntelliJ IDEA.
  User: gokcan
  Date: 27.10.2015
  Time: 00:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login: successful</title>
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
        WELCOME!
    </div>

    <div id="section">
        <div class="roundbox">
            Welcome, ${username}! You are in the system...
        </div>
    </div>

    <div id="footer">
        Copyright © lokum
    </div>
</body>
</html>

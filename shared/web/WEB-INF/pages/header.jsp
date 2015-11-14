<%--
  Created by IntelliJ IDEA.
  User: coldwhistle
  Date: 11/6/15
  Time: 7:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${contextPath}/static/css/bootstrap/bootstrap.css">
    <link rel="stylesheet" href="${contextPath}/static/css/bootstrap/bootstrap-theme.css">

    <style>
        form {
            text-align: center
        }

        #header {
            background-color: black;
            color: white;
            text-align: center;
            padding: 5px;
            height: 100px;
        }

        #section {
            padding: 1%;
        }

        #footer {
            background-color: black;
            color: white;
            text-align: right;
            padding: 5px;
            width:100%;
            height:80px;
            position:absolute;
            bottom:0;
            left:0;
        }

        .btn-modified {
            display: inline-block;
            padding: 3px 7px;
            margin-bottom: 0;
            font-size: 14px;
            font-weight: normal;
            line-height: 1.42857143;
            text-align: center;
            white-space: nowrap;
            vertical-align: middle;
            -ms-touch-action: manipulation;
            touch-action: manipulation;
            cursor: pointer;
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none;
            background-image: none;
            border: solid#337ab7;
            border-radius: 4px;
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
            margin-bottom: 5px;
            width: 40%;
        }
    </style>
    <title>WeStory</title>
</head>
<div id="header">
    <center>
    <h2>WeStory</h2>
    <p>Share your story.</p>
    </center>
</div>
<body>
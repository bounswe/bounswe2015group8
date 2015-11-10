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
    <title>Sign up</title>
    <style>
        body {
            background-color:skyblue;
        }
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
        .image-upload > input {
            display: none;
        }
        .wideInput{
            text-align: left;
            padding-left:0;
            padding-top:0;
            padding-bottom:0.4em;
            padding-right: 0.4em;
            width: 400px;
            height: 200px;
        }
    </style>
    <link rel="stylesheet" href="${contextPath}/static/css/bootstrap/bootstrap.css">
    <link rel="stylesheet" href="${contextPath}/static/css/bootstrap/bootstrap-theme.css">
</head>
<body>
<div id="header">
  POST YOUR STORY!
</div>
<br>
<div class="row" style="background-color: skyblue;">
    <div class="col-md-4 col-md-offset-4">
        <form id="form" action="${contextPath}/upload_post" method="POST" enctype="multipart/form-data">
            <input type="text" name="heritageId" style="display:none;" value="${heritageId}"/>
            <div class="form-group">
                <label for="title">Title</label>
                <input type="text" class="form-control" id="title" name="title" placeholder="Enter a Title">
            </div>
            <div class="form-group" style="height:50%;">
                <label for="content">Content</label>
                <textarea type="text" class="form-control wideInput" id="content" name="content"
                       placeholder="Write Your Story.." style="height:80%;" autocomplete="off"></textarea>
            </div>
            <div class="form-group">
                <label for="media">
                    <img style="float: right;" height="5%" width="10%" src="/static/img/attachment.png"/>
                </label>
                <input style="display: none;" id="media" name="media" type="file"/>
                <button type="submit" class="btn btn-success">POST!</button>
            </div>
        </form>
    </div>
</div>


<%--
<div id="section">
  <div class="roundbox">
    <form class="form-inline" id="form" action="${contextPath}/upload_post" method="POST" enctype="multipart/form-data">
      Title: <input type="text" name="title" id="title" class="form-control"> <br>
      Content: <input valign="top" style="height:50%; width:80%;" type="text" name="content" id="content" class="form-control"> <br>
      <input type="file" name="media" id="media" class="form-control"> <br>
      <input type="text" name="heritageId" style="display:none;" value="${heritageId}"/>
      <button type="submit" class="btn btn-default">POST!</button><br>
    </form>
  </div>
</div>
--%>

<div id="footer">
  Copyright © lokum
</div>
</body>
</html>
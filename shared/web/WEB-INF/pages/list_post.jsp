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

<c:set var="posts" value="${allContent.posts}"/>
<c:set var="medias" value="${allContent.medias}"/>
<c:set var="comments" value="${allContent.comments}"/>

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
            margin-bottom: 5px;
        }
    </style>
    <link rel="stylesheet" href="${contextPath}/static/css/bootstrap/bootstrap.css">
    <link rel="stylesheet" href="${contextPath}/static/css/bootstrap/bootstrap-theme.css">
</head>
<body>
<div id="header">
    YOUR POSTS
</div>

<div class="container" style="height:100%; overflow-y: scroll;">
    <c:forEach items="${posts}" var="post">
        <div class="row" style="background-color: skyblue;">
            <div class="col-md-4 col-md-offset-4">
                <form id="form" action="${contextPath}/upload_post" method="POST" enctype="multipart/form-data">
                    <div class="form-group">
                        <label for="title_${post.id}">Title</label>
                        <input disabled="disabled" type="text" class="form-control" id="title_${post.id}"
                               value="${post.title}">
                    </div>
                    <div class="form-group" style="height:50%;">
                        <label for="content_${post.id}">Content</label>
                        <textarea disabled="disabled" type="text" class="form-control wideInput" id="content_${post.id}"
                                  style="height:80%;">${post.content}</textarea>
                    </div>
                    <div class="form-group">
                        <label for="date_${post.id}">Date posted:</label>
                        <input type="text" disabled="disabled" class="form-control" id="date_${post.id}"
                               value="${post.postDate}">
                    </div>

                    <c:forEach var="media" items="${medias}">
                        <c:if test="${media.postOrHeritageId == post.id && media.postOrHeritage==false}">
                            <div class="form-group">
                                <img src="${contextPath}/static/${media.mediaLink}" height="100%" width="100%">
                            </div>
                        </c:if>
                    </c:forEach>
                </form>
            </div>
        </div>
        <hr style="border:3px;">

        <%--<div class="roundbox">
            <span>Title: ${post.title}</span>
            <br>
            <span>Content: ${post.content}</span>
            <br>
            <span>Date posted: ${post.postDate}</span>
        </div>--%>
    <span>Do you have an opinion on this post?
        <button style="float:right; margin-right:5%;"
                onclick="window.location.href='${contextPath}/comment/${post.id}'">Add Comment
        </button>
    </span>
    </c:forEach>

</div>

<div id="footer">
    Copyright © lokum
</div>
</body>
</html>
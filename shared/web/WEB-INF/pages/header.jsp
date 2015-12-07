<%--
  Created by IntelliJ IDEA.
  User: coldwhistle
  Date: 11/6/15
  Time: 7:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>WeStory</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <script src="https://content.jwplatform.com/libraries/s5hFO93v.js"></script>

    <link rel="stylesheet" href="${contextPath}/static/css/bootstrap.css">
    <link rel="stylesheet" href="${contextPath}/static/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="${contextPath}/static/css/bootstrap-tokenfield.css"/>
    <link rel="stylesheet" href="${contextPath}/static/css/styles.css">
    <link rel="stylesheet" href="${contextPath}/static/css/jquery-ui.min.css"/>

    <script src="${contextPath}/static/js/jquery-1.11.3.js"></script>
    <script src="${contextPath}/static/js/jquery-ui.min.js"></script>
    <script src="${contextPath}/static/js/bootstrap/bootstrap.js"></script>
    <script src="${contextPath}/static/js/bootstrap/bootstrap-tokenfield.js"></script>
</head>

<script type="text/javascript">
    function searchByTag(){
        var tag = document.getElementById("search").value;
        window.location.href = "${contextPath}/searchByTag/" + tag;
    }
</script>

<body>
<nav class="navbar navbar-default navbar-static-top header">
    <sec:authorize var="isAuthorized" access="isAuthenticated()" />
    <div class="container-fluid"  style="background-color: darkblue">
        <div class="navbar-header">
            <a class="navbar-brand" href="${contextPath}/show_heritages" style="color: floralwhite">&#x2655; WeStory</a>
        </div>
        <c:if test="${isAuthorized}">
            <a style="float:right; margin-top: 0.5%;" href="${contextPath}/logout" class="btn btn-default" role="button">Log Out</a>
            <a style="float:right; margin-top: 0.5%; margin-right: 0.5%; font-size: 30px; color: floralwhite" href="${contextPath}/profile.jsp"><span class="glyphicon glyphicon-user"></span></a>
        </c:if>
        <c:if test="${!isAuthorized}">
            <a style="float:right; margin-top: 0.5%;" href="${contextPath}/login" class="btn btn-default" role="button">Log In</a>
            <a style="float:right; margin-top: 0.5%; margin-right: 0.3%;" href="${contextPath}/signup" class="btn btn-default" role="button">Sign Up</a>
        </c:if>


        <div class="navbar-form navbar-header" role="search" style="margin-left:30%;">
            <div class="form-group">
                <input style="color:black; width:300px;" id="search" type="text" placeholder="Search Posts by Tags..." class="form-control">
                <button class="btn btn-default" type="submit" onclick="searchByTag();"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></button>
            </div>
        </div>
    </div>
</nav>


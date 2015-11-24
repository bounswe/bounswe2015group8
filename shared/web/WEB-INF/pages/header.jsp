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


<body>
<nav class="navbar navbar-default navbar-static-top header">
    <div class="container-fluid"  style="background-color: darkblue">
        <div class="navbar-header">
            <a class="navbar-brand" href="${contextPath}/index.jsp">&#x2655; WeStory</a>
        </div>
        <sec:authorize access="isAuthenticated()">
            <a style="float:right; margin-top:0.5%;" href="${contextPath}/logout" class="btn btn-default" role="button">Log Out</a>
        </sec:authorize>
    </div>
</nav>


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
    <link rel="stylesheet" href="${contextPath}/static/css/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet" href="${contextPath}/static/css/styles.css">
    <link rel="stylesheet" href="${contextPath}/static/css/jquery-ui.min.css"/>
    <link rel="stylesheet" href="${contextPath}/static/css/advanced-search.css"/>

    <script src="${contextPath}/static/js/jquery-1.11.3.js"></script>
    <script src="${contextPath}/static/js/jquery-ui.min.js"></script>
    <script src="${contextPath}/static/js/moment.js"></script>
    <script src="${contextPath}/static/js/bootstrap/bootstrap.js"></script>
    <script src="${contextPath}/static/js/bootstrap/bootstrap-tokenfield.js"></script>
    <script src="${contextPath}/static/js/bootstrap/bootstrap-datetimepicker.min.js"></script>
    <script src="${contextPath}/static/js/notify.js"></script>
</head>

<script type="text/javascript">
    function searchByTag(){
        var tag = document.getElementById("search").value;
        if(tag == ""){
            $.notify("Please type something before searching it!", "danger");
            return false;
        }
        window.location.href = "${contextPath}/searchByTag/" + tag;
    }

    function open_search_modal(){
        $("#search_modal").modal('show');
    }

    function recommendHeritage(){
        $.ajax({
            url: "${contextPath}/recommendHeritage",
            type: "POST",
            success: function(response) {
                if(response.length == 0){
                    $.notify("We don't have any recommendation for you, sorry :(", "info");
                }
                else{
                    var html = "<div class='panel panel-success'>" +
                            "<div class='panel-heading'>" +
                            "<b><h class='panel-title' style='margin-left:0.5%'>Would you like to see posts of the following heritages?</h></b></div>";
                    console.log(response);
                    for(var i = 0; i < response.length; i++){
                        var rTitle = 'no title';
                        if(response[i]['title'] != null){
                            rTitle = response[i]['title'];
                        }
                        html += "<div class='row col-md-offset-1'>";
                        html += "<div class='row'>Name: <a style='color:black; font-weight:bold;' href='${contextPath}/show_posts/"+response[i]['id']+"'>"+rTitle+"</a></div>";
                        html += "<div class='row'>Description:<span style='max-width:300px;overflow:hidden;text-overflow:ellipsis'> "+response[i]['description']+"</span></div>";
                        html += "</div><br>";

                    }
                    html += "</div>";

                    $.notify.addStyle('recommend', {
                        html: "<div><span data-notify-html/></div>"


                    });
                    $.notify(html, {style: 'recommend', autoHide:false});
                }
            }
        });
    }

    function checkAchievements(){
        $.ajax({
            url: "${contextPath}/checkAchievements",
            type: "POST",
            success: function(response){
                if(Object.keys(response).length != 0) {
                    var isFirst = true;
                    var html = "<div class='panel panel-info'>" +
                            "<div class='panel-heading'>" +
                            "<b><h class='panel-title' style='margin-left:0.5%'>New Badges Earned!</h></b></div>";
                    for(var unlocked in response){
                        if(!isFirst){
                            html += "<hr>";
                        }
                        isFirst = false;
                        html += "<div class='row'> <div class='col-md-2'>";
                        html += "<img style='width: 150px; height: 150px;' src='${contextPath}/static/img/badges/"+ unlocked+response[unlocked] +".png'/>";
                        html += "</div> <div class='col-md-offset-3 col-md-7' style='margin-top:10%;'>";
                        html += "<p style='color:green; font-weight: bold;'> Achievement Unlocked! </p><p style='font-weight:bold;'>" + unlocked.toUpperCase() + " LEVEL: " + response[unlocked] + "</p>";
                        html += "</div></div>";
                    }
                    $.notify.addStyle('achievement', {
                        html: "<div><span data-notify-html/></div>"
                    });
                    $.notify(html, {style: 'achievement', autoHide:false});
                }
                setTimeout(checkAchievements, 10000);
            }
        });
    }

    $(document).ready(function(){
       checkAchievements();
    });
</script>

<body>
<%@ include file="/WEB-INF/pages/templates/advanced-search.html" %>

<sec:authorize access="isAuthenticated()">
    <sec:authentication var="principal" property="principal" />
    <sec:authorize var="isAuthorized" access="isAuthenticated()" />
</sec:authorize>

<nav class="navbar navbar-default navbar-static-top header">

    <div class="container-fluid"  style="background-color: darkblue">
        <div class="navbar-header">
            <c:if test="${isAuthorized}">
                <a class="navbar-brand" href="${contextPath}/feed" style="color: floralwhite">&#x2655; WeStory</a>
            </c:if>
            <c:if test="${!isAuthorized}">
                <a class="navbar-brand" href="${contextPath}/show_heritages" style="color: floralwhite">&#x2655; WeStory</a>
            </c:if>

        </div>
        <c:if test="${isAuthorized}">
            <a style="float:right; margin-top: 0.5%;" href="${contextPath}/logout" class="btn btn-default" role="button">Log Out</a>
            <a style="float:right; margin-top: 0.5%; margin-right: 0.5%; font-size: 30px; color: floralwhite" href="${contextPath}/profile/${principal.username}"><span class="glyphicon glyphicon-user"></span></a>
            <a class="btn btn-default" role="button" style="float:right; margin-top: 0.5%; margin-right: 0.5%;" onclick="recommendHeritage();">Recommend</a>
        </c:if>
        <c:if test="${!isAuthorized}">
            <a style="float:right; margin-top: 0.5%;" href="${contextPath}/login" class="btn btn-default" role="button">Log In</a>
            <a style="float:right; margin-top: 0.5%; margin-right: 0.3%;" href="${contextPath}/signup" class="btn btn-default" role="button">Sign Up</a>
        </c:if>


        <div class="navbar-form navbar-header" role="search" style="margin-left:30%;">
            <div class="form-group">
                <input style="color:black; width:300px;" id="search" type="text" placeholder="Search Posts by Tags..." class="form-control">
                <button class="btn btn-default" type="submit" onclick="searchByTag();"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></button>
                <button onclick="open_search_modal();" class="btn" style="background-color: #9a4ce2">Advanced Search</button>
            </div>
        </div>
    </div>
</nav>


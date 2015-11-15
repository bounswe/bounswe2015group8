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
<%@ include file="/WEB-INF/pages/header.jsp" %>
<!--
<div class="roundbox" style="padding-top: 40px">
<form action="${contextPath}/login" method="post" class="form-inline" id="form">
<label for="username"><b>Username: </b></label>
<input type="text" name="username" id="username" class="form-control"> <br>
<label for="password"><b>Password: </b></label>
<input type="password" name="password" id="password" class="form-control"> <br>
<button type="submit" class="btn btn-default">Log In</button>
<br>
<c:if test="${param['success'] == 'false'}">
    <span style="color:red;">Your username or password is wrong!</span>
</c:if>
</form>
</div>
-->
<!--
<form class="well col-xs-4 col-xs-offset-4" role="form">
<div class="input-group">

<span class="input-group-addon"><span class="glyphicon glyphicon-user" aria-hidden="true"></span></span>
<label class="sr-only" for="username">Username</label>
<input type="text" name="username" id="username" class="form-control" placeholder="Username">
</div>
<div class="input-group">
<span class="input-group-addon"><span class="glyphicon glyphicon-lock" aria-hidden="true"></span></span>
<label class="sr-only" for="password">Password</label>
<input type="text" name="password" id="password" class="form-control" placeholder="Password">
</div>
<div class="input-group">
<button type="submit" class="btn btn-default">Log In</button>
</div>
</form>
-->
<div class="container center-div ">
    <div class="well">
        <form action="/login" id="loginForm">
            <div class="form-group input-group">
                <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                <input class="form-control" type="text" name='username' placeholder="Username"/>
            </div>
            <div class="form-group input-group">
                <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                <input class="form-control" type="password" name='password' placeholder="Password"/>
            </div>
            <div class="form-group">
                <button type="button" class="btn btn-def btn-block">Login</button>
            </div>
            <div class="form-group text-center">
                <a href="${contextPath}/forget_password">Forgot Password</a>
            </div>
            <c:if test="${param['success'] == 'false'}">
                <span style="color:red;">Your username or password is wrong!</span>
            </c:if>
            <c:if test="${param['resetPassword'] == 'true'}">
                <span style="color:blue;">You have been sent the password reset link!</span>
            </c:if>
            <c:if test="${param['passwordChanged'] == 'true'}">
                <span style="color:green;">Your password is successfully changed!</span>
            </c:if>
        </form>
    </div>
</div>

<%@ include file="/WEB-INF/pages/footer.jsp" %>



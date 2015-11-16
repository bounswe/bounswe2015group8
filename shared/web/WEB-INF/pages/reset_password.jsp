<%--
  Created by IntelliJ IDEA.
  User: gokcan
  Date: 15.11.2015
  Time: 03:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ include file="/WEB-INF/pages/header.jsp" %>
<div class="roundbox" style="padding-top: 40px">
  <span style="margin-left: 2%; color: forestgreen;">Hello ${username}! Enter your new password please</span>
  <form action="${contextPath}/reset_password" method="post" class="form-inline" id="form">
    <input type="hidden" name="username" id="username" value="${username}" />
    <input type="password" name="password" id="password" class="form-control" /> <br>
    <button type="submit" class="btn btn-default">Reset</button><br>
  </form>
</div>
<%@ include file="/WEB-INF/pages/footer.jsp" %>
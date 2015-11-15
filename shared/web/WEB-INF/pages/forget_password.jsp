<%--
  Created by IntelliJ IDEA.
  User: gokcan
  Date: 15.11.2015
  Time: 01:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ include file="/WEB-INF/pages/header.jsp" %>
<div class="roundbox" style="padding-top: 40px">
  <form action="${contextPath}/forget_password" method="post" class="form-inline" id="form">
    <label for="username"><b>Username: </b></label>
    <input type="text" name="username" id="username" class="form-control"> <br>
    <button type="submit" class="btn btn-default">Reset</button><br>
  </form>
</div>
<%@ include file="/WEB-INF/pages/footer.jsp" %>
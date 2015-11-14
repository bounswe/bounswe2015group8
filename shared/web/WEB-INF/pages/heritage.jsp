<%--
  Created by IntelliJ IDEA.
  User: gokcan
  Date: 08.11.2015
  Time: 17:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ include file="/WEB-INF/pages/header.jsp" %>
<div class="roundbox">
    <form class="form-inline" id="form" action="${contextPath}/upload_heritage" method="POST"
          enctype="multipart/form-data">
        <b>Name: </b><input type="text" name="name" id="name" class="form-control"> <br>
        <b>Place: </b><input type="text" name="place" id="place" class="form-control"> <br>
        <b>Description: </b><input valign="top" style="height:50%; width:80%;" type="text" name="description" id="description" class="form-control"> <br>
        <input type="file" name="media" id="media" class="form-control"> <br>
        <button type="submit" class="btn-modified">POST!</button>
        <br>
    </form>
</div>
<%@ include file="/WEB-INF/pages/footer.jsp" %>





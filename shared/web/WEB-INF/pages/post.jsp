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


<%@ include file="/WEB-INF/pages/header.jsp" %>
<div class="roundbox">
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
                    <img height="3%" width="10%" src="/static/img/attachment.png"/>
                </label>
                <input style="display: none;" id="media" name="media" type="file"/>
                <button type="submit" class="btn-modified" style="float: right">POST!</button>
            </div>
        </form>
</div>
<%@ include file="/WEB-INF/pages/footer.jsp" %>


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


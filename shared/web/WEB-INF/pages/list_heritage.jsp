<%--
  Created by IntelliJ IDEA.
  User: gokcan
  Date: 08.11.2015
  Time: 17:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ include file="/WEB-INF/pages/header.jsp" %>
<script type="text/javascript">
    console.log("${allHeritages}");
</script>
<div id="section">
    <center>
        <div class="roundbox" style="width: 25%; margin-top: 15px;">

            Why don't you add a new cultural heritage?<br>
            <button type="button" class="btn-modified" onclick="window.location.href='${contextPath}/heritage'"> Add a cultural heritage!</button>
        </div>

    </center>

    <br>

    <c:forEach items="${allHeritages}" var="heritage">
        <div class="roundbox">
            <span><b>Name: </b> ${heritage.name}</span>
            <br>
            <span><b>Place: </b> ${heritage.place}</span>
            <br>
            <span><b>Description: </b> ${heritage.description}</span>
            <button type="button" class="btn-modified" style="float:right; margin-right:5%; border: solid#337ab7"
                    onclick="window.location.href='${contextPath}/post/${heritage.id}'">Add Post
            </button>
            <button type="button" class="btn-modified" style="float:right; margin-right:5%;"
                    onclick="window.location.href='${contextPath}/show_posts/${heritage.id}'">See Posts
            </button>
            <br>
            <span><b>Date posted: </b> ${heritage.postDate}</span>

        </div>
    </c:forEach>

</div>
<%@ include file="/WEB-INF/pages/footer.jsp" %>

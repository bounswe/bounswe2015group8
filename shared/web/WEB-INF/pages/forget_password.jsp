<%@ include file="/WEB-INF/pages/header.jsp" %>
<div class="page-content container" style="width: 450px">

    <div class="panel panel-default">
        <div class="panel-body">
    <form action="${contextPath}/forget_password" method="post" class="form-inline" id="form">
        <label for="username"><b>Username: </b></label>
        <input type="text" name="username" id="username" class="form-control"> <br>
        <div class="col-sm-offset-3 col-sm-4">
            <button type="submit" class="btn btn btn-default btn-block">Reset</button>
        </div>

        <c:if test="${param['userNotExists'] != null}">
            <span style="color:red;">There is no such user!</span>
        </c:if>
    </form>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/pages/footer.jsp" %>
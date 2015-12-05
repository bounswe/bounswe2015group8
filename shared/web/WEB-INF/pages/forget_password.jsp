<%@ include file="/WEB-INF/pages/header.jsp" %>
<div class="page-content container" style="width: 450px">

    <div class="panel panel-primary">
        <div class="panel-heading"><h>Reset password</h></div>
        <div class="panel-body">
    <form action="${contextPath}/forget_password" method="post" class="form-inline" id="form">
        <label for="username"><b>Username: </b></label>
        <input type="text" name="username" id="username" class="form-control">
        <button type="submit" class="btn btn btn-default">Reset</button>

        <c:if test="${param['userNotExists'] != null}">
            <span style="color:red;">There is no such user!</span>
        </c:if>
    </form>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/pages/footer.jsp" %>
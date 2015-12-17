<%@ include file="/WEB-INF/pages/header.jsp" %>
<div class="page-content container" style="width: 350px">
    <c:if test="${param['passwordChanged'] == 'true'}">
        <div class="alert alert-success" role="alert">Your password has been changed successfully!</div>
    </c:if>
    <c:if test="${param['resetPassword'] == 'true'}">
        <div class="alert alert-info" role="alert">Password reset link has been sent to your e-mail.</div>
    </c:if>
    <c:if test="${param['success'] == 'false'}">
        <div class="alert alert-danger" role="alert">Wrong username or password!</div>
    </c:if>
    <div class="panel panel-default">
        <div class="panel-body">
            <form action="${contextPath}/login" method="post" id="loginForm">
                <div class="form-group input-group">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                    <input class="form-control" type="text" name='username' placeholder="Username"/>
                </div>
                <div class="form-group input-group">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                    <input class="form-control" type="password" name='password' placeholder="Password"/>
                </div>
                <a href="${contextPath}/forget_password">Forgot your password?</a>
                <center>
                    <div class="form-group">
                        <button type="submit" class="btn btn-default">Login</button>
                    </div>
                </center>
            </form>
        </div>
    </div>
<%@ include file="/WEB-INF/pages/footer.jsp" %>
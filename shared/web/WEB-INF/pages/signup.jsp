<%@ include file="/WEB-INF/pages/header.jsp" %>
<div class="page-content container">

    <div class="panel panel-default">
        <div class="panel-body">
            <form action="${contextPath}/signup" method="post" id="signupForm">
                <div class="form-group input-group">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                    <input class="form-control" type="text" name='username' placeholder="Username"/>
                </div>
                <div class="form-group input-group">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
                    <input class="form-control" type="email" name='email' placeholder="E-mail"/>
                </div>
                <div class="form-group input-group">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                    <input class="form-control" type="password" name='password' placeholder="Password"/>
                </div>
                <div class="form-group input-group">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-ok"></i></span>
                    <input class="form-control" type="password" name='password2' placeholder="Confirm password"/>
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn btn-default btn-block">Sign Up</button>
                </div>
            </form>
        </div>
    </div>

<%@ include file="/WEB-INF/pages/footer.jsp" %>

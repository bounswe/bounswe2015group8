<%--
  Created by IntelliJ IDEA.
  User: Berk
  Date: 26.10.2015
  Time: 23:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ include file="/WEB-INF/pages/header.jsp" %>
    <div class="roundbox" style="padding-top: 60px">
        <form action="${contextPath}/login" method="post" class="form-inline" id="form">
            <center>
                <table>
                    <tr>
                        <td><label for="username">Username</label></td>
                        <td><input type="text" name="username" id="username" class="form-control"></td>
                    </tr>
                    <tr>
                        <td><label for="email">E-mail</label></td>
                        <td><input type="text" name="email" id="email" class="form-control"></td>
                    </tr>
                    <tr>
                        <td><label for="password">Password</label></td>
                        <td><input type="password" name="password" id="password" class="form-control"></td>
                    </tr>
                    <tr>
                        <td><label for="password2">Confirm password</label></td>
                        <td><input type="password" name="password2" id="password2" class="form-control"></td>
                    </tr>
                </table>
                <br>
                <button type="submit" class="btn-modified">Join!</button>
            </center>
        </form>
    </div>
<%@ include file="/WEB-INF/pages/footer.jsp" %>

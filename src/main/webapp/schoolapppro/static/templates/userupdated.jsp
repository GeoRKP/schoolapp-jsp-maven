<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Updated</title>
</head>
<body>
<c:choose>
    <c:when test="${requestScope.error eq true}">
        <p style="color: red;">Error: ${requestScope.message}</p>
    </c:when>
    <c:otherwise>
        <p>The user has been updated:</p>
        <ul>
            <li>ID: <strong>${requestScope.user.id}</strong></li>
            <li>Username: <strong>${requestScope.user.username}</strong></li>
            <li>Password: <strong>${requestScope.user.password}</strong></li>
        </ul>
    </c:otherwise>
</c:choose>

<p><a href="${pageContext.request.contextPath}/schoolapppro/menu">Back to Home</a></p>
</body>
</html>

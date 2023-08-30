<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Teacher Updated</title>
</head>
<body>
<c:choose>
    <c:when test="${requestScope.error eq true}">
        <p style="color: red;">Error: ${requestScope.message}</p>
    </c:when>
    <c:otherwise>
        <p>The teacher has been updated:</p>
        <ul>
            <li>ID: <strong>${requestScope.teacher.id}</strong></li>
            <li>SSN: <strong>${requestScope.teacher.ssn}</strong></li>
            <li>First Name: <strong>${requestScope.teacher.firstName}</strong></li>
            <li>Last Name: <strong>${requestScope.teacher.lastName}</strong></li>
            <li>Speciality ID: <strong>${requestScope.teacher.specialityId}</strong></li>
            <li>User ID: <strong>${requestScope.teacher.userId}</strong></li>
        </ul>
    </c:otherwise>
</c:choose>

<p><a href="${pageContext.request.contextPath}/schoolapppro/menu">Back to Home</a></p>
</body>
</html>

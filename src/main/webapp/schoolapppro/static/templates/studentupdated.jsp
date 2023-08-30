<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Student Updated</title>
</head>
<body>
<c:choose>
    <c:when test="${requestScope.error eq true}">
        <p style="color: red;">Error: ${requestScope.message}</p>
    </c:when>
    <c:otherwise>
        <p>The student has been updated:</p>
        <ul>
            <li>ID: <strong>${requestScope.student.id}</strong></li>
            <li>First Name: <strong>${requestScope.student.firstName}</strong></li>
            <li>Last Name: <strong>${requestScope.student.lastName}</strong></li>
            <li>Gender: <strong>${requestScope.student.gender}</strong></li>
            <li>Birthdate: <strong>${requestScope.student.birthdate}</strong></li>
            <li>City ID: <strong>${requestScope.student.cityId}</strong></li>
            <li>User ID: <strong>${requestScope.student.userId}</strong></li>
        </ul>
    </c:otherwise>
</c:choose>

<p><a href="${pageContext.request.contextPath}/schoolapppro/menu">Back to Home</a></p>
</body>
</html>

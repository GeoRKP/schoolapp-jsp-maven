<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Meeting Updated</title>
</head>
<body>
<c:choose>
    <c:when test="${requestScope.error eq true}">
        <p style="color: red;">Error: ${requestScope.message}</p>
    </c:when>
    <c:otherwise>
        <p>The meeting has been updated:</p>
        <ul>
            <li>Teacher ID: <strong>${requestScope.meeting.teacherId}</strong></li>
            <li>Student ID: <strong>${requestScope.meeting.studentId}</strong></li>
            <li>Meeting Date: <strong>${requestScope.meeting.meetingDate}</strong></li>
            <li>Meeting Room: <strong>${requestScope.meeting.meetingRoom}</strong></li>
        </ul>
    </c:otherwise>
</c:choose>

<p><a href="${pageContext.request.contextPath}/schoolapppro/menu">Back to Home</a></p>
</body>
</html>

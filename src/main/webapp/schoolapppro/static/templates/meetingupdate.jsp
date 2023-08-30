<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <link rel="stylesheet" href="/css/login.css">
    <title>Update Meeting</title>
</head>
<body>
<div class="container-lg">
    <div class="container d-flex justify-content-center align-items-center min-vh-100">
        <div class="col-md-6 right-box">
            <a href="${pageContext.request.contextPath}/shcoolapppro/menu" class="btn btn-primary" > Menu </a>

            <div class="row align-items-center">
                <form method="post" action="${pageContext.request.contextPath}/shcoolapppro/meetingupdate">
                    <div class="input-group mb-3">
                        <input name="teacherid" type="text" class="form-control form-control-lg bg-light fs-6" value="${requestScope.meetingid}">
                    </div>
                    <div class="input-group mb-3">
                        <input name="studentid" type="text" class="form-control form-control-lg bg-light fs-6" value="${requestScope.studentid}">
                    </div>
                    <div class="input-group mb-3">
                        <input name="date" type="date" class="form-control form-control-lg bg-light fs-6" value="${requestScope.meetingdate}">
                    </div>
                    <div class="input-group mb-3">
                        <input name="meetingroom" type="text" class="form-control form-control-lg bg-light fs-6" value="${requestScope.meetingroom}">
                    </div>
                    <div class="input-group mb-2">
                        <button type="submit" class="btn btn-lg btn-primary w-100 fs-6">Update Meeting</button>
                    </div>
                </form>
            </div>
    </div>
</div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
</body>
</html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <title>Users List</title>
</head>
<body>
<div class="container-lg">
    <div class="table-responsive-lg">
        <c:if test="error">
            <p>${requestScope.message}</p>
        </c:if>
        <a href="${pageContext.request.contextPath}/shcoolapppro/menu" class="btn btn-primary" > Menu </a>
        <table class="table table-bordered caption-top">
            <caption>Search Results</caption>
            <thead>
            <tr>
                <th scope="col">Id</th>
                <th scope="col">Username</th>
                <th scope="col">Password</th>
                <th scope="col">Update</th>
                <th scope="col">Delete</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="user" items="${requestScope.users}">
                <tr>
                    <th scope="row"> ${user.id} </th>
                    <td> ${user.username} </td>
                    <td> ${user.password} </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/schoolapppro/static/templates/userupdate.jsp?id=${user.id}&username=${user.username}&password=${user.password}" class="btn btn-primary" > Update
                        </a>
                    </td>
                    <td>
                        <a  class="btn btn-primary" href="${pageContext.request.contextPath}/schoolapppro/userdelete?id=${user.id}&username=${user.username}}"
                            onclick="return confirm('Are you sure you want to delete this user?')"> Delete
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
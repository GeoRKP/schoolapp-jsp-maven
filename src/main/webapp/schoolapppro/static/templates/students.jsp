<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <title>Students List</title>
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
                <th scope="col">Firstname</th>
                <th scope="col">Lastname</th>
                <th scope="col">Gender</th>
                <th scope="col">Birthdate</th>
                <th scope="col">City Id</th>
                <th scope="col">User Id</th>
                <th scope="col">Update</th>
                <th scope="col">Delete</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="student" items="${requestScope.students}">
                <tr>
                    <th scope="row"> ${student.id} </th>
                    <td> ${student.firstname} </td>
                    <td> ${student.lastname} </td>
                    <td> ${student.gender} </td>
                    <td> ${student.birthdate} </td>
                    <td> ${student.cityId} </td>
                    <td> ${student.userId} </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/schoolapppro/studentupdate?id=${student.id}&firstname=${firstname}&lastname=${student.lastname}&gender=${student.gender}&birthdate=${student.birthdate}&cityid=${student.cityId}&userid=${student.userId}" class="btn btn-primary" > Update
                        </a>
                    </td>
                    <td>
                        <a  class="btn btn-primary" href="${pageContext.request.contextPath}/schoolapppro/studentdelete?id=${student.id}&lastname=${student.lastname}}"
                            onclick="return confirm('Are you sure you want to delete this student?')">Delete
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
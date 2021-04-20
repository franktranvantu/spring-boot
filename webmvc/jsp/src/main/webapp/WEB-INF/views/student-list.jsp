<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<html>
<head>
    <title>Student Management</title>
    <link rel="stylesheet" href="${contextPath}/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${contextPath}/student/student.css">
</head>
<body>
    <h1>Student Management</h1>
    <form action="create-student">
        <button>Create Student</button>
    </form>
    <table>
        <thead>
            <tr>
                <td>Id</td>
                <td>Name</td>
                <td>Email</td>
                <td>Birthday</td>
                <td colspan="2">Actions</td>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="student" items="${students}">
                <tr>
                    <td>${student.id}</td>
                    <td>${student.name}</td>
                    <td>${student.email}</td>
                    <td>${student.dob}</td>
                    <td><a href="update-student/${student.id}">Update</a></td>
                    <td><a href="delete-student/${student.id}">Delete</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <script src="${contextPath}/webjars/jquery/jquery.min.js"></script>
    <script src="${contextPath}/webjars/bootstrap/js/bootstrap.min.js"></script>
    <script src="${contextPath}/student/student.js"></script>
</body>
</html>

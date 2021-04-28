<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<html>
<head>
    <title>Student Management</title>
    <link rel="stylesheet" href="${contextPath}/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${contextPath}/webjars/font-awesome/css/all.css">
    <link rel="stylesheet" href="${contextPath}/common.css">
    <link rel="stylesheet" href="${contextPath}/student/student.css">
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <a class="navbar-brand" href="${contextPath}">Enrolment Management</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" href="${contextPath}/course">Courses <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${contextPath}/student">Students</a>
                </li>
            </ul>
            <form class="form-inline my-2 my-lg-0">
                <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
            </form>
        </div>
    </nav>
    <c:if test="${not empty result}">
        <div class="alert alert-${result.status == 'SUCCESS' ? 'success' : 'danger'} ml-auto position-absolute message" role="alert">
            ${result.message}
        </div>
    </c:if>
    <div class="container">
        <div class="card">
            <div class="card-header bg-transparent d-flex justify-content-between align-items-center">
                <h4 class="card-title mb-0">All Students List</h4>
                <a href="${contextPath}/student/create-student" class="btn btn-primary"><i class="fas fa-user-plus"></i> Add new</a>
            </div>
            <div class="card-body">
                <table class="table">
                    <thead class="thead-dark">
                        <tr class="text-center">
                            <th>Id</th>
                            <th>Name</th>
                            <th>Email</th>
                            <th>Birthday</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="student" items="${students}">
                            <tr>
                                <td class="text-center">${student.id}</td>
                                <td>${student.name}</td>
                                <td>${student.email}</td>
                                <td class="text-center"><spring:eval expression="student.dob" /></td>
                                <td class="text-center">
                                    <a href="${contextPath}/student/update-student/${student.id}" class="btn btn-sm btn-primary"><i class="fas fa-pencil-alt"></i></a>
                                    <a href="#" class="btn btn-sm btn-danger delete-student-link" data-id="${student.id}"><i class="fas fa-trash"></i></a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

        <input type="hidden" id="student-id-to-delete" />

        <!-- Delete Student Modal -->
        <div class="modal fade" id="delete-student-modal" tabindex="-1" role="dialog" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Delete Student</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        Are you sure you want to delete student permanently?
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-dark" data-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-danger" id="delete-student-btn">Delete</button>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="${contextPath}/webjars/jquery/jquery.min.js"></script>
    <script src="${contextPath}/webjars/bootstrap/js/bootstrap.min.js"></script>
    <script src="${contextPath}/common.js"></script>
    <script src="${contextPath}/student/student-list.js"></script>
</body>
</html>

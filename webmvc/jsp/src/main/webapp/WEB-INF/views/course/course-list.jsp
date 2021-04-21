<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<html>
<head>
    <title>Course Management</title>
    <link rel="stylesheet" href="${contextPath}/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${contextPath}/index.css">
    <link rel="stylesheet" href="${contextPath}/course/course.css">
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
    <div class="container">
        <h1 class="text-center display-1">Course Management</h1>
        <form action="${contextPath}/course/create-course">
            <div class="form-group row">
                <div class="col-sm-12 text-center">
                    <button class="btn btn-dark">Create Course</button>
                </div>
            </div>
        </form>
        <table class="table">
            <thead class="thead-dark">
                <tr class="text-center">
                    <th>Id</th>
                    <th>Name</th>
                    <th colspan="2">Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="course" items="${courses}">
                    <tr>
                        <td class="text-center">${course.id}</td>
                        <td>${course.name}</td>
                        <td class="text-center"><a href="${contextPath}/course/update-course/${course.id}">Update</a></td>
                        <td class="text-center"><a href="#" class="delete-course-link" data-id="${course.id}">Delete</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <input type="hidden" id="course-id-to-delete" />

        <!-- Delete Course Modal -->
        <div class="modal fade" id="delete-course-modal" tabindex="-1" role="dialog" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Delete Course</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        Are you sure you want to delete course permanently?
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-dark" data-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-danger" id="delete-course-btn">Delete</button>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="${contextPath}/webjars/jquery/jquery.min.js"></script>
    <script src="${contextPath}/webjars/bootstrap/js/bootstrap.min.js"></script>
    <script src="${contextPath}/index.js"></script>
    <script src="${contextPath}/course/course.js"></script>
</body>
</html>

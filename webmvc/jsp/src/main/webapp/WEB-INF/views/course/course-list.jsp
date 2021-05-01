<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<html>
<head>
    <title>Course Management</title>
    <link rel="stylesheet" href="${contextPath}/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${contextPath}/webjars/datatables/css/jquery.dataTables.css">
    <link rel="stylesheet" href="${contextPath}/webjars/font-awesome/css/all.css">
    <link rel="stylesheet" href="${contextPath}/common.css">
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
            <form class="form-inline my-2 my-lg-0" action="logout" method="post">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit"><i class="fas fa-sign-out-alt"></i> Logout</button>
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
                <h4 class="card-title mb-0">All Courses List</h4>
                <a href="${contextPath}/course/create-course" class="btn btn-primary"><i class="fas fa-user-plus"></i> Add new</a>
            </div>
            <div class="card-body">
                <table id="course" class="table">
                    <thead class="thead-dark">
                        <tr class="text-center">
                            <th>Id</th>
                            <th>Name</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="course" items="${courses}">
                        <tr>
                            <td class="text-center">${course.id}</td>
                            <td>${course.name}</td>
                            <td class="text-center">
                                <form action="${contextPath}/course/update-course" method="post">
                                    <input type="hidden" name="id" value="${course.id}" />
                                    <button class="btn btn-sm btn-primary"><i class="fas fa-pencil-alt"></i></button>
                                    <a href="" class="btn btn-sm btn-danger delete-student-button" data-id="${course.id}"><i class="fas fa-trash"></i></a>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

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
                        <button type="button" class="btn btn-danger" id="confirm-delete-course">Delete</button>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="${contextPath}/webjars/jquery/jquery.min.js"></script>
    <script src="${contextPath}/webjars/bootstrap/js/bootstrap.min.js"></script>
    <script src="${contextPath}/webjars/datatables/js/jquery.dataTables.min.js"></script>
    <script src="${contextPath}/common.js"></script>
    <script src="${contextPath}/course/course-list.js"></script>
</body>
</html>

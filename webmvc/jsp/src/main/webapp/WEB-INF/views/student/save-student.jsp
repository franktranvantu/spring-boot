<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<html>
<head>
    <title>${action} Student</title>
    <link rel="stylesheet" href="${contextPath}/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${contextPath}/webjars/bootstrap-datepicker/css/bootstrap-datepicker.min.css">
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
    <div class="alert alert-danger ml-auto position-absolute message" role="alert">
        ${result.message}
    </div>
    <div class="container save-student">
        <h1 class="text-center mb-4">${action} Student</h1>
        <form:form action="${contextPath}/student/save-student" method="post" modelAttribute="student" id="save-student">
            <form:hidden path="id" />
            <div class="form-group row">
                <label for="name" class="col-sm-2 col-form-label">Name</label>
                <div class="col-sm-10">
                    <form:input path="name" class="form-control" id="name" aria-describedby="name" />
                    <div id="name" class="invalid-feedback">
                        Name is mandatory
                    </div>
                    <div id="name" class="invalid-feedback">
                        <form:errors path="name" />
                    </div>
                </div>
            </div>
            <div class="form-group row">
                <label for="email" class="col-sm-2 col-form-label">Email</label>
                <div class="col-sm-10">
                    <form:input path="email" class="form-control" id="email" aria-describedby="email" />
                    <div id="email" class="invalid-feedback">
                        Email is mandatory
                    </div>
                    <div id="email" class="invalid-feedback">
                        <form:errors path="email" />
                    </div>
                </div>
            </div>
            <div class="form-group row">
                <label for="dob" class="col-sm-2 col-form-label">Birthday</label>
                <div class="col-sm-10">
                    <form:input path="dob" class="form-control" id="dob" placeholder="dd/mm/yyyy" />
                </div>
            </div>
            <div class="form-group row">
                <div class="col-sm-12 text-right">
                    <button type="button" class="btn btn-secondary" id="back">Back</button>
                    <button type="submit" class="btn btn-dark">${action}</button>
                </div>
            </div>
        </form:form>
    </div>

    <script src="${contextPath}/webjars/lodash/lodash.min.js"></script>
    <script src="${contextPath}/webjars/jquery/jquery.min.js"></script>
    <script src="${contextPath}/webjars/bootstrap/js/bootstrap.min.js"></script>
    <script src="${contextPath}/webjars/bootstrap-datepicker/js/bootstrap-datepicker.min.js"></script>
    <script src="${contextPath}/common.js"></script>
    <script src="${contextPath}/student/save-student.js"></script>
</body>
</html>

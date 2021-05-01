<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<html>
<head>
    <title>${action} Enrolment</title>
    <link rel="stylesheet" href="${contextPath}/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${contextPath}/common.css">
    <link rel="stylesheet" href="${contextPath}/enrolment/enrolment.css">
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
        <div class="alert alert-danger ml-auto position-absolute message" role="alert">
            ${result.message}
        </div>
    </c:if>

    <div class="container save-enrolment">
        <h1 class="text-center mt-4 mb-4">${action} Enrolment</h1>
        <form:form action="${contextPath}/save-enrolment" method="post" modelAttribute="enrolment" id="save-enrolment">
            <form:hidden path="id" />
            <div class="form-group row">
                <label for="course" class="col-sm-2 col-form-label">Course</label>
                <div class="col-sm-10">
                    <form:select path="course" class="form-control" id="course" aria-describedby="course">
                        <c:forEach var="course" items="${courses}">
                            <option value="${course.id}" ${enrolment.course.id == course.id ? 'selected' : ''}>${course.name}</option>
                        </c:forEach>
                    </form:select>
                    <div id="course" class="invalid-feedback">
                        Course is mandatory
                    </div>
                    <div id="course" class="invalid-feedback">
                        <form:errors path="course" />
                    </div>
                </div>
            </div>
            <div class="form-group row">
                <label for="student" class="col-sm-2 col-form-label">Student</label>
                <div class="col-sm-10">
                    <form:select path="student" class="form-control" id="student" aria-describedby="student">
                        <c:forEach var="student" items="${students}">
                            <option value="${student.id}" ${enrolment.student.id == student.id ? 'selected' : ''}>${student.name}</option>
                        </c:forEach>
                    </form:select>
                    <div id="student" class="invalid-feedback">
                        Student is mandatory
                    </div>
                    <div id="student" class="invalid-feedback">
                        <form:errors path="student" />
                    </div>
                </div>
            </div>
            <div class="form-group row">
                <label for="semester" class="col-sm-2 col-form-label">Semester</label>
                <div class="col-sm-10">
                    <form:input path="semester" class="form-control" id="semester" aria-describedby="semester"/>
                    <div id="semester" class="invalid-feedback">
                        Semester is mandatory
                    </div>
                    <div id="semester" class="invalid-feedback">
                        <form:errors path="semester" />
                    </div>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-sm-12 text-right">
                    <button type="button" class="btn btn-secondary" id="back">Back</button>
                    <button type="submit" class="btn btn-primary">${action}</button>
                </div>
            </div>
        </form:form>
    </div>

    <script src="${contextPath}/webjars/jquery/jquery.min.js"></script>
    <script src="${contextPath}/webjars/lodash/lodash.min.js"></script>
    <script src="${contextPath}/webjars/bootstrap/js/bootstrap.min.js"></script>
    <script src="${contextPath}/common.js"></script>
    <script src="${contextPath}/enrolment/save-enrolment.js"></script>
</body>
</html>

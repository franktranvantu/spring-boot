<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<html>
<head>
    <title>${action} Student</title>
    <link rel="stylesheet" href="${contextPath}/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${contextPath}/student/student.css">
</head>
<body>
    <div class="container save-student">
        <h1 class="text-center mb-4">${action} Student</h1>
        <form:form action="${contextPath}/student/save-student" method="post" modelAttribute="student">
            <form:hidden path="id" />
            <div class="form-group row">
                <label for="name" class="col-sm-2 col-form-label">Name</label>
                <div class="col-sm-10">
                    <form:input path="name" class="form-control" id="name" />
                </div>
            </div>
            <div class="form-group row">
                <label for="email" class="col-sm-2 col-form-label">Email</label>
                <div class="col-sm-10">
                    <form:input path="email" class="form-control" id="email" />
                </div>
            </div>
            <div class="form-group row">
                <label for="dob" class="col-sm-2 col-form-label">Birthday</label>
                <div class="col-sm-10">
                    <form:input path="dob" class="form-control" id="dob" />
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

    <script src="${contextPath}/webjars/jquery/jquery.min.js"></script>
    <script src="${contextPath}/webjars/bootstrap/js/bootstrap.min.js"></script>
    <script src="${contextPath}/student/student.js"></script>
</body>
</html>

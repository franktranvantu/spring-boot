<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<html>
<head>
    <title>${action} Student</title>
    <link rel="stylesheet" href="${contextPath}/student/student.css">
</head>
<body>
    <h1>${action} Student</h1>
    <form:form action="${contextPath}/save-student" method="post" modelAttribute="student">
        <form:hidden path="id" />
        <p>
            <label for="name">Name:</label>
            <from:input path="name" id="name" />
        </p>
        <p>
            <label for="email">Email:</label>
            <from:input path="email" id="email" />
            <span>${emailError}</span>
        </p>
        <p>
            <label for="dob">Birthday:</label>
            <from:input path="dob" id="dob" />
        </p>
        <p>
            <button>${action}</button>
            <button type="button" onclick="backToHome()">Back</button>
        </p>
    </form:form>

    <script src="${contextPath}/student/student.js"></script>
</body>
</html>

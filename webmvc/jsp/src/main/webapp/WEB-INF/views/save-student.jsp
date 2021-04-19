<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>{action} Student</title>
</head>
<body>
    <h1 align="center">{action} Student</h1>
    <div align="center">
        <form:form action="save-student" method="post" modelAttribute="student">
            <p>
                <label for="name">Name:</label>
                <from:input path="name" id="name" />
            </p>
            <p>
                <label for="email">Email:</label>
                <from:input path="email" id="email" />
            </p>
            <p>
                <label for="dob">Birthday:</label>
                <from:input path="dob" id="dob" />
            </p>
            <p>
                <button>{action}</button>
            </p>
        </form:form>
    </div>
</body>
</html>

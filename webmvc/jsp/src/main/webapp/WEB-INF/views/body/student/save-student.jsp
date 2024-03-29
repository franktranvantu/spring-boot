<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
  <title>${action} Student</title>
</head>
<body>
  <c:if test="${not empty result}">
    <div class="alert alert-danger ml-auto position-absolute message" role="alert">
        ${result.message}
    </div>
  </c:if>
  <div class="container w-25">
    <h1 class="text-center mt-4 mb-4">${action} Student</h1>
    <form:form action="${contextPath}/student/save-student" method="post" modelAttribute="student" id="save-student">
      <form:hidden path="id"/>
      <div class="form-group row">
        <label for="name" class="col-sm-3 col-form-label">Name <span class="text-danger">*</span></label>
        <div class="col-sm">
          <c:set var="nameError"><form:errors path="name"/></c:set>
          <c:choose>
            <c:when test="${empty nameError}">
              <form:input path="name" class="form-control" id="name" aria-describedby="name"/>
              <div id="name" class="invalid-feedback">
                Name is mandatory
              </div>
            </c:when>
            <c:when test="${not empty nameError}">
              <form:input path="name" class="form-control is-invalid" id="name" aria-describedby="name"/>
              <div id="name" class="invalid-feedback">
                Name is mandatory
              </div>
            </c:when>
          </c:choose>
        </div>
      </div>
      <div class="form-group row">
        <label for="email" class="col-sm-3 col-form-label">Email <span class="text-danger">*</span></label>
        <div class="col-sm">
          <c:set var="emailError"><form:errors path="email"/></c:set>
          <c:choose>
            <c:when test="${empty emailError}">
              <form:input path="email" class="form-control" id="email" aria-describedby="email"/>
              <div id="email" class="invalid-feedback">
                Email is mandatory
              </div>
            </c:when>
            <c:when test="${not empty emailError}">
              <form:input path="email" class="form-control is-invalid" id="email" aria-describedby="email"/>
              <div id="email" class="invalid-feedback">
                Email is mandatory
              </div>
            </c:when>
          </c:choose>
        </div>
      </div>
      <div class="form-group row">
        <label for="dob" class="col-sm-3 col-form-label">Birthday</label>
        <div class="col-sm">
          <form:input path="dob" class="form-control" id="dob" placeholder="dd/mm/yyyy"/>
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
</body>
</html>

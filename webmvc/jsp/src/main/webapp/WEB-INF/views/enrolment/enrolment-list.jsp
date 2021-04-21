<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<html>
<head>
  <title>Enrolment Management</title>
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
        <li class="nav-item active">
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
    <h1 class="text-center display-1">Enrolment Management</h1>
    <form action="${contextPath}/create-enrolment">
      <div class="form-group row">
        <div class="col-sm-12 text-center">
          <button class="btn btn-dark">Create Enrolment</button>
        </div>
      </div>
    </form>
    <table class="table">
      <thead class="thead-dark">
      <tr class="text-center">
        <th>Id</th>
        <th>Course</th>
        <th>Student</th>
        <th>Semester</th>
        <th colspan="2">Actions</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach var="enrolment" items="${enrolments}">
        <tr>
          <td class="text-center">${enrolment.id}</td>
          <td>${enrolment.course.name}</td>
          <td>${enrolment.student.name}</td>
          <td>${enrolment.semester}</td>
          <td class="text-center"><a href="${contextPath}/update-enrolment/${enrolment.id}">Update</a></td>
          <td class="text-center"><a href="#" class="delete-enrolment-link" data-id="${enrolment.id}">Delete</a></td>
        </tr>
      </c:forEach>
      </tbody>
    </table>

    <input type="hidden" id="enrolment-id-to-delete" />

    <!-- Delete Enrolment Modal -->
    <div class="modal fade" id="delete-enrolment-modal" tabindex="-1" role="dialog" aria-hidden="true">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">Delete Enrolment</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
            Are you sure you want to delete enrolment permanently?
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-dark" data-dismiss="modal">Close</button>
            <button type="button" class="btn btn-danger" id="delete-enrolment-btn">Delete</button>
          </div>
        </div>
      </div>
    </div>
  </div>

  <script src="${contextPath}/webjars/jquery/jquery.min.js"></script>
  <script src="${contextPath}/webjars/bootstrap/js/bootstrap.min.js"></script>
  <script src="${contextPath}/common.js"></script>
  <script src="${contextPath}/enrolment/enrolment.js"></script>
</body>
</html>
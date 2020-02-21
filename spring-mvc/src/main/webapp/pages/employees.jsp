<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Employee List</title>
</head>
<body>
    <form action="create-employee" method="post">
        <h3>Create employee</h3>
        <label>Name</label>
        <input type="text" name="name" />
        <label>Team</label>
        <input type="text" name="team" />
        <button type="submit">Create</button>
    </form>
    <hr />
    <form action="find-employee-by-name">
        <h3>Search by name</h3>
        <label>Name</label>
        <input type="text" name="name" />
        <button type="submit">Search</button>
    </form>
    <form action="update-employee" method="post">
        <h3>Update employee</h3>
        <label>Name</label>
        <input type="text" name="name" readonly value="${employee.name}" />
        <label>Team</label>
        <input type="text" name="team" value="${employee.team}" />
        <button type="submit">Update</button>
    </form>
    <form action="delete-employee" method="post">
        <h3>Delete employee</h3>
        <label>Name</label>
        <input type="text" name="name" readonly value="${employee.name}" />
        <label>Team</label>
        <input type="text" name="team" readonly value="${employee.team}" />
        <button type="submit">Delete</button>
    </form>
    <hr />
    <form action="find-employees-by-team">
        <h3>Search by team</h3>
        <label>Team</label>
        <input type="text" name="team" />
        <button type="submit">Search</button>
    </form>
    <hr />
    <h3>Employee list</h3>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Team</th>
            </tr>
        </thead>
        <c:if test="${!empty employees}">
            <tbody>
                <c:forEach items="${employees}" var="employee">
                    <tr>
                        <td>${employee.id}</td>
                        <td>${employee.name}</td>
                        <td>${employee.team}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </c:if>
    </table>
</body>
</html>
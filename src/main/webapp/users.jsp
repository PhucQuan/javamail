<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Admin - SQL Gateway App</title>
    <link rel="stylesheet" href="styles/main.css" type="text/css" />
</head>
<body>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
    <div class="nav">
        <a href="emailList">Email List</a>
        <a href="sqlGateway">SQL Gateway</a>
        <a href="userAdmin">User Admin</a>
    </div>

    <div class="container">
        <h1>User Admin</h1>
        <h2>Users</h2>
        
        <table>
            <thead>
                <tr>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Email</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="user" items="${users}">
                <tr>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.email}</td>
                    <td>
                        <form action="userAdmin" method="post" style="display:inline;">
                            <input type="hidden" name="action" value="display_user">
                            <input type="hidden" name="email" value="${user.email}">
                            <input type="submit" value="Update">
                        </form>
                        <form action="userAdmin" method="post" style="display:inline;">
                            <input type="hidden" name="action" value="delete_user">
                            <input type="hidden" name="email" value="${user.email}">
                            <input type="submit" value="Delete" 
                                   onclick="return confirm('Are you sure you want to delete this user?');">
                        </form>
                    </td>
                </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>

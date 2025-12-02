<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update User - SQL Gateway App</title>
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
        <h1>Update User</h1>
        
        <form action="userAdmin" method="post">
            <input type="hidden" name="action" value="update_user">
            
            <label>Email</label>
            <input type="email" name="email" value="${user.email}" readonly>
            
            <label>First Name</label>
            <input type="text" name="firstName" value="${user.firstName}" required>
            
            <label>Last Name</label>
            <input type="text" name="lastName" value="${user.lastName}" required>
            
            <input type="submit" value="Update User">
        </form>
        
        <form action="userAdmin" method="post">
            <input type="hidden" name="action" value="display_users">
            <input type="submit" value="View All Users">
        </form>
    </div>
</body>
</html>

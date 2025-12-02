<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>Murach's Email List</title>
    <link rel="stylesheet" href="styles/main.css" type="text/css" />
</head>

<body>
    <div class="nav">
        <a href="emailList">Email List</a>
        <a href="sqlGateway">SQL Gateway</a>
        <a href="userAdmin">User Admin</a>
    </div>
    
    <h1>Join our email list</h1>
    <p>To join our email list, enter your name and
        email address below.</p>

    <p style="color: red;">${message}</p>

    <form action="emailList" method="post">
        <input type="hidden" name="action" value="add">

        <label>Email:</label>
        <input type="email" name="email" value="${user.email}" required><br>

        <label>First Name:</label>
        <input type="text" name="firstName" value="${user.firstName}" required><br>

        <label>Last Name:</label>
        <input type="text" name="lastName" value="${user.lastName}" required><br>

        <label>&nbsp;</label>
        <input type="submit" value="Join Now" class="margin_left">
    </form>
</body>

</html>
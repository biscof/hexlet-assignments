<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- BEGIN -->
<!DOCTYPE html>
<html>
<head>
    <title>User</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
<table class="table">
    <thead>
    <tr>
        <th scope="col">id</th>
        <th scope="col">firstName</th>
        <th scope="col">lastName</th>
        <th scope="col">email</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>${user.get("id")}</td>
        <td>${user.get("firstName")}</td>
        <td>${user.get("lastName")}</td>
        <td>${user.get("email")}</td>
    </tr>
    </tbody>
</table>
<a href="/users/delete?id=${user.get("id")}">Delete user</a>
</body>
</html>
<!-- END -->

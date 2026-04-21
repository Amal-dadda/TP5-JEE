<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset='ISO-8859-1' >
<title>Insert title here</title>
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">

</head>

<body>

<div class="d-grid gap-2 col-6 mx-auto">
 <h3>Log in</h3>

 <form action="${pageContext.request.contextPath}/app" method = "POST" >
 
    <legend>Log in: </legend>
    
    <input type="hidden" name="action" value="login">
    <div class="mb-1">
      <label for="disabledTextInput" class="form-label">Username: </label>
      <input type="text" class="form-control"  name = "username" required>
    </div>
    
    <div class="mb-1">
      <label for="disabledTextInput" class="form-label">Password: </label>
      <input type="password" class="form-control"  name = "password" required>
    </div>
    
    
    <div class="mb-1">
    <a href="${pageContext.request.contextPath}/register.jsp">Register</a>
    </div>
    
    <button type="submit" class="btn btn-success">Login</button>
</form>
 
 
 </div>
 
 
</body>

</html>








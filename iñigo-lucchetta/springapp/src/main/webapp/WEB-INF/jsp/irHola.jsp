<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Hello Spring MVC</title>
<script type="text/javascript" src="javascript.js">

function myFunction(){
	alert("hola");
	return 0;
}

    

</script>
</head>
<body>
<form name="formulario" method="POST" onsubmit="return myFunction()" action="http://localhost:8080/springapp/">
  First name:<br>
  <input type="text" id="name" name="firstname" value="">
  <br>
  Last name:<br>
  <input type="text" id="last" name="lastname" value="">
  <br><br>
  <input type="submit" value="Submit">
</form> 


hola soy llamado desde un controller
</body>
</html>
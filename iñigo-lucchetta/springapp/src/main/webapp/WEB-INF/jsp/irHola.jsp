<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Hello Spring MVC</title>

<script >

function myFunction(){
	var name = document.getElementById("name").value;
	var password = document.getElementById("pass")
	alert("hasta aca anda");
	
	
}

$(function() {
    $('#btnCallService').click(function() {
        $.ajax({
            type: 'POST',
            url: 'http://localhost:8080/springapp/autenticacion',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            success: function(response) {
                $('#lblData').html(JSON.stringify(response));
            },
            error: function(error) {
                console.log(error);
            }
        });
    });
});
    

</script>
</head>
<body>
<form name="formulario" method="POST" onsubmit="return myFunction()" action="http://localhost:8080/springapp/">
  User name:<br>
  <input type="text" id="name" name="firstname" value="">
  <br>
  Password:<br>
  <input type="text" id="pass" name="lastname" value="">
  <br><br>
  <input type="submit" id="btnCallService" value="Submit">
</form> 


hola soy llamado desde un controller
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Hello Spring MVC</title>
</head>
<body>

<form  id="myForm" name="formulario" >
  First name:
  <br>
  <input type="text" id="firstname" name="firstname" >
  <br>
  Last name:
  <br>
  <input type="text" id="lastname" name="lastname" >
  <br><br>
  <input type="submit" value="submit">
</form> 

<script src="http://code.jquery.com/jquery-2.2.4.min.js" integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="
       crossorigin="anonymous"></script>
  <script type="text/javascript">
  document.getElementById('myForm').addEventListener('submit', function (event) {
      event.preventDefault();
      var firstName = document.getElementsByName('firstname')[0].value;
      var lastName = document.getElementsByName('lastname')[0].value;
      
      $.ajax({
          type: 'POST',
          url: 'http://localhost:8080/springapp/usuarios',
          data: {
            nombre:"fran"
            
            },
            success: function (respone) {
              // en response vuelve la respues de la API.
              console.log(response);
            },
            dataType:'JSON'
          });
        });
      </script>
    </body>

    </html>
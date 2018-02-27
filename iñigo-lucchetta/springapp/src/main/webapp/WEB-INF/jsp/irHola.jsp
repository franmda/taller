
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Hello Spring MVC</title>
</head>
<body>

	<form action="javascript:usuario();" id="myForm" name="formulario" method="get">
		First name: <br> <input type="text" id="firstname"
			name="firstname"> <br> Last name: <br> <input
			type="text" id="lastname" name="lastname"> <br>
		<br> <input type="submit" value="submit">
	</form>

	<script src="http://code.jquery.com/jquery-2.2.4.min.js"
		integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="
		crossorigin="anonymous"></script>
	<script type="text/javascript">
		function usuario () {
			$.ajax({
				url : "usuarios/1",
				type : "GET",
				success : function(res) {
					res = $.parseJSON(res);
					// en response vuelve la respues de la API.
					console.log(res);
				}
			});			
		}
	</script>
</body>

</html>
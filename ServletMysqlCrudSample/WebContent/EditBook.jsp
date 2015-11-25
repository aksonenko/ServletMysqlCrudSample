<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  	<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
 	<script src="//code.jquery.com/jquery-1.10.2.js"></script>
	<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
	<link rel="stylesheet" href="/resources/demos/style.css">
	<script>
	  $(function() {
	    $( "#datepicker" ).datepicker();
	  });
	</script>
	<title>Book detail</title>
</head>
<body>
	<script>
		$(function() {
			$('input[name=date]').datepicker();
		});
	</script>

	<form method="POST" action='BookController' name="form">
		<input type="hidden" readonly="readonly" name="id"
            value="<c:out value="${item.id}" />" />  
		Name : <input
			type="text" name="name" value="<c:out value="${item.name}" />" /><br /> 
		Author : <input type="text" name="author"
			value="<c:out value="${item.author}" />" /> <br /> 
		Date : <input type="text" name="date" id="datepicker"
			value="<fmt:formatDate pattern="dd/MM/yyyy" value="${item.date}" />" />
		<br /> <input
            type="submit" value="OK" />
	</form>
</body>
</html>
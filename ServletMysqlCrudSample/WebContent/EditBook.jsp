<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link type="text/css"
	href="css/ui-lightness/jquery-ui-1.8.18.custom.css" rel="stylesheet" />
<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.8.18.custom.min.js"></script>
<title>Book detail</title>
</head>
<body>
	<script>
		$(function() {
			$('input[name=date]').datepicker();
		});
	</script>

	<form method="POST" action='BookController' name="form">
		Name : <input
			type="text" name="name" value="<c:out value="${item.name}" />" /><br /> 
		Author : <input type="text" name="author"
			value="<c:out value="${item.author}" />" /> <br /> 
		Date : <input type="text" name="date"
			value="<fmt:formatDate pattern="MM/dd/yyyy" value="${item.date}" />" />
		<br /> <input
            type="submit" value="Submit" />
	</form>
</body>
</html>
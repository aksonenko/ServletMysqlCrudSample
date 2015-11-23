<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Show All Users</title>
</head>
<body>
	<table border=1>
		<thead>
			<tr>
				<th>Id</th>
				<th>Name</th>
				<th>Author</th>
				<th>Date</th>
				<th colspan=2>Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="item">
				<tr>
					<td><c:out value="${item.id}" /></td>
					<td><c:out value="${item.name}" /></td>
					<td><c:out value="${item.author}" /></td>
					<td><fmt:formatDate pattern="yyyy-MMM-dd" value="${item.date}" /></td>
					<td><a
						href="BookController?action=edit&id=<c:out value="${item.id}"/>">Update</a></td>
					<td><a
						href="BookController?action=delete&id=<c:out value="${item.id}"/>">Delete</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<p>
		<a href="BookController?action=insert">Add</a>
	</p>
</body>
</html>
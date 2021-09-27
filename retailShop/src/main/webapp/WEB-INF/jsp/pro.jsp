<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<div class="container">
		<table class="table table-striped">
			<caption>Your Todos are:</caption>
			<thead>
				<tr>
					<th>Description</th>
					<th>Date</th>
					<th>Is it Done ?</th>
					<th>Delete</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${retails}" var="retail">
					<tr>
						<td>${retail.desc}</td>
						<td>${retail.price}</td>
						<td>${retail.user}</td>
						<td><a type="button" class="btn btn-warning"
							href="/delete-todo?id=${retail.id}">DELETE</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<a class="btn btn-success" href="/add-todo">Add a todo</a>
		<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
		<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	</div>
</body>
</html>

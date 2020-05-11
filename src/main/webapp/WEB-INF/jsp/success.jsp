<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Shortest Distance between two planets</title>
</head>
<body>
	<div align="center">
		<h2>Shortest Distance between two planets</h2>
		<table border="1" cellpadding="5">
			<tr>
				<th>Shortest Path</th>
			</tr>
			<c:forEach items="${shortestPaths}" var="shortestPaths">
				<tr>
					<td>${shortestPaths}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>
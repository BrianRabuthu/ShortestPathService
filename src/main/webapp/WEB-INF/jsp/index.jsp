<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01
    Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Planet Routes</title>
</head>
<body>
<div align="center">
    <h2>Planet Routes</h2>
    <h3>Calculate Short </h3>
    <form method="get" action="findshortestpath">                  
        <label>Travel From: </label><input type="text" name="origin" /> &nbsp;
        <label>Arrive At: </label><input type="text" name="destination" /> &nbsp;
        <input type="submit" value="Search" />
    </form>
    <h3><a href="/new">Add new route</a></h3>
    <table border="1" cellpadding="5">
        <tr>
            <th>Route ID</th>
            <th>Origin</th>
            <th>Destination</th>
            <th>Distance</th>
        </tr>
        <c:forEach items="${listPlanetRoutes}" var="planetRoute">
        <tr>
            <td>${planetRoute.id}</td>
            <td>${planetRoute.planetOrigin}</td>
            <td>${planetRoute.planetDestination}</td>
            <td>${planetRoute.distance}</td>
            <td>
                <a href="/edit?id=${planetRoute.id}">Edit</a>
                &nbsp;&nbsp;&nbsp;
                <a href="/delete?id=${planetRoute.id}">Delete</a>
            </td>
        </tr>
        </c:forEach>
    </table>
</div>   
</body>
</html>
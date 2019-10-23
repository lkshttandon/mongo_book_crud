<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Library</h1>
	<form action="FetchData" method="post">
		Enter ID: <input type="number" name = "bookid" min="0"><br>
 		Enter Book Name : <input type="text" name="name"><br>
		Enter Author Name: <input type="text" name="author"><br>
		Enter Book Type:  <input type="text" name = "type" ><br>
		Price: <input type="number" name = "price" min="0" ><br>

		<br> <input type="submit" name="button" value="Add">
		 <input type="submit" name="button" value="Update">
		 <input type="submit" name="button" value="Delete">
	</form>
	<hr>

	<table border ="1">
		<thead>
			<td>Book Id</td>
			<td>Name</td>
			<td>Author</td>
			<td>Type</td>
			<td>Price</td>
		</thead>
		<c:forEach var="zip" items="${list}">
			<tr>
				<td>${zip.id}</td>
				<td>${zip.name}</td>
				<td>${zip.author}</td>
				<td>${zip.type}</td>
				<td>${zip.price}</td>
			</tr>

		</c:forEach>
	</table>
</body>
</html>


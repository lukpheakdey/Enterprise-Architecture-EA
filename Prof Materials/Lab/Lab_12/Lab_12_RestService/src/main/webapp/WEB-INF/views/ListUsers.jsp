<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE HTML>
<html>
<head>
<title>Save the User</title>
<spring:url value="/resource/css/main.css" var="mainCss" />
<link href="${mainCss}" rel="stylesheet" />
</head>
<body>
<div id="global">
    <h2>List of Users</h2>


<table>
		<tr><th>Name</th><th>Category</th><th>Description</th></tr>

	<c:forEach var="user" items = "${users}">
		<tr>
		<td>${user.firstName}   ${user.lastName}</td>
 		<td>${user.email}</td>
		</tr>
	</c:forEach>
</table>   

    <form action="users/add" method="get">
           <input id="submit" type="submit"  
                value="Add user">
	</form>
	
    <form action="items/add" method="get">
           <input id="submit" type="submit"  
                value="Add item">
	</form>
     
    <form action="logout" method="get">
           <input id="submit" type="submit"  
                value="Logout">
	</form>
     
</div>
</body>
</html>
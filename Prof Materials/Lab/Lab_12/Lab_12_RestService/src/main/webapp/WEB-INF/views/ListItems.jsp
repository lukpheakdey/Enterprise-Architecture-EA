<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE HTML>
<html>
<head>
<title>Save the Item</title>
<spring:url value="/resource/css/main.css" var="mainCss" />
<link href="${mainCss}" rel="stylesheet" />
</head>
<body>
<div id="global">
    <h2>List of Items</h2>


<table>
		<tr><th>Name</th><th>Category</th><th>Description</th></tr>

	<c:forEach var="item" items = "${items}">
		<tr>
		<td>${item.name}</td>
 		<td>${item.description}</td>
		</tr>
	</c:forEach>
</table>   

    <form action="items/add" method="get">
           <input id="submit" type="submit"  
                value="Add item">
	</form>
         <form action="users/add" method="get">
           <input id="submit" type="submit"  
                value="Add user">
	</form>
    <form action="logout" method="get">
           <input id="submit" type="submit"  
                value="Logout">
	</form>
     
</div>
</body>
</html>
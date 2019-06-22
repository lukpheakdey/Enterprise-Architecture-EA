 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE HTML>
<html>
<head>
<title>Add Item Form</title>
<spring:url value="/resource/css/main.css" var="mainCss" />
<link href="${mainCss}" rel="stylesheet" />
</head>
<body>

<div id="global">
<form action="add" method="post">
    <fieldset>
        <legend>Add a item</legend>
         
        <p>
            <label for="name">Item Name: </label>
            <input type="text" id="name" name="name" 
                tabindex="1">
        </p>
        <p>
            <label for="description">Description: </label>
            <input type="text" id="description" value= "${item.description}"
                name="description" tabindex="2">
        </p>
        <p>
            <label for="initialPrice">Initial Price: </label>
            <input type="text" id="initialPrice" name="initialPrice" 
                tabindex="3">
        </p>
        <p id="buttons">
            <input id="reset" type="reset" tabindex="4">
            <input id="submit" type="submit" tabindex="5" 
                value="Add Item">
        </p>
    </fieldset>
</form>
</div>
</body>
</html>

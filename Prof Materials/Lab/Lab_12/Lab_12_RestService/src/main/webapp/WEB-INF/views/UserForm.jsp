 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE HTML>
<html>
<head>
<title>Add User Form</title>
<spring:url value="/resource/css/main.css" var="mainCss" />
<link href="${mainCss}" rel="stylesheet" />
</head>
<body>

<div id="global">
<form action="add" method="post">
    <fieldset>
        <legend>Add a user</legend>
         
        <p>
            <label for="firstName">First Name: </label>
            <input type="text" id="firstName" name="firstName" 
                tabindex="1">
        </p>
        <p>
            <label for="lastName">Last Name: </label>
            <input type="text" id="lastName" name="lastName" 
                tabindex="1">
        </p>
        <p>
            <label for="email">Email: </label>
            <input type="text" id="email" 
                name="email" tabindex="2">
        </p>
        <p>
            <label for="initialPrice">Login Name: </label>
            <input type="text" id="userCredentials.userName" name="userCredentials.userName"> 
 
        </p>
            <label for="password">Password: </label>
            <input type="text" id="userCredentials.password" name="userCredentials.password">
        </p>
        
        </p>
            <label for="authority">Authorization: </label>
            <input type="text" id="userCredentials.authority(0).authority" name="userCredentials.authority(0).authority" 
                tabindex="3">
        </p>
        
        <p id="buttons">
            <input id="reset" type="reset" tabindex="4">
            <input id="submit" type="submit" tabindex="5" 
                value="Add User">
        </p>
    </fieldset>
</form>
</div>
</body>
</html>

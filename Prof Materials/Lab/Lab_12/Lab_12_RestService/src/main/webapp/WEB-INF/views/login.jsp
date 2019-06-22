<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE HTML>
<html>
<head>
<title>Save Item</title>
<spring:url value="/resource/css/main.css" var="mainCss" />
<link href="${mainCss}" rel="stylesheet" />
<!-- <style type="text/css">@import url(resource/css/main.css);</style> -->
</head>
<body>
<div id="global">
				<h1>Welcome Kimosabe!! </h1>
 <form action="<spring:url value="/postLogin"></spring:url>" method="post">
 
    <fieldset>
        <legend>Please sign in</legend>
        
            <label for="userName">User Name: </label>
            <input type="text" id="userName" name="userName">
            <p>
                <label for="password">Password: </label>
                <input type="text" id="password" name="password">
    		</p>
            <p id="buttons">
            <input id="submit" type="submit"  value="Login">
        	</p>
        
     </fieldset>
</form>
	 
</div>
</body>

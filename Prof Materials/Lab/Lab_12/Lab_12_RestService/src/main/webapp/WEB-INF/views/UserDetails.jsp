<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE HTML>
<html>
<head>
<title>Save User</title>
<spring:url value="/resource/css/main.css" var="mainCss" />
<link href="${mainCss}" rel="stylesheet" />
<!-- <style type="text/css">@import url(resource/css/main.css);</style> -->
</head>
<body>
<div id="global">
    <h4>The user has been saved.</h4>
    <p>
        <h5>Details:</h5>
        User Name: ${user.firstName}<br/>
         Description: ${user.lastName}<br/>
        Price: $${user.email}
    </p>
    
    <form action="users" method="get">
           <input id="submit" type="submit"  
                value="List users">
	</form>
    
</div>
</body>
</html>
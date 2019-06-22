<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
    <h4>The item has been saved.</h4>
    <p>
        <h5>Details:</h5>
        Item Name: ${item.name}<br/>
//        Category: ${item.category.name}<br/>
        Description: ${item.description}<br/>
        Price: $${item.initialPrice}
    </p>
    
    <form action="listitems" method="get">
           <input id="submit" type="submit"  
                value="List items">
	</form>
    
</div>
</body>
</html>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML>
<html>
<head>
<title>Add Product Form</title>
<style type="text/css">@import url(resources/css/main.css);</style>
</head>
<body>

<div id="global">
<form:form modelAttribute="newProduct" action="product" method="post">
    <fieldset>
        <legend>Add a product</legend>
        <p>
            <label for="category">Category </label>
 				<form:select id="category" path="category.id" 
					items="${categories}" itemValue="id" itemLabel="name" />
        </p>
         
        <p>
            <label for="name">Product Name: </label>
            <form:input  id="name" path="name"  />
        </p>
        <p>
            <label for="description">Description: </label>
            <form:input  id="description" path="description"  />
        </p>
        <p>
            <label for="price">Price: </label>
            <form:input  id="price" path="price" />
        </p>
        <p id="buttons">
            <input id="reset" type="reset">
            <input id="submit" type="submit" value="Add Product">
        </p>
    </fieldset>
</form:form>
</div>
</body>
</html>

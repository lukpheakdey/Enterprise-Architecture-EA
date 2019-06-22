 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE HTML>
<html>
<head>
<title>Add Product Form</title>
<style type="text/css">@import url(resources/main.css);</style>
</head>
<body>

<div id="global">
<form action="product" method="post">
    <fieldset>
        <legend>Add a product</legend>
        <p>
            <label for="category">Category </label>
 		 	<select name="category.id">
		    	<option value="-">  --Select Category-- </option>
		
		  		<c:forEach var="category" items="${categories}">
		    		<option value="${category.id}" > ${category.name}</option>
				</c:forEach>
		    </select>
        </p>
         
        <p>
            <label for="name">Product Name: </label>
            <input type="text" id="name" name="name" >
         </p>
        <p>
            <label for="description">Description: </label>
            <input type="text" id="description" value= "${product.description}"
                name="description" >
        </p>
        <p>
            <label for="price">Price: </label>
            <input type="text" id="price" name="price" >
        </p>
        <p id="buttons">
            <input id="reset" type="reset" >
            <input id="submit" type="submit" 
                value="Add Product">
        </p>
    </fieldset>
</form>
</div>
</body>
</html>

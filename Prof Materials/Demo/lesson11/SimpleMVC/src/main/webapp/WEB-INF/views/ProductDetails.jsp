<!DOCTYPE HTML>
<html>
<head>
<title>Save Product</title>
<style type="text/css">@import url(resources/css/main.css);</style>
</head>
<body>
<div id="global">
    <h4>The product has been saved.</h4>
    <p>
        <h5>Details:</h5>
        Product Name: ${newProduct.name}<br/>
        Category: ${newProduct.category.name}<br/>
        Description: ${newProduct.description}<br/>
        Price: $${newProduct.price}
    </p>
    
    <form action="listproducts" method="get">
           <input id="submit" type="submit"  
                value="List products">
	</form>
    
</div>
</body>
</html>
<!DOCTYPE html>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<title>Deals</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/lib/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Karma">
<style>
body,h1,h2,h3,h4,h5,h6 {font-family: "Karma", sans-serif}
.w3-sidenav a {padding:20px}
</style>
<body>
	<c:forEach items="${listOfHotels}" var="item">    
    	<div>
		  <ul>
			<li>
			  <img src="<c:out value="${item.getImgPath()}"/>" >
			  <h3><c:out value="${item.getHotelName()}"/></h3>
			  <p><c:out value="${item.getDescription()}"/></p>
			</li>
		  </ul>
		</div>
	</c:forEach>
</body>
</html>

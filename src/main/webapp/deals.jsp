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
* {margin: 0; padding: 0;}

div {
  margin: 20px;
}

ul {
  list-style-type: none;
  width: 500px;
}

h3 {
  font: bold 20px/1.5 Helvetica, Verdana, sans-serif;
}

li img {
  float: left;
  margin: 0 15px 0 0;
}

li p {
  font: 200 12px/1.5 Georgia, Times New Roman, serif;
}

li {
  padding: 10px;
  overflow: auto;
}

li:hover {
  background: #eee;
  
}
</style>
<body>
	<c:forEach items="${listOfHotels}" var="item">    
    	<div>
		  <ul>
			<li>
			  <img src="<c:out value="${item.getImgPath()}"/>" >
			  <h3><strong>Hotel: </strong><c:out value="${item.getHotelName()}"/></h3>
			  <p><strong>Description: </strong><c:out value="${item.getDescription()}"/></p>
			  <p><strong>Destination: </strong><c:out value="${item.getDest()}"/></p>
			  <p><strong>Trip date: </strong><c:out value="${item.getTripDate()}"/></p>
			  <p><strong>Hotel Ratings: </strong><c:out value="${item.getRatings()}"/></p>
			  <p><strong>Night's price: </strong><c:out value="${item.getPrice()}"/>&thinsp;USD</p>
			</li>
			<hr>
		  </ul>
		</div>
	</c:forEach>
</body>
</html>

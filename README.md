Hotel deals parsed from JSON API as assignment for Expedia company

By Mohammad Hader
This website is hosted on heroku: https://exp94.herokuapp.com/

Language: Java - maven
Design:  MVC 
Components: 
	1) launch: Main.java --> define the path to the controller 
	2) view: index.jsp --> welcoming page
	3) view: deals.jsp --> view all hotel deals
	3) model: HotelInformation.java --> contains hotel attributes
	4) controller: HelloServlet.java -->process request and create response
	5) service class: HelloService.java --> contains all functions to process JSON API

Setting the application:

	1) creating app on heroku.
	2) defining dependencies (tomcat,JSON-simple,JSTL) in pom.xml.
	3) define Procfile and a maven plugins in pom.xml.
	4) making a live call to the JSON API then parse it and extract information.
	5) writing parsed information on request scope to use it in "view".
	6) using JSTL in deals.jsp to interact with request scope.
	7) initializing Git repository then commiting changes.
	8) pushing project into Heroku's cloud.


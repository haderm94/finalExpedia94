<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/lib/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lato">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
body,h1,h2,h3,h4,h5,h6 {font-family: "Lato", sans-serif}
.w3-bar,h1,button {font-family: "Montserrat", sans-serif}
.fa-anchor,.fa-coffee {font-size:200px}
</style>
<body>

<!-- Navbar -->


<!-- Header -->
<header class="w3-container w3-red w3-center w3-padding-128">
  <h1 class="w3-margin w3-jumbo">Expedia Hotel Deals</h1>
  <p class="w3-xlarge" >by Mohammad Hader</p>
  <form method="GET" action="hello"><button class="w3-btn w3-padding-16 w3-large w3-margin-top" type="submit">Deals</button></form>
</header>

<!-- First Grid -->



<!-- Footer -->
<footer class="w3-container w3-padding-64 w3-center w3-opacity">  
  <div class="w3-xlarge w3-padding-32">
   <a href="https://www.facebook.com/mohammad.hader" class="w3-hover-text-indigo"><i class="fa fa-facebook-official"></i></a>
   <a href="https://www.linkedin.com/in/mohammad-hader-976588111/" class="w3-hover-text-indigo"><i class="fa fa-linkedin"></i></a>
 </div>
 <p>By Mohammad Hader 	Email: mohammad.hader@hotmail.com</p>
</footer>

<script>
// Used to toggle the menu on small screens when clicking on the menu button
function myFunction() {
    var x = document.getElementById("navDemo");
    if (x.className.indexOf("w3-show") == -1) {
        x.className += " w3-show";
    } else { 
        x.className = x.className.replace(" w3-show", "");
    }
}
</script>

</body>
</html>

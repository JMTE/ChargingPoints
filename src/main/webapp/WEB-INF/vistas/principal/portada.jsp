<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!doctype html>
<html lang="es">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<title>CHANGING POINTS</title>
<link rel="canonical" href="https://getbootstrap.com/docs/5.1/examples/carousel/">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
  integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
  crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
  integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
  integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Advent+Pro:wght@600&display=swap" rel="stylesheet">
<style>
#container2 {
	width: 100%;
	height: 800px;
	background: url("/img/portada2.jpg");
	background-position: center center; /*respecto a body*/
	background-repeat: no-repeat;
  
	opacity: 0.8;
	color: white;
	display: flex;
	justify-content: center;
	font-family: 'Advent Pro'
}

#imagenes {
   margin-top:100px;
	width: 50%;
	height: 18%;
	text-align: center;
    opacity:0.8;
    font-size:29px;
   	
}
#imagen1{
 
 border-radius: 20%;
}
#texto2{
font-size:40px;
}
footer{
	background:#0CBB77;
	color:white;
	opacity:0.9;
	font-size:17px;
    text-align:center;
}

</style>
</head>
<header>
  <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-success">
    <div class="container-fluid">
      <img alt="imagen" src="/img/Logo.jpg" style="width: 50px; margin-right: 10px"> <a class="navbar-brand" href="#">CHARGING POINTS</a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse" aria-controls="navbarCollapse"
        aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarCollapse">
        <ul class="navbar-nav me-auto mb-2 mb-md-0">
          <li class="nav-item"><a class="nav-link active" aria-current="page" href="/index"><i class="bi bi-box-arrow-in-right"> Login</i></a></li>
          <li class="nav-item"><a class="nav-link" href="/registro"><i class="bi bi-person-plus"> Registro</i></a></li>
          <li class="nav-item"><a class="nav-link" href="#"><i class="bi bi-envelope"> Contacto</i></a></li>
        </ul>
      </div>
    </div>
  </nav>
</header>
<body>
  <div id="container2">
    <div id="imagenes">
      
    <img src="/img/imagenCarga.jpg" class="img-fluid" alt="..." id="imagen1">
    <p>El futuro tiene due&ntildeo: El veh&iacuteculo el&eacutectrico</p>
    <p id="texto2">Nuestro reto: Minimizar barreras</p>
     
     
     
     
    </div>
  </div>
</body>
<footer><p> Copyright 2022 Charging Points //  By Jose Maria Tenreiro Eiranova  //  Powered by JAVA EE <i class="bi bi-facebook"></i> <i class="bi bi-instagram"></i></p></footer>

</html>

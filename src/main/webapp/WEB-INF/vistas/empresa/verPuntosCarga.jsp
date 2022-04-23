<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
    <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>PUNTOS CARGA</title>
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="/styles.css">
	<style>
body{

background: url("/img/portada2.jpg")  ;
background-position:center center; /*respecto a body*/
        background-repeat: no-repeat;
       
      
}

.table{
color:white;
background:white:
}
#descripcion{
width:60%;
}

</style>
</head>

<body>

<div class="container">

<header>
<nav class="navbar navbar-expand-lg navbar-light ">
  <div class="container-fluid">
    <a class="navbar-brand  " href="/index"><i class="bi bi-bank"> CHARGING POINTS</i></a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
      <ul class="navbar-nav">
     
     
       
      
      <sec:authorize access="isAuthenticated()">
        <li class="nav-item" >
          <a class="nav-link" href="/empresa/"><i class="bi bi-arrow-return-left"> Volver</i></a>
        </li>
        </sec:authorize>
        <sec:authorize access="isAuthenticated()">
        <li class="nav-item" >
          <a class="nav-link" href="/salir"><i class="bi bi-box-arrow-right"> Cerrar Sesión</i></a>
        </li>
        </sec:authorize>
        
        
      </ul>
    </div>
  </div>
</nav>
</header>


<div class="cuerpo text-white">

<h2>LISTA DE CONECTORES DISPONIBLES EN LA ESTACIÓN</h2>
<table class="table">
  <thead>
    <tr>
      <th scope="col">Nombre</th>
      <th scope="col">Descripcion</th>
      <th scope="col">Eliminar</th>
      
       
       
      
      
    </tr>
  </thead>
  
  <tbody>
  <c:forEach var="ele" items="${listaConectores}">
  <tr>
      <th scope="row"><i class="bi bi-forward-fill"> ${ele.nombre }</i></th>
     <td id="descripcion">${ele.descripcion} </td>
    <td>  
	   		<a class="btn btn-danger " href="/empresa/eliminarConector/${ele.idConector }">Eliminar <i class="bi bi-trash"></i></a> 
	      
      </td>  
         
    </tr>
  </c:forEach>
  
   
  
   
  </tbody>
</table>
<a class="btn btn-success " href="/empresa/anadirConector">Añadir Conector <i class="bi bi-plus-circle"></i></a> 
</div>

</div>
</body>
</html>
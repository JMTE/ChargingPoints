<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
    <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registro Usuario</title>
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<link rel="stylesheet" href="/styles.css">
</head>
<body>
<div class="container">
<header>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <div class="container-fluid">
    <a class="navbar-brand  " href="/"><i class="bi bi-bank"> CHARGING POINTS</i></a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
      <ul class="navbar-nav">
      
        
       
       
    
        
        
      <sec:authorize access="!isAuthenticated()">
        <li class="nav-item" >
          <a class="nav-link" href="/index"><i class="bi bi-arrow-return-left"> Volver</i></a>
        </li>
        </sec:authorize>
       
        
      </ul>
    </div>
  </div>
</nav>
</header>
</div>
<div class="container" id="contenedorAlta">

<h1> REGISTRO DE USUARIO</h1>
<form action="/registro" method="post">

	 <div class="form-group">
    <label >Novedad</label> 
    <select  class="form-select" aria-label="Default select example" name="perfil" required>
    <option value="3" selected>Empresa o Cliente</option>
    <option value="2" >Empresa</option>
    <option value="3" >Cliente</option>
    
    </select>
  	</div>
  	<div class="form-group">
    <label for="exampleInputEmail1">Username</label>
    <input type="text" class="form-control" required  placeholder="Username" name="username">
   
  </div>
  <div class="form-group">
    <label for="exampleInputPassword1">Password</label>
    <input type="password" class="form-control" required placeholder="password" name="password">
  </div>
  <div class="form-group">
    <label for="exampleInputPassword1">Email</label>
    <input type="email" class="form-control" required placeholder="email" name="email">
  </div>
  <div class="form-group">
    <label for="exampleInputPassword1">Nombre</label>
    <input type="text"  class="form-control" required placeholder="Nombre" name="nombre">
  </div>
  <div class="form-group">
    <label for="exampleInputPassword1">Apellidos</label>
    <input type="text" class="form-control" required placeholder="Apellidos" name="apellidos">
  </div>
  <div class="form-group">
    <label for="exampleInputPassword1">Direccion</label>
    <input type="text" class="form-control" required placeholder="Direccion" name="direccion">
  </div>
  
  
  
  <br>
  
  <button type="submit" class="btn btn-success">Alta Usuario</button>
  
  <h2>${mensaje }</h2>
</form>
</div>

</body>
</html>
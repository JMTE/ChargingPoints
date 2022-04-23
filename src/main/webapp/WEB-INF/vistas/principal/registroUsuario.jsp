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
<style>
body{
margin-top:50px;
background: url("/img/portada2.jpg")  ;
background-position:center center; /*respecto a body*/
        background-repeat: no-repeat;
        opacity: 0.8;
        color:white;
}
#contenedorAlta{
margin-top:20px;
 width:50%
}
</style>
</head>
<body>
<div class="container">
<header>
<nav class="navbar navbar-expand-lg navbar-light bg-success">
  <div class="container-fluid">
  <img alt="imagen" src="/img/Logo.jpg" style="width:50px; margin-right:10px">
    <a class="navbar-brand  " href="/"> CHARGING POINTS</a>
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
    <label >Tipo Usuario</label> 
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
  <div class="form-group">
    <label >Provincia</label> 
    <select  class="form-select" aria-label="Default select example" name="provincia" required>
    
    <option value="">Elige Provincia</option>
    <option value="Alava">Álava</option>
    <option value="Albacete">Albacete</option>
    <option value="Alicante">Alicante</option>
    <option value="Almeria">Almería</option>
    <option value="Asturias">Asturias</option>
    <option value="Avila">Ávila</option>
    <option value="Badajoz">Badajoz</option>
    <option value="Baleares">Baleares</option>
    <option value="Barcelona">Barcelona</option>
    <option value="Burgos">Burgos</option>
    <option value="Caceres">Cáceres</option>
    <option value="Cadiz">Cádiz</option>
    <option value="Cantabria">Cantabria</option>
    <option value="Castellon">Castellón</option>
    <option value="Ceuta">Ceuta</option>
    <option value="Ciudad Real">Ciudad Real</option>
    <option value="Cordoba">Córdoba</option>
    <option value="Cuenca">Cuenca</option>
    <option value="Girona">Gerona/Girona</option>
    <option value="Granada">Granada</option>
    <option value="Guadalajara">Guadalajara</option>
    <option value="Gipuzkoa">Guipúzcoa/Gipuzkoa</option>
    <option value="Huelva">Huelva</option>
    <option value="Huesca">Huesca</option>
    <option value="Jaen">Jaén</option>
    <option value="A Coruna">La Coruña/A Coruña</option>
    <option value="La Rioja">La Rioja</option>
    <option value="Las Palmas">Las Palmas</option>
    <option value="Leon">León</option>
    <option value="Lleida">Lérida/Lleida</option>
    <option value="Lugo">Lugo</option>
    <option value="Madrid">Madrid</option>
    <option value="Malaga">Málaga</option>
    <option value="Melilla">Melilla</option>
    <option value="Murcia">Murcia</option>
    <option value="Navarra">Navarra</option>
    <option value="Ourense">Orense/Ourense</option>
    <option value="Palencia">Palencia</option>
    <option value="Pontevedra">Pontevedra</option>
    <option value="Salamanca">Salamanca</option>
    <option value="Segovia">Segovia</option>
    <option value="Sevilla">Sevilla</option>
    <option value="Soria">Soria</option>
    <option value="Tarragona">Tarragona</option>
    <option value="Tenerife">Tenerife</option>
    <option value="Teruel">Teruel</option>
    <option value="Toledo">Toledo</option>
    <option value="Valencia">Valencia</option>
    <option value="Valladolid">Valladolid</option>
    <option value="Bizkaia">Vizcaya/Bizkaia</option>
    <option value="Zamora">Zamora</option>
    <option value="Zaragoza">Zaragoza</option>
  </select>
  </div>
    <div class="form-group">
    <label >Codigo Postal</label>
    <input type="number"  maxlength="5" class="form-control" required placeholder="Codigo Postal" name="cpostal">
  </div>
   
  	
  
  
  
  <br>
  
  <button type="submit" class="btn btn-success">Alta Usuario</button>
  
  <h2>${mensaje }</h2>
</form>
</div>

</body>
</html>
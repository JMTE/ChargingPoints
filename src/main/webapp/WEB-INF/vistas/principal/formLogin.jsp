<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
  integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
  crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
  integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
  integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
<style type="text/css">
body {
	background: url("/img/portada2.jpg");
	background-position: center center; /*respecto a body*/
	background-repeat: no-repeat;
	opacity: 0.8;
}

.container {
	background: #0CBB77;
	margin-top: 100px;
	border: #0CBB77 8px ridge;
	padding-bottom: 20px;
}
</style>
</head>
<body>
  <div class="container">
    <header>
      <nav class="navbar navbar-expand-lg navbar-light ">
        <img alt="imagen" src="/img/Logo.jpg" style="width: 50px; margin-right: 10px"> <a class="navbar-brand  " href="/index"> CHARGING
          POINTS</a> <a class="nav-link" style="color: white" href="/"><i class="bi bi-arrow-return-left"> Volver</i></a>
      </nav>
    </header>
    <div style="margin-top: 100px; width: 50%; margin-left: 20%">
      <form action="/login" method="post">
        <div class="mb-3">
          <label id="label1" for="username" class="form-label">DNI o CIF</label> <input type="text" class="form-control" id="username" name="username">
        </div>
        <div class="mb-3">
          <label for="password" class="form-label">Password</label> <input type="password" class="form-control" id="password" name="password">
        </div>
        <p>${mensaje }</p>
        <button type="submit" class="btn btn-success">Entrar</button>
      </form>
      <br>
      <div class="form-group">
        <div class="col-md-12 control">
          <div style="border-top: 1px solid #888; padding-top: 15px; font-size: 85%">
            No tienes cuenta? <a href="/registro" style="color: white"> Registrate aqui </a>
          </div>
        </div>
      </div>
    </div>
  </div>
</body>
</html>
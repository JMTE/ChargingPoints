<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>AÑADIR VEHICULO</title>
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
  integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
  crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
  integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
  integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<link rel="stylesheet" href="/styles.css">
<link rel="stylesheet" href="/styles.css">
<style>
body {
	background: url("/img/portada2.jpg");
	background-position: center center; /*respecto a body*/
	background-repeat: no-repeat;
	color: white
}

.table {
	color: white;
	background: white:
}

#contenedorAlta {
	margin-top: 20px;
	width: 50%
}
</style>
</head>
<body>
  <div class="container">
    <header>
      <nav class="navbar navbar-expand-lg navbar-light ">
        <div class="container-fluid">
          <img alt="imagen" src="/img/Logo.jpg" style="width: 50px; margin-right: 10px"> <a class="navbar-brand  " href="/"> CHARGING POINTS</a>
          <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown"
            aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse" id="navbarNavDropdown">
            <ul class="navbar-nav">
              <li class="nav-item"><a class="nav-link" href="/index"><i class="bi bi-arrow-return-left"> Volver</i></a></li>
            </ul>
          </div>
        </div>
      </nav>
    </header>
  </div>
  <div class="container" id="contenedorAlta">
    <h1>AÑADIR VEHICULO</h1>
    <form action="/cliente/anadirVehiculo" method="post">
      <div class="form-group">
        <div class="form-group">
          <label for="Marca">Marca vehículo</label> <input type="text" class="form-control" required placeholder="Marca" name="nombre">
        </div>
        <div class="form-group">
          <label for="matricula">Matricula</label> <input type="text" class="form-control" maxlength="7" required placeholder="Matricula"
            name="matricula">
        </div>
        <div class="form-group">
          <label for="matricula">Potencia</label> <input type="number" class="form-control" placeholder="Potencia" name="potencia">
        </div>
        <div class="form-group">
          <label for="matricula">Autonomia</label> <input type="number" class="form-control" placeholder="Autonomia" name="autonomia">
        </div>
        <div class="form-group">
          <label>Tipo Conector</label> <select class="form-select" aria-label="Default select example" name="idConector" required>
            <option value="1" selected>Tipo Conector</option>
            <option value="1">Schuko</option>
            <option value="2">SAE J1772 (Tipo 1)</option>
            <option value="3">Mennekes (Tipo 2)</option>
            <option value="4">CHAdeMo</option>
            <option value="5">combinado CSS</option>
          </select>
        </div>
        <div class="form-group">
          <label>Tipo Bateria</label> <select class="form-select" aria-label="Default select example" name="idBateria" required>
            <option value="1" selected>Tipo Bateria</option>
            <option value="1">Níquel-cadmio (NiCd)</option>
            <option value="2">Ion-litio (LiCoO2)</option>
            <option value="3">Ion-litio con cátodo de LiFePO4</option>
            <option value="4">Polímero de litio (LiPo)</option>
          </select>
        </div>
      </div>
      <br>
      <button type="submit" class="btn btn-success">
        <i class="bi bi-plus-circle"> Alta Vehiculo</i>
      </button>
    </form>
  </div>
</body>
</html>
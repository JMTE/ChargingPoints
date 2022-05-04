<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>NUEVA RESERVA</title>
<!-- CSS only -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
	integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
	crossorigin="anonymous"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
	integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
	integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
	crossorigin="anonymous"></script>
 <script src="/mainReserva.js"></script>
 <link rel="stylesheet"
	href="/styles.css">
	<style>
body{

background: url("/img/portada2.jpg")  ;
background-position:center center; /*respecto a body*/
        background-repeat: no-repeat;
       color:white
      
}

.table{
color:white;
background:white:
}
#contenedorAlta{
margin-top:20px;
 width:50%;

margin-left:25%;
}
#contenedorAlta input, label{
margin-left:25%;
}

</style>
</head>

<body>

	<div class="container">

		<header>
			<nav class="navbar navbar-expand-lg navbar-light ">
				<div class="container-fluid">
					<a class="navbar-brand  " href="/index"><i class="bi bi-bank">
							CHARGING POINTS</i></a>
					<button class="navbar-toggler" type="button"
						data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown"
						aria-controls="navbarNavDropdown" aria-expanded="false"
						aria-label="Toggle navigation">
						<span class="navbar-toggler-icon"></span>
					</button>
					<div class="collapse navbar-collapse" id="navbarNavDropdown">
						<ul class="navbar-nav">







							<sec:authorize access="isAuthenticated()">
								<li class="nav-item"><a class="nav-link"
									href="/cliente/"><i class="bi bi-box-arrow-right">
											Volver</i></a></li>
							</sec:authorize>
							<sec:authorize access="isAuthenticated()">
								<li class="nav-item"><a class="nav-link" href="/salir"><i
										class="bi bi-box-arrow-right"> Cerrar Sesión</i></a></li>
							</sec:authorize>


						</ul>
					</div>
				</div>
			</nav>
		</header>

		<div class="cuerpo  p-2 text-white " id="contenedorAlta">
	
			<div class="row">
        <div class="col-md-12">
            <div class="well well-sm">
                <form class="form-horizontal" method="post" action="/cliente/addCarrito">
                    <fieldset>
                        <legend class="text-center header">INFORMACIÓN NUEVA CARGA</legend>


						 <div class="form-group">
                            <span class="col-md-1 col-md-offset-2 text-center"><i class="fa fa-user bigicon"></i></span>
                            <div class="col-md-8">
                            	<label>Estacion</label>
                                <input id="idEstacion" type="text" name="idEstacion" value="${reserva.estacione.idEstacion }" readonly class="form-control">
                            </div>
                        </div>
                        <div class="form-group">
                            <span class="col-md-1 col-md-offset-2 text-center"><i class="fa fa-user bigicon"></i></span>
                            <div class="col-md-8">
                            	<label>Direccion</label>
                                <input id="direccion" type="text" name="direccion" value="${reserva.estacione.direccion }" readonly class="form-control">
                            </div>
                        </div>
                        <div class="form-group">
                            <span class="col-md-1 col-md-offset-2 text-center"><i class="fa fa-user bigicon"></i></span>
                            <div class="col-md-8">
                            	<label>Dia Carga</label>
                                <input  id="fechaServicio" name="fecha" type="text" value="<fmt:formatDate pattern = "dd-MM-yyyy" value = "${reserva.fechaServicio}" />" readonly class="form-control">
                            </div>
                        </div>

                        <div class="form-group">
                            <span class="col-md-1 col-md-offset-2 text-center"><i class="fa fa-envelope-o bigicon"></i></span>
                            <div class="col-md-8">
                            	<label>Franja Horaria</label>
                                <c:choose>
    								<c:when test="${reserva.horasCarga==1}">
    								<input type="text" class="form-control" name="horas" value="Manana" readonly >
    								</c:when>
									
    								<c:otherwise>
    								<input type="text" class="form-control" name="horas" value="Tarde" readonly >
   									 </c:otherwise>     
								</c:choose>
                            </div>
                        </div>

                        <div class="form-group">
                            <span class="col-md-1 col-md-offset-2 text-center"><i class="fa fa-phone-square bigicon"></i></span>
                            <div class="col-md-8">
                            	<label>Conector</label>
                                <input id="descripcion" name="descripcion" value="${reserva.descripcion}" readonly class="form-control">
                            </div>
                        </div>
                         <div class="form-group">
                            <span class="col-md-1 col-md-offset-2 text-center"><i class="fa fa-phone-square bigicon"></i></span>
                            <div class="col-md-8">
                            	<label>Minutos Carga</label>
                                <input id="minutos"  type="number" required class="form-control">
                            </div>
                        </div>
                        <div class="form-group">
                            <span class="col-md-1 col-md-offset-2 text-center"><i class="fa fa-phone-square bigicon"></i></span>
                            <div class="col-md-8">
                            	<label>Precio</label>
                                <input id="precio" name="precioTotal"  readonly class="form-control">
                            </div>
                        </div>
                        
                        
                        <br>

                        <div class="form-group">
                            <div class="col-md-12 text-center">
                            
                                <button type="submit"  class="btn btn-success btn-lg" >Reservar</button>
                            </div>
                        </div>
                    </fieldset>
                </form>
            </div>
        </div>
    </div>

				
		</div>

	</div>
</body>
</html>
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
<title>DATOS CLIENTE</title>
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
 <script src="/js/main.js"></script>
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
				  <img alt="imagen" src="/img/Logo.jpg" style="width:50px; margin-right:10px">
					<a class="navbar-brand  " href="/index">
							CHARGING POINTS</a>
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
									href="/cliente/"><i class="bi bi-arrow-return-left">
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

		<div class="cuerpo bg-success p-2 text-white " id="contenedorAlta">
	
			<div class="row">
        <div class="col-md-12">
            <div class="well well-sm">
                <form class="form-horizontal" method="post" action="/cliente/modificarDatosCliente">
                    <fieldset>
                        <legend class="text-center header">Datos Usuario</legend>

                        <div class="form-group">
                            <span class="col-md-1 col-md-offset-2 text-center"><i class="fa fa-user bigicon"></i></span>
                            <div class="col-md-8">
                            	<label>Usuario</label>
                                <input id="username" type="text" name="username" value="${usuario.username }" readonly class="form-control">
                            </div>
                        </div>
                        <div class="form-group">
                            <span class="col-md-1 col-md-offset-2 text-center"><i class="fa fa-user bigicon"></i></span>
                            <div class="col-md-8">
                            	<label>Password</label>
                                <input  id="password" name="password" type="password" value="${usuario.password }" disabled class="form-control">
                            </div>
                        </div>

                        <div class="form-group">
                            <span class="col-md-1 col-md-offset-2 text-center"><i class="fa fa-envelope-o bigicon"></i></span>
                            <div class="col-md-8">
                            	<label>Nombre</label>
                                <input id="nombre" name="nombre" value="${usuario.nombre }" disabled class="form-control">
                            </div>
                        </div>

                        <div class="form-group">
                            <span class="col-md-1 col-md-offset-2 text-center"><i class="fa fa-phone-square bigicon"></i></span>
                            <div class="col-md-8">
                            	<label>Apellidos</label>
                                <input id="apellidos" name="apellidos" value="${usuario.apellidos }" disabled class="form-control">
                            </div>
                        </div>
                         <div class="form-group">
                            <span class="col-md-1 col-md-offset-2 text-center"><i class="fa fa-phone-square bigicon"></i></span>
                            <div class="col-md-8">
                            	<label>Direccion</label>
                                <input id="direccion" name="direccion" value="${usuario.direccion }" disabled class="form-control">
                            </div>
                        </div>
                        <div class="form-group">
                            <span class="col-md-1 col-md-offset-2 text-center"><i class="fa fa-phone-square bigicon"></i></span>
                            <div class="col-md-8">
                            	<label>Email</label>
                                <input id="email" name="email" value="${usuario.email }" disabled class="form-control">
                            </div>
                        </div>
                        <div class="form-group">
                            <span class="col-md-1 col-md-offset-2 text-center"><i class="fa fa-phone-square bigicon"></i></span>
                            <div class="col-md-8">
                            	<label>Provincia</label>
                                <input id="provincia" name="provincia" value="${usuario.provincia }" disabled class="form-control">
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <span class="col-md-1 col-md-offset-2 text-center"><i class="fa fa-phone-square bigicon"></i></span>
                            <div class="col-md-8">
                            	<label>Código Postal</label>
                                <input id="cpostal" name="cpostal" type="number"  maxlength="5" value="${usuario.cpostal }" disabled class="form-control">
                            </div>
                        </div>
                        
                        
                        

                        <div class="form-group">
                            <div class="col-md-12 text-center">
                            	<button type="button"  class="btn btn-success btn-lg" id="habilitaCampos">Modificar</button>
                                <button type="submit" id="botonSubmit" class="btn btn-success btn-lg" disabled>Aplicar Cambios</button>
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
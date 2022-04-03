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
<title>Principal</title>
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
</head>
<body>
	<div class="container">
		<header>
			<nav class="navbar navbar-expand-lg navbar-light bg-light">
				<div class="container-fluid">
					<a class="navbar-brand  " href="/index"><i class="bi bi-shop">
							CHARGING POINTS</i></a>
					<button class="navbar-toggler" type="button"
						data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown"
						aria-controls="navbarNavDropdown" aria-expanded="false"
						aria-label="Toggle navigation">
						<span class="navbar-toggler-icon"></span>
					</button>
					<div class="collapse navbar-collapse" id="navbarNavDropdown">
						<ul class="navbar-nav">
							<sec:authorize access="hasAuthority('ADMIN')">
								<li class="nav-item"><a class="nav-link  "
									aria-current="page" href="/administrador/verUsuarios"><i
										class="bi bi-people-fill"> Usuarios</i></a></li>
							</sec:authorize>

							<sec:authorize access="hasAuthority('ADMIN')">
								<li class="nav-item"><a class="nav-link"
									href="/administrador/verClientes"><i class="bi bi-bag">
											Clientes</i></a></li>
							</sec:authorize>

							<sec:authorize access="hasAuthority('EMPRE')">
								<li class="nav-item"><a class="nav-link"
									href="/empresa/verClientes"><i class="bi bi-bag">
											Clientes</i></a></li>
							</sec:authorize>
							<sec:authorize access="hasAuthority('EMPRE')">
								<li class="nav-item"><a class="nav-link"
									href="/empresa/verRecargas"><i class="bi bi-bag">
											Recargas</i></a></li>
							</sec:authorize>


							<sec:authorize access="hasAuthority('ADMIN')">
								<li class="nav-item"><a class="nav-link"
									href="/administrador/verEmpresas"><i class="bi bi-book">
											Empresas</i></a></li>

							</sec:authorize>

							<sec:authorize access="hasAuthority('ADMIN')">
								<li class="nav-item"><a class="nav-link"
									href="/administrador/verReservas"><i class="bi bi-bag">
											Reservas</i></a></li>
							</sec:authorize>

							<sec:authorize access="hasAuthority('CLIEN')">
								<li class="nav-item"><a class="nav-link"
									href="/cliente/datosCliente"><i class="bi bi-person-fill">Mis
											datos </i></a></li>
							</sec:authorize>
								<sec:authorize access="hasAuthority('CLIEN')">
								<li class="nav-item"><a class="nav-link"
									href="/cliente/añadirVehiculo"><i class="bi bi-cart">
											 Vehiculo </i></a></li>
							</sec:authorize>
							<sec:authorize access="hasAuthority('CLIEN')">
								<li class="nav-item"><a class="nav-link"
									href="/cliente/verCarrito"><i class="bi bi-cart"> Ver
											Carrito( ${numeroCarrito } ) </i></a></li>
							</sec:authorize>
						
							<sec:authorize access="hasAuthority('CLIEN')">
								<li class="nav-item">
									<form class="d-flex" action="/cliente/buscarEstacionesLibres" method="post">
										<input class="form-control me-2" type="text" name="fecha"
											placeholder="Fecha para Reserva" onclick="ocultarError();"
											onfocus="(this.type='date')" onblur="(this.type='text')"
											aria-label="Search" name="fecha">
										<button class="btn btn-outline-success" type="submit">Buscar</button>
									</form>
								</li>
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
		<div class="cuerpo bg-success p-2 text-white bg-opacity-25">
			<div class="imagenhome">
				<sec:authorize access="hasAnyAuthority('EMPRE','ADMIN')">

					<br>
					<h4>
						<i class="bi bi-person-check"> Usuario: ${usuario.username }</i>
					</h4>
					<br>
					<sec:authorize access="hasAnyAuthority('ADMIN','EMPRE')">
						<a class="btn btn-success " href="/admon/altaLibro"><i
							class="bi bi-book"> Nuevo Libro</i></a>
						<a class="btn btn-success " href="/admon/altaTema"><i
							class="bi bi-journal-plus"> Nuevo Tema</i></a>
						<br>
						<br>
						<form class="d-flex" action="/admon/verPedidos" method="post">
							<input class="form-control" type="date" required name="fechaAlta">
							<button class="btn btn-outline-success" type="submit">Ver
								Pedidos</button>
						</form>
					</sec:authorize>

				</sec:authorize>

				<br>

				<sec:authorize access="hasAuthority('ADMIN')">
					<h3>ULTIMOS USUARIOS REGISTRADOS</h3>
					<table class="table">
						<thead>
							<tr>
								<th scope="col">DNI/CIF</th>
								<th scope="col">Nombre</th>
								<th scope="col">Apellidos</th>
								<th scope="col">Perfil</th>
								<th scope="col">Historial</th>

							</tr>
						</thead>
						<tbody>
							<c:forEach var="ele" items="${listaUsuariosUltimos }">

								<tr>
									<td>${ele.username }</td>
									<td>${ele.nombre }</td>
									<td>${ele.apellidos }</td>
									<td>${ele.perfiles }</td>
									<td><a class="btn btn-success "
										href="/administrador/historialUsuario/${ele.username }">Historial
											<i class="bi bi-pencil"></i>
									</a></td>



								</tr>

							</c:forEach>
						</tbody>


					</table>
				</sec:authorize>

				<sec:authorize access="hasAuthority('CLIEN')">


					<h3>PROXIMAS RECARGAS</h3>
					<table class="table">
						<thead>
							<tr>
								<th scope="col">Direccion</th>
								<th scope="col">Fecha Servicio</th>
								<th scope="col">Conector</th>
								<th scope="col">Precio Total</th>
								<th scope="col">Franja Horaria</th>
								<th scope="col">Estado</th>
								


							</tr>
						</thead>
						<tbody>
						<c:forEach var="ele" items="${listaReservasPendientes }">
						<tr>
							<td>${ele.estacione.direccion }</td>
							<td>${ele.fechaServicio }</td>
							<td>${ele.descripcion }</td>
							<td>${ele.precioTotal }</td>
							<c:choose>
    								<c:when test="${ele.horasCarga<2}">
    								<td>Mañana</td>
    								</c:when>
									
    								<c:when test="${ele.horasCarga>1}">
    								<td>Tarde</td>
   									</c:when>     
							</c:choose>
							<td>${ele.estado }</td>
						</tr>
						</c:forEach>
						
						</tbody>

					</table>
				</sec:authorize>

				<sec:authorize access="hasAuthority('CLIEN')">

					<h3>TODAS LAS RECARGAS</h3>
					<table class="table">
						<thead>
							<tr>
								<th scope="col">Direccion</th>
								<th scope="col">Fecha Servicio</th>
								<th scope="col">Conector</th>
								<th scope="col">Precio Total</th>
								<th scope="col">Franja Horaria</th>
								<th scope="col">Estado</th>


							</tr>
						</thead>
						<tbody>
						<c:forEach var="ele" items="${listaReservasPorCliente }">
						<tr>
							<td>${ele.estacione.direccion }</td>
							<td>${ele.fechaServicio }</td>
							<td>${ele.descripcion }</td>
							<td>${ele.precioTotal }</td>
							
							<c:choose>
    								<c:when test="${ele.horasCarga<2}">
    								<td>Mañana</td>
    								</c:when>
									
    								<c:when test="${ele.horasCarga>1}">
    								<td>Tarde</td>
   									</c:when>     
							</c:choose>
							<td>${ele.estado }</td>
						</tr>
						</c:forEach>
						</tbody>

					</table>
				</sec:authorize>


				<sec:authorize access="hasAuthority('EMPRE')">
					<h3>ULTIMAS RECARGAS EN LA ESTACIÓN</h3>
					<table class="table">
						<thead>
							<tr>
								<th scope="col">IDRESERVA</th>
								<th scope="col">FECHA RESERVA</th>
								<th scope="col">FECHA SERVICIO</th>
								<th scope="col">ESTADO</th>

								<th scope="col">ESTACION</th>
								<th scope="col">CLIENTE</th>
								<th scope="col">PRECIO</th>
								<th scope="col">PAGADO</th>


							</tr>
						</thead>
						<tbody>
							<c:forEach var="ele" items="${listaRecargasPorEmpresa }">
								<tr>
									<td>${ele.idReserva }</td>
									<td>${ele.fechaReserva }</td>
									<td>${ele.fechaServicio }</td>
									<td>${ele.estado }</td>
									<td>${ele.estacione.idEstacion }</td>
									<td>${ele.usuario.username }</td>
									<td>${ele.precioTotal }</td>
									<td>${ele.pagado }</td>
								</tr>
							</c:forEach>
						</tbody>


					</table>
				</sec:authorize>

			</div>
			<a class="btn btn-success " href="/index">${volver }</a>
		</div>

	</div>


</body>
</html>
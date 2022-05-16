package fp.charging.points.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fp.charging.points.modelo.beans.Bateria;
import fp.charging.points.modelo.beans.Conectore;
import fp.charging.points.modelo.beans.Reserva;
import fp.charging.points.modelo.beans.Usuario;
import fp.charging.points.modelo.beans.Vehiculo;
import fp.charging.points.modelo.dao.IntBateriaDao;
import fp.charging.points.modelo.dao.IntConectoreDao;
import fp.charging.points.modelo.dao.IntEstacioneDao;
import fp.charging.points.modelo.dao.IntReservaDao;
import fp.charging.points.modelo.dao.IntUsuarioDao;
import fp.charging.points.modelo.dao.IntVehiculoDao;
import fp.charging.points.repository.IntBateriaRepository;
/**
 * 
 * Esta clase define el Controlador del Cliente y sus funcionalidades
 * 
 * @author JMTE
 * @since 20/05/2022
 * @version 1.0
 * 
 * 
 * 
 */
@Controller

@RequestMapping("cliente")
public class ClienteController {

	@Autowired
	private IntUsuarioDao usuDao;
	@Autowired
	HttpSession sesion;
	@Autowired
	HttpSession sesionNumeroCarrito;
	@Autowired
	HttpSession listaSesion;
	@Autowired
	private IntEstacioneDao estDao;
	@Autowired
	private IntConectoreDao conDao;
	@Autowired
	private IntReservaDao resDao;
	@Autowired
	private IntVehiculoDao vehDao;

	@Autowired
	private IntBateriaDao batDao;

	@Autowired
	private PasswordEncoder pwenco;

	/**
	 * Este metodo con su anotacion GetMapping actúa como un acceso directo para @RequestMapping (method
	 * = RequestMethod.GET). Nos permite acceder a la pagina de cliente
	 * 
	 * @param model Es usado para definir un modelo en una aplicacion Spring MVC
	 * @return jsp de la pantalla index con los elementos para cliente
	 */
	@GetMapping("/")

	public String index(Model model) {
		// Recuperamos al usuario de la sesion
		Usuario usuario = (Usuario) sesion.getAttribute("usuario");
		// Recuperamos la lista que nos va a dar el numero que tiene el Ver Carrito a su lado
		List<String> numeroCarrito = (List<String>) sesionNumeroCarrito.getAttribute("numeroCarrito");
		model.addAttribute("numeroCarrito", numeroCarrito.size());
		model.addAttribute("listaReservasPendientes", resDao.findReservasPorUsuarioAndEstadoPendiente(usuario.getUsername()));
		model.addAttribute("listaReservasPorCliente", resDao.findReservaPorUsuario(usuario.getUsername()));
		return "principal/index";
	}

	/**
	 * Este metodo con su anotacion GetMapping actúa como un acceso directo para @RequestMapping (method
	 * = RequestMethod.GET). Nos permite acceder a la pantalla de los datos del cliente
	 * @param model Es usado para definir un modelo en una aplicacion Spring MVC
	 * @return jsp de la pantalla datos del cliente
	 */

	@GetMapping("/datosCliente")
	public String datosCliente(Model model) {

		Usuario usuario = (Usuario) sesion.getAttribute("usuario");

		model.addAttribute("usuario", usuDao.findUsuarioByDni(usuario.getUsername()));

		return "cliente/datosCliente";
	}

	/**
	 * Este metodo con su anotacion PostMapping actúa como un acceso directo para @RequestMapping
	 * (method = RequestMethod.POST). Con este metodo, traemos los datos modificados del usuario y los
	 * cambiamos en la base de datos.
	 * @param model Es usado para definir un modelo en una aplicacion Spring MVC
	 * @param usuario Usuario del que sus datos serán modificados
	 * @return jsp de la pantalla index
	 */
	@PostMapping("/modificarDatosCliente")
	public String modificarDatosCliente(Model model, Usuario usuario) {

		Usuario usuario2 = usuDao.findUsuarioByDni(usuario.getUsername());
		if(!usuario.getPassword().equals(usuario2.getPassword())) {
		usuario2.setPassword(pwenco.encode(usuario.getPassword()));	
		}
		
		usuario2.setNombre(usuario.getNombre());
		usuario2.setApellidos(usuario.getApellidos());
		usuario2.setDireccion(usuario.getDireccion());
		usuario2.setCpostal(usuario.getCpostal());
		usuario2.setProvincia(usuario.getProvincia());
		usuDao.modificarDatosCliente(usuario2);
		model.addAttribute("listaReservasPendientes", resDao.findReservasPorUsuarioAndEstadoPendiente(usuario.getUsername()));
		model.addAttribute("listaReservasPorCliente", resDao.findReservaPorUsuario(usuario.getUsername()));

		return "redirect:/cliente/";
	}

	/**
	 * Este metodo con su anotacion PostMapping actúa como un acceso directo para @RequestMapping
	 * (method = RequestMethod.POST). Con este metodo, llevamos el dato de la fecha para la reserva y
	 * presentamos las estaciones con sus conectores
	 * @param model Es usado para definir un modelo en una aplicacion Spring MVC
	 * @param fecha Fecha en las que se buscan estaciones para carga
	 * @return jsp de la pantalla estacionesLibres
	 * @throws ParseException Excepcion por la fecha
	 */
	@PostMapping("/buscarEstacionesLibres")
	public String verEstacionesLibres(Model model, @RequestParam String fecha) throws ParseException {
		Usuario usuario = (Usuario) sesion.getAttribute("usuario");
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		Date dataFormateada = formato.parse(fecha);
		Date now = new Date();

		// Si la fecha es anterior a la actual, no permitimos mostrar estaciones
		if (dataFormateada.before(now)) {

			// Recuperamos la lista que nos va a dar el numero que tiene el Ver Carrito a su lado
			List<String> numeroCarrito = (List<String>) sesionNumeroCarrito.getAttribute("numeroCarrito");
			model.addAttribute("numeroCarrito", numeroCarrito.size());
			model.addAttribute("listaReservasPendientes", resDao.findReservasPorUsuarioAndEstadoPendiente(usuario.getUsername()));
			model.addAttribute("listaReservasPorCliente", resDao.findReservaPorUsuario(usuario.getUsername()));
			model.addAttribute("mensajeFechaAnterior", "La fecha no puede ser anterior a la actual");
			return "principal/index";
		} else {
			if (usuDao.findUsuarioByDni(usuario.getUsername()).getVehiculo() == null) {
				return "cliente/verVehiculo";
			} else {
				// Formateamos la fecha que nos devuelve como String para poder introducirla como Date

				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

				model.addAttribute("listaEstacionesLibres", estDao.findAll());
				model.addAttribute("fecha", sdf.format(dataFormateada));
				return "cliente/verEstacionesLibres";
			}
		}

	}

	/**
	 * Este metodo con su anotacion GetMapping actúa como un acceso directo para @RequestMapping (method
	 * = RequestMethod.GET). Nos permite acceder a la pantalla de reserva de estacion.
	 * 
	 * @param model         Es usado para definir un modelo en una aplicacion Spring MVC
	 * @param idEstacion    Nos proporciona el idEstacion
	 * @param idConector    Nos proporciona el idConector
	 * @param fechaServicio Nos proporciona la fecha en la que se producira el servicio
	 * @param horasCarga    Nos proporciona la franja horaria del servicio (Mañana/tarde)
	 * @param descripcion   Nos proporciona el conector que se reserva.
	 * @return jsp de la pantalla datos del cliente
	 * @throws ParseException Excepcion por la fecha
	 */

	@GetMapping("/reservar/{idEstacion}/{idConector}/{fechaServicio}/{horasCarga}/{descripcion}")
	public String reservar(Model model, @PathVariable int idEstacion, @PathVariable int idConector, @PathVariable String fechaServicio,
			@PathVariable BigDecimal horasCarga, @PathVariable String descripcion) throws ParseException {
		SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
		Date dataFormateada = formato.parse(fechaServicio);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		if (resDao.findReservaPorEstacionFechaServicioHorasCargaDescripcion(idEstacion, dataFormateada, horasCarga, descripcion) == 1) {
			model.addAttribute("listaEstacionesLibres", estDao.findAll());
			model.addAttribute("fecha", sdf.format(dataFormateada));
			model.addAttribute("mensajeError", "Esta franja horaria en este punto de carga esta reservado");
			return "cliente/verEstacionesLibres";
		} else {
			Reserva reserva = new Reserva();

			reserva.setEstacione(estDao.findEstacionById(idEstacion));
			reserva.setHorasCarga(horasCarga);

			Conectore conector = conDao.findConectorById(idConector);
			reserva.setDescripcion(conector.getNombre());

			reserva.setFechaServicio(dataFormateada);

			model.addAttribute("reserva", reserva);
			return "cliente/nuevaReserva";
		}

	}

	/**
	 * Este metodo con su anotacion PostMapping actúa como un acceso directo para @RequestMapping
	 * (method = RequestMethod.POST). Con este metodo, recibimos los datos de la reserva y creamos una
	 * reserva nueva que añadimos al carrito a la espera de que se formalice la reserva
	 * @param model Es usado para definir un modelo en una aplicacion Spring MVC
	 * @param reserva Reserva a añadir en el carrito
	 * @param idEstacion idEstacion en la que se hace la reserva
	 * @param fecha Fecha de la reserva
	 * @param horas Franja horaria de la reserva
	 * @throws ParseException Excepcion por la fecha
	 * @return jsp de la pantalla cliente con el carrito actualizado
	 */
	@PostMapping("/addCarrito")
	public String altaCarrito(Model model, Reserva reserva, @RequestParam int idEstacion, @RequestParam String fecha, @RequestParam String horas)
			throws ParseException {

		// Rescatamos las listas que tenemos en sesion
		List<Reserva> lista = (List<Reserva>) listaSesion.getAttribute("listaCarrito");
		List<String> numeroCarrito = (List<String>) sesionNumeroCarrito.getAttribute("numeroCarrito");

		// sumamos un numero al lado del ver Carrito
		numeroCarrito.add("Otra recarga al carrito");

		Usuario usuario = (Usuario) sesion.getAttribute("usuario");

		SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
		Date dataFormateada = formato.parse(fecha);
		reserva.setFechaServicio(dataFormateada);
		reserva.setFechaReserva(new Date());

		if (horas.equals("Manana")) {
			reserva.setHorasCarga(new BigDecimal(1));
		} else {
			reserva.setHorasCarga(new BigDecimal(2));
		}
		reserva.setEstacione(estDao.findEstacionById(idEstacion));
		reserva.setEstado("Pendiente");
		reserva.setUsuario(usuario);
		reserva.setPagado("No");
		resDao.addReservaCarrito(reserva, lista);
		System.out.println(lista);
		// Presentamos los datos necesarios en el index
		model.addAttribute("listaReservasPendientes", resDao.findReservasPorUsuarioAndEstadoPendiente(usuario.getUsername()));
		model.addAttribute("listaReservasPorCliente", resDao.findReservaPorUsuario(usuario.getUsername()));
		model.addAttribute("numeroCarrito", numeroCarrito.size());
		model.addAttribute("listaReservas", lista);
		return "redirect:/cliente/";
	}

	/**
	 * Este metodo con su anotacion GetMapping actúa como un acceso directo para @RequestMapping (method
	 * = RequestMethod.GET). Nos presenta la pantalla del carrito con sus items
	 * @param model Es usado para definir un modelo en una aplicacion Spring MVC
	 * @return jsp de la pantalla carrito de la compra
	 */
	@GetMapping("/verCarrito")
	public String verCarrito(Model model) {
		List<Reserva> lista = (List<Reserva>) listaSesion.getAttribute("listaCarrito");
		model.addAttribute("listaReservas", lista);
		return "cliente/verCarrito";
	}

	/**
	 * Este metodo con su anotacion GetMapping actúa como un acceso directo para @RequestMapping (method
	 * = RequestMethod.GET). Nos permite eliminar un item del carrito de la compra
	 * 
	 * @param model     Es usado para definir un modelo en una aplicacion Spring MVC
	 * @param idReserva Nos proporciona el idReserva
	 * @return jsp de la pantalla carrito de la compra
	 */
	@GetMapping("/eliminarCarrito/{idReserva}")
	public String eliminarCarrito(Model model, @PathVariable int idReserva) {
		List<String> numeroCarrito = (List<String>) sesionNumeroCarrito.getAttribute("numeroCarrito");
		List<Reserva> lista = (List<Reserva>) listaSesion.getAttribute("listaCarrito");
		lista.remove(idReserva);
		numeroCarrito.remove(0);
		model.addAttribute("listaReservas", lista);
		return "cliente/verCarrito";
	}

	/**
	 * Este metodo con su anotacion GetMapping actúa como un acceso directo para @RequestMapping (method
	 * = RequestMethod.GET). Aqui realizamos la reserva de los items que tenemos en el carrito
	 * @param model Es usado para definir un modelo en una aplicacion Spring MVC
	 * @throws ParseException Excepcion por la fecha
	 * @return jsp de la pantalla datos del cliente
	 */
	@GetMapping("/reservar")
	public String reservar(Model model) throws ParseException {

		Usuario usuario = (Usuario) sesion.getAttribute("usuario");
		List<Reserva> lista = (List<Reserva>) listaSesion.getAttribute("listaCarrito");
		List<String> numeroCarrito = (List<String>) sesionNumeroCarrito.getAttribute("numeroCarrito");
		resDao.altaReservas(lista);
		lista.clear();
		numeroCarrito.clear();
		model.addAttribute("listaReservasPendientes", resDao.findReservasPorUsuarioAndEstadoPendiente(usuario.getUsername()));
		model.addAttribute("listaReservasPorCliente", resDao.findReservaPorUsuario(usuario.getUsername()));

		return "redirect:/cliente/";
	}

	/**
	 * Este metodo con su anotacion GetMapping actúa como un acceso directo para @RequestMapping (method
	 * = RequestMethod.GET). Cancelamos una reserva y retornamos a la pantalla cliente.
	 * 
	 * @param model Es usado para definir un modelo en una aplicacion Spring MVC
	 * @param idReserva Nos proporciona el idReserva
	 * @return jsp de la pantalla datos del cliente
	 */
	@GetMapping("/cancelarReserva/{idReserva}")
	public String cancelarReserva(Model model, @PathVariable int idReserva) {

		resDao.cancelarReserva(idReserva);
		Usuario usuario = (Usuario) sesion.getAttribute("usuario");
		model.addAttribute("listaReservasPendientes", resDao.findReservasPorUsuarioAndEstadoPendiente(usuario.getUsername()));
		model.addAttribute("listaReservasPorCliente", resDao.findReservaPorUsuario(usuario.getUsername()));
		return "redirect:/cliente/";
	}

	/**
	 * Este metodo con su anotacion GetMapping actúa como un acceso directo para @RequestMapping (method
	 * = RequestMethod.GET). Nos permite acceder a la pantalla vehiculo
	 * @param model Es usado para definir un modelo en una aplicacion Spring MVC
	 * @return jsp de la pantalla vehiculo
	 */
	@GetMapping("/verVehiculo")
	public String verVehiculo(Model model) {
		Usuario usuario = (Usuario) sesion.getAttribute("usuario");
		model.addAttribute("vehiculo", usuario.getVehiculo());

		return "cliente/verVehiculo";
	}

	/**
	 * Este metodo con su anotacion GetMapping actúa como un acceso directo para @RequestMapping (method
	 * = RequestMethod.GET). Eliminamos un vehiculo.
	 * 
	 * @param model     Es usado para definir un modelo en una aplicacion Spring MVC
	 * @param matricula Nos proporciona la matricula del vehiculo a eliminar
	 * @return jsp de la pantalla datos del cliente
	 */
	@GetMapping("/eliminarVehiculo/{matricula}")
	public String eliminarVehiculo(Model model, @PathVariable String matricula) {
		Usuario usuario = (Usuario) sesion.getAttribute("usuario");
		usuario.setVehiculo(null);
		usuDao.modificarDatosCliente(usuario);
		vehDao.eliminarVehiculo(matricula);

		model.addAttribute("listaReservasPendientes", resDao.findReservasPorUsuarioAndEstadoPendiente(usuario.getUsername()));
		model.addAttribute("listaReservasPorCliente", resDao.findReservaPorUsuario(usuario.getUsername()));

		return "redirect:/cliente/";
	}

	/**
	 * Este metodo con su anotacion GetMapping actúa como un acceso directo para @RequestMapping (method
	 * = RequestMethod.GET). Nos permite acceder a la pantalla de añadir vehiculo.
	 * 
	 * @param model Es usado para definir un modelo en una aplicacion Spring MVC
	 * @return jsp del formulario para añadir vehiculo
	 */
	@GetMapping("/anadirVehiculo")
	public String anadirVehiculo(Model model) {
		Usuario usuario = (Usuario) sesion.getAttribute("usuario");

		if (usuario.getVehiculo() == null) {

			return "cliente/anadirVehiculo";
		} else {
			model.addAttribute("vehiculo", usuario.getVehiculo());
			model.addAttribute("mensaje", "El cliente solamente puede tener registrado un vehiculo");
			return "cliente/verVehiculo";
		}
	}

	/**
	 * Este metodo con su anotacion PostMapping actúa como un acceso directo para @RequestMapping
	 * (method = RequestMethod.POST). Con este metodo, recibimos los datos del formulario de añadir
	 * vehiculo y lo añadimos a la base de datos
	 * 
	 * @param model      Es usado para definir un modelo en una aplicacion Spring MVC
	 * @param vehiculo   Nos proporciona el vehiculo
	 * @param idBateria  Nos proporciona la idBateria
	 * @param idConector Nos proporciona el idConector
	 * @return jsp de la pantalla ver vehiculo con el vehiculo actualizado.
	 */

	@PostMapping("/anadirVehiculo")
	public String anadirVehiculo(Model model, Vehiculo vehiculo, @RequestParam int idBateria, @RequestParam int idConector) {
		Usuario usuario = (Usuario) sesion.getAttribute("usuario");

		Bateria bateria = new Bateria();
		Conectore conector = new Conectore();
		bateria = batDao.findBateriaById(idBateria);
		conector = conDao.findConectorById(idConector);
		vehiculo.setConectore(conector);
		vehiculo.setBateria(bateria);
		vehDao.altaVehiculo(vehiculo);
		usuario.setVehiculo(vehiculo);
		usuDao.altaUsuario(usuario);
		model.addAttribute("vehiculo", vehiculo);
		return "redirect:/cliente/verVehiculo";

	}

	/**
	 * Este metodo con su anotacion GetMapping actúa como un acceso directo para @RequestMapping (method
	 * = RequestMethod.GET). Nos permite finalizar una reserva.
	 * 
	 * @param model     Es usado para definir un modelo en una aplicacion Spring MVC
	 * @param idReserva Nos proporciona el idReserva de la reserva a finalizar
	 * @return jsp de la pantalla carrito de la compra
	 */

	@GetMapping("/finalizarReserva/{idReserva}")
	public String finalizarReserva(Model model, @PathVariable int idReserva) {
		Usuario usuario = (Usuario) sesion.getAttribute("usuario");
		// Recuperamos la lista que nos va a dar el numero que tiene el Ver Carrito a su lado
		List<String> numeroCarrito = (List<String>) sesionNumeroCarrito.getAttribute("numeroCarrito");
		if (resDao.findReservaById(idReserva).getFechaServicio().after(new Date())) {
			model.addAttribute("mensajeFinalizar", "No se puede finalizar una reserva antes de su fecha.Solo cancelar");
			model.addAttribute("numeroCarrito", numeroCarrito.size());
			model.addAttribute("listaReservasPendientes", resDao.findReservasPorUsuarioAndEstadoPendiente(usuario.getUsername()));
			model.addAttribute("listaReservasPorCliente", resDao.findReservaPorUsuario(usuario.getUsername()));
			return "redirect:/cliente/";
		} else {
			resDao.findReservaById(idReserva).setEstado("Terminada");
			resDao.modificarReserva(idReserva);
			model.addAttribute("numeroCarrito", numeroCarrito.size());
			model.addAttribute("listaReservasPendientes", resDao.findReservasPorUsuarioAndEstadoPendiente(usuario.getUsername()));
			model.addAttribute("listaReservasPorCliente", resDao.findReservaPorUsuario(usuario.getUsername()));
			return "redirect:/cliente/";
		}

	}
	
	

}

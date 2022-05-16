package fp.charging.points.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fp.charging.points.modelo.beans.Perfile;
import fp.charging.points.modelo.beans.Reserva;
import fp.charging.points.modelo.beans.Usuario;
import fp.charging.points.modelo.dao.IntEstacioneDao;
import fp.charging.points.modelo.dao.IntReservaDao;
import fp.charging.points.modelo.dao.IntUsuarioDao;

/**
 * 
 * Esta clase define el Controlador del Administrador y sus funcionalidades
 * 
 * @author JMTE
 * @since 20/05/2022
 * @version 1.0
 * 
 * 
 * 
 */
@Controller
@RequestMapping("administrador")
public class AdministradorController {

	@Autowired
	private PasswordEncoder pwenco;
	@Autowired

	private IntUsuarioDao usuDao;

	@Autowired
	private IntReservaDao resDao;

	@Autowired
	private IntEstacioneDao estDao;

	/**
	 * Este metodo con su anotacion GetMapping actúa como un acceso directo para @RequestMapping (method
	 * = RequestMethod.GET). Nos permite acceder a la pagina de administrador
	 * 
	 * @param model Es usado para definir un modelo en una aplicacion Spring MVC
	 * @return jsp de la pantalla index con los elementos para administrador
	 */

	@GetMapping("/")
	public String inicio(Model model) {

		model.addAttribute("listaUsuariosUltimos", usuDao.find10UltimosUsuariosRegistrados());
		return "principal/index";
	}

	/**
	 * Este metodo con su anotacion GetMapping actúa como un acceso directo para @RequestMapping (method
	 * = RequestMethod.GET). Nos permite acceder a la pagina para ver los usuarios totales
	 * 
	 * @param model Es usado para definir un modelo en una aplicacion Spring MVC
	 * @return jsp de la pantalla usuarios
	 */
	@GetMapping("/verUsuarios")

	public String verUsuarios(Model model) {

		model.addAttribute("listaUsuarios", usuDao.findAll());
		return "administrador/verUsuarios";
	}

	/**
	 * Este metodo con su anotacion GetMapping actúa como un acceso directo para @RequestMapping (method
	 * = RequestMethod.GET). Nos permite acceder a la pagina clientes totales
	 * @param model Es usado para definir un modelo en una aplicacion Spring MVC
	 * @return jsp de la pantalla con clientes
	 */
	@GetMapping("/verClientes")

	public String verClientes(Model model) {

		model.addAttribute("listaClientes", usuDao.findUsuariosCliente());
		return "administrador/verClientes";
	}

	/**
	 * Este metodo con su anotacion GetMapping actúa como un acceso directo para @RequestMapping (method
	 * = RequestMethod.GET). Nos permite acceder a la pagina las empresas
	 * 
	 * @param model Es usado para definir un modelo en una aplicacion Spring MVC
	 * @return jsp de la pantalla con las empresas
	 */
	@GetMapping("/verEmpresas")

	public String verEmpresas(Model model) {

		model.addAttribute("listaEmpresas", usuDao.findUsuariosEmpresa());
		return "administrador/verEmpresas";
	}

	/**
	 * Este metodo con su anotacion GetMapping actúa como un acceso directo para @RequestMapping (method
	 * = RequestMethod.GET). Nos permite acceder a la pagina de las reservas
	 * 
	 * @param model Es usado para definir un modelo en una aplicacion Spring MVC
	 * @return jsp de la pantalla de las reservas
	 */
	@GetMapping("/verReservas")

	public String verReservas(Model model) {

		model.addAttribute("listaReservas", resDao.findAll());
		return "administrador/verReservas";
	}

	/**
	 * Este metodo con su anotacion GetMapping actúa como un acceso directo para @RequestMapping (method
	 * = RequestMethod.GET). Nos permite acceder a la pagina del historial de un usuario
	 * 
	 * @param model    Es usado para definir un modelo en una aplicacion Spring MVC
	 * @param username Nos proporciona el username del usuario a mostrar historial
	 * @return jsp de la pantalla historial de usuario
	 */
	@GetMapping("/historialUsuario/{username}")

	public String verHistorialUsuario(Model model, @PathVariable String username) {

		Usuario usuario = usuDao.findUsuarioByDni(username);
		BigDecimal totalGastado = new BigDecimal(0);
		if(usuario.getPerfiles().get(0).getIdPerfil()!=1) {
			// Comprobamos si se trata de un usuario cliente o empresa
			if (usuario.getEstacione() == null) {

				// Si es usuario cliente calculamos todo lo que lleva gastado
				for (Reserva e : resDao.findReservasPorUsuarioAndEstadoTerminada(username)) {
					totalGastado = totalGastado.add(e.getPrecioTotal());
				}

				// Mostramos la pantalla de historial usuario
				model.addAttribute("listaReservaPorUsuario", resDao.findReservaPorUsuario(username));
				model.addAttribute("totalGastado", totalGastado);
				return "administrador/historialUsuario";
			} else {
				// Mostramos la pantalla de historial empresa
				int idEstacion = usuDao.findUsuarioByDni(username).getEstacione().getIdEstacion();
				model.addAttribute("listaReservas", resDao.findReservasPorEmpresa(idEstacion));
				return "administrador/historialEmpresa";

			}
		}else {
			model.addAttribute("listaUsuarios", usuDao.findAll());
			return "administrador/verUsuarios";
		}
		

	}

	/**
	 * Este metodo con su anotacion GetMapping actúa como un acceso directo para @RequestMapping (method
	 * = RequestMethod.GET). Nos permite eliminar un usuario
	 * 
	 * @param model    Es usado para definir un modelo en una aplicacion Spring MVC
	 * @param username Nos proporciona el username del usuario a eliminar
	 * @return jsp de la pantalla usuarios totales
	 */
	@GetMapping("/eliminarUsuario/{username}")
	public String eliminarUsuario(Model model, @PathVariable String username) {
		// Tenemos que comprobar que tipo de usuario es.
		for (Perfile e : usuDao.findUsuarioByDni(username).getPerfiles()) {
			// Si el usuario es cliente
			if (e.getIdPerfil() == 3) {
				// Comprobamos si tiene reservas y las eliminamos
				if (resDao.findReservaPorUsuario(username).size() > 1) {
					for (Reserva i : resDao.findReservaPorUsuario(username)) {
						resDao.cancelarReserva(i.getIdReserva());
					}
				}
				// Borramos usuario
				usuDao.borrarUsuario(username);

				model.addAttribute("listaUsuarios", usuDao.findAll());

				// Si el perfil es empresa
			} else if (e.getIdPerfil() == 2) {
				// Eliminamos las reservas de esa estacion
				int idEstacion = usuDao.findUsuarioByDni(username).getEstacione().getIdEstacion();
				if (resDao.findReservasPorEmpresa(idEstacion).size() > 0) {
					for (Reserva a : resDao.findReservasPorEmpresa(idEstacion)) {
						resDao.cancelarReserva(a.getIdReserva());
					}
				}

				// Eliminamos los conectores de esa estacion
				estDao.findEstacionById(idEstacion).setConectores(null);
				// Eliminamos la estacion y el usuario
				usuDao.borrarUsuarioEstacion(username, idEstacion);

				model.addAttribute("listaUsuarios", usuDao.findAll());
				// Si el usuario es administrador y existe mas de un administrador
			} else if (e.getIdPerfil() == 1 && usuDao.findUsuariosAdministradores().size() > 1) {

				usuDao.borrarUsuario(username);

				model.addAttribute("listaUsuarios", usuDao.findAll());

			} else {

				model.addAttribute("mensajeNoEliminar", "No se puede eliminar el único perfil administrador");
				model.addAttribute("listaUsuarios", usuDao.findAll());

			}
		}
		return "/administrador/verUsuarios";

	}

	/**
	 * Este metodo con su anotacion GetMapping actúa como un acceso directo para @RequestMapping (method
	 * = RequestMethod.GET). Nos permite acceder a la pagina de historial de una empresa
	 * 
	 * @param model    Es usado para definir un modelo en una aplicacion Spring MVC
	 * @param username Nos proporciona el username del usuario a mostrar el historial
	 * @return jsp de de la pantalla de historial de esa empresa
	 */

	@GetMapping("/historialEmpresa/{username}")

	public String historialEmpresa(Model model, @PathVariable String username) {
		int idEstacion = usuDao.findUsuarioByDni(username).getEstacione().getIdEstacion();
		model.addAttribute("listaReservas", resDao.findReservasPorEmpresa(idEstacion));
		return "administrador/historialEmpresa";
	}

	/**
	 * Este metodo con su anotacion GetMapping actúa como un acceso directo para @RequestMapping (method
	 * = RequestMethod.GET). Nos permite cancelar una reserva
	 * 
	 * @param model      Es usado para definir un modelo en una aplicacion Spring MVC
	 * @param idReserva  Nos proporciona el idReserva de la reserva a cancelar
	 * @param idEstacion Nos proporciona el idEstacion
	 * @return retornamos a la direccion administrador/verEmpresas
	 */

	@GetMapping("/cancelarReserva/{idReserva}/{idEstacion}")
	public String cancelarReserva(Model model, @PathVariable int idReserva, @PathVariable int idEstacion) {

		resDao.cancelarReserva(idReserva);

		model.addAttribute("listaReservas", resDao.findReservasPorEmpresa(idEstacion));
		return "redirect:/administrador/verEmpresas";
	}

	/**
	 * Este metodo con su anotacion PostMapping actúa como un acceso directo para @RequestMapping
	 * (method = RequestMethod.POST). Con este metodo, traemos los datos del nombre del usuario que
	 * introducimos en el input y lo buscamos
	 * 
	 * @param model  Es usado para definir un modelo en una aplicacion Spring MVC
	 * @param nombre Nos proporciona el nombre del usuario a buscar
	 * @return jsp de la pantalla ver Usuarios con solo las coincidencias con el nombre buscado
	 */

	@PostMapping("/buscarUsuario")
	public String buscarUsuario(Model model, String nombre) {
		System.out.println(nombre);
		System.out.println(usuDao.findUsuariosByNombre(nombre));
		model.addAttribute("listaUsuarios", usuDao.findUsuariosByNombre(nombre));
		return "administrador/verUsuarios";
	}

	/**
	 * Este metodo con su anotacion PostMapping actúa como un acceso directo para @RequestMapping
	 * (method = RequestMethod.POST). Con este metodo, traemos los datos del nombre del cliente que
	 * introducimos en el input y lo buscamos
	 * 
	 * @param model  Es usado para definir un modelo en una aplicacion Spring MVC
	 * @param nombre Nos proporciona el nombre del cliente a buscar
	 * @return jsp de la pantalla ver Clientes con solo las coincidencias con el nombre buscado
	 */
	@PostMapping("/buscarCliente")
	public String buscarCliente(Model model, String nombre) {

		model.addAttribute("listaClientes", usuDao.findClientesByNombre(nombre));
		return "administrador/verClientes";
	}

	/**
	 * Este metodo con su anotacion PostMapping actúa como un acceso directo para @RequestMapping
	 * (method = RequestMethod.POST). Con este metodo, traemos los datos del nombre de la empresa que
	 * introducimos en el input y lo buscamos
	 * 
	 * @param model  Es usado para definir un modelo en una aplicacion Spring MVC
	 * @param nombre Nos proporciona el nombre de la empresa a buscar
	 * @return jsp de la pantalla ver Empresas con solo las coincidencias con el nombre buscado
	 */
	@PostMapping("/buscarEmpresa")
	public String buscarEmpresa(Model model, String nombre) {

		model.addAttribute("listaEmpresas", usuDao.findEmpresasByNombre(nombre));
		return "administrador/verEmpresas";
	}

	/**
	 * Este metodo con su anotacion GetMapping actúa como un acceso directo para @RequestMapping (method
	 * = RequestMethod.GET). Nos permite acceder al formulario para dar de alta un administrador
	 * 
	 * @param model Es usado para definir un modelo en una aplicacion Spring MVC
	 * @return jsp de de la pantalla con el formulario para dar de alta administrador
	 */
	@GetMapping("/altaAdmin")
	public String altaAdmin(Model model) {
		return "administrador/altaAdmin";
	}

	/**
	 * Este metodo con su anotacion PostMapping actúa como un acceso directo para @RequestMapping
	 * (method = RequestMethod.POST). Con este metodo, traemos los datos del administrador que queremos
	 * agregar y lo introducimos en la base de datos
	 * @param model Es usado para definir un modelo en una aplicacion Spring MVC
	 * @param usuario Usuario que será dado de alta como administrador
	 * @return jsp de la pantalla principal del administrador
	 */
	@PostMapping("/altaAdmin")
	public String altaAdmin(Model model, Usuario usuario) {
		Perfile perfil = new Perfile();
		perfil.setIdPerfil(1);
		List<Perfile> listaPerfiles = new ArrayList<Perfile>();
		listaPerfiles.add(perfil);
		usuario.setPerfiles(listaPerfiles);
		usuario.setEnabled(1);
		usuario.setPassword(pwenco.encode(usuario.getPassword()));
		usuario.setFechaRegistro(new Date());
		// Si el usuario ya existe
		if (usuDao.altaUsuario(usuario) == 0) {
			model.addAttribute("mensaje", "Este username ya existe");
			return "administrador/altaAdmin";
		} else {
			// Añadimos el usuario a la BBDD
			usuDao.altaUsuario(usuario);
		}
		model.addAttribute("listaUsuariosUltimos", usuDao.find10UltimosUsuariosRegistrados());
		return "principal/index";
	}

}

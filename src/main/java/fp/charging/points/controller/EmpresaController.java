package fp.charging.points.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fp.charging.points.modelo.beans.Conectore;
import fp.charging.points.modelo.beans.Estacione;
import fp.charging.points.modelo.beans.Reserva;
import fp.charging.points.modelo.beans.Usuario;
import fp.charging.points.modelo.dao.IntConectoreDao;
import fp.charging.points.modelo.dao.IntEstacioneDao;
import fp.charging.points.modelo.dao.IntReservaDao;
import fp.charging.points.modelo.dao.IntUsuarioDao;

/**
 * 
 * Esta clase define el Controlador del usuario Empresa y sus funcionalidades
 * 
 * @author JMTE
 * @since 20/05/2022
 * @version 1.0
 * 
 * 
 * 
 */
@Controller
@RequestMapping("empresa")
public class EmpresaController {

	@Autowired
	private IntReservaDao resDao;

	@Autowired
	private IntUsuarioDao usuDao;

	@Autowired
	private IntEstacioneDao estDao;
	@Autowired
	private IntConectoreDao conDao;

	@Autowired
	private HttpSession sesion;

	/**
	 * Este metodo con su anotacion GetMapping actúa como un acceso directo para @RequestMapping (method
	 * = RequestMethod.GET). Nos permite acceder a la pagina de empresa
	 * 
	 * @param model Es usado para definir un modelo en una aplicacion Spring MVC
	 * @return jsp de la pantalla index con los elementos para empresa
	 */

	@GetMapping("/")

	public String index(Model model) {

		Usuario usuario = (Usuario) sesion.getAttribute("usuario");
		model.addAttribute("listaReservasPendientes", resDao.findReservasPorEstacionAndEstadoPendiente(usuario.getEstacione().getIdEstacion()));
		model.addAttribute("listaReservasTotales", resDao.findReservasPorEmpresa(usuario.getEstacione().getIdEstacion()));

		return "principal/index";
	}

	/**
	 * Este metodo con su anotacion GetMapping actúa como un acceso directo para @RequestMapping (method
	 * = RequestMethod.GET). Nos permite acceder a la pagina de clientes
	 * 
	 * @param model Es usado para definir un modelo en una aplicacion Spring MVC
	 * @return jsp de la pantalla clientes
	 */

	@GetMapping("/verClientes")

	public String verClientes(Model model) {
		Usuario usuario = (Usuario) sesion.getAttribute("usuario");
		model.addAttribute("listaClientes", usuDao.findUsuariosCliente());

		return "empresa/verClientes";
	}

	/**
	 * Este metodo con su anotacion GetMapping actúa como un acceso directo para @RequestMapping (method
	 * = RequestMethod.GET). Nos permite acceder a la pagina de recargas
	 * 
	 * @param model Es usado para definir un modelo en una aplicacion Spring MVC
	 * @return jsp de la pantalla recargas
	 */

	@GetMapping("/verRecargas")

	public String verRecargas(Model model) {
		Usuario usuario = (Usuario) sesion.getAttribute("usuario");
		model.addAttribute("listaReservas", resDao.findReservasPorEmpresa(usuario.getEstacione().getIdEstacion()));

		return "empresa/verRecargas";
	}

	/**
	 * Este metodo con su anotacion GetMapping actúa como un acceso directo para @RequestMapping (method
	 * = RequestMethod.GET). Nos permite acceder a la pagina de puntos de carga en la estacion de la
	 * empresa
	 * 
	 * @param model Es usado para definir un modelo en una aplicacion Spring MVC
	 * @return jsp de la pantalla puntos de carga
	 */

	@GetMapping("/verPuntosCarga")

	public String verPuntosCarga(Model model) {
		Usuario usuario = (Usuario) sesion.getAttribute("usuario");
		List<Conectore> listaConectores = estDao.findEstacionById(usuario.getEstacione().getIdEstacion()).getConectores();

		Estacione estacion = estDao.findEstacionById(usuario.getEstacione().getIdEstacion());
		int puntosCarga = estacion.getNumeroPuntosCarga();

		model.addAttribute("listaConectores", listaConectores);
		return "empresa/verPuntosCarga";
	}

	/**
	 * Este metodo con su anotacion GetMapping actúa como un acceso directo para @RequestMapping (method
	 * = RequestMethod.GET). Nos permite eliminar un conector de la estacion
	 * 
	 * @param model      Es usado para definir un modelo en una aplicacion Spring MVC
	 * @param idConector Nos proporciona el idConector
	 * @return jsp de la pantalla index con los elementos para administrador
	 */

	@GetMapping("/eliminarConector/{idConector}")
	public String eliminarConector(Model model, @PathVariable int idConector) {

		Usuario usuario = (Usuario) sesion.getAttribute("usuario");
		Estacione estacion = estDao.findEstacionById(usuario.getEstacione().getIdEstacion());
		int puntosCarga = estacion.getNumeroPuntosCarga();
		List<Conectore> listaConectores = estacion.getConectores();
		Conectore conector = conDao.findConectorById(idConector);
		if (listaConectores.contains(conector)) {
			listaConectores.remove(conector);
			estacion.setConectores(listaConectores);
			puntosCarga = puntosCarga - 1;
			estacion.setNumeroPuntosCarga(puntosCarga);
			estDao.altaEstacion(estacion);

		}

		model.addAttribute("listaConectores", estDao.findEstacionById(usuario.getEstacione().getIdEstacion()).getConectores());
		return "redirect:/empresa/verPuntosCarga";
	}

	/**
	 * Este metodo con su anotacion GetMapping actúa como un acceso directo para @RequestMapping (method
	 * = RequestMethod.GET). Nos permite acceder a la pagina con el formulario para añadir conector
	 * 
	 * @param model Es usado para definir un modelo en una aplicacion Spring MVC
	 * @return jsp de la pantalla con el formulario para añadir conector
	 */

	@GetMapping("/anadirConector")
	public String anadirConector(Model model) {

		return "empresa/anadirConector";
	}

	/**
	 * Este metodo con su anotacion PostMapping actúa como un acceso directo para @RequestMapping
	 * (method = RequestMethod.POST). Con este metodo, recibimos los datos del formulario de agregar un
	 * conector a la estacion y lo añadimos a la base de datos
	 * 
	 * @param model      Es usado para definir un modelo en una aplicacion Spring MVC
	 * @param idConector Nos proporciona el idConector
	 * @return jsp de la pantalla ver vehiculo con el vehiculo actualizado.
	 */
	@PostMapping("/anadirConector")
	public String anadirConector(Model model, int idConector) {
		Usuario usuario = (Usuario) sesion.getAttribute("usuario");
		// Buscamos la estacion
		Estacione estacion = estDao.findEstacionById(usuario.getEstacione().getIdEstacion());
		// Buscamos su lista de conectores
		List<Conectore> listaConectores = estacion.getConectores();
		// Buscamos el conector que queremos añadir
		Conectore conector = conDao.findConectorById(idConector);

		int puntosCarga = estacion.getNumeroPuntosCarga();
		// Si la lista de conectores es cero, añadimos el conector sin problema
		if (listaConectores.size() == 0) {
			listaConectores.add(conector);
			estacion.setConectores(listaConectores);

			puntosCarga = puntosCarga + 1;

			estacion.setNumeroPuntosCarga(puntosCarga);
			estDao.altaEstacion(estacion);
			// Si la lista de conectores no está vacia, comprobamos si el conector que queremos añadir existe
		} else {
			if (listaConectores.contains(conector)) {
				model.addAttribute("mensajeConector", "El conector ya se encuentra en la estación");
				return "empresa/anadirConector";
			} else {

				listaConectores.add(conector);
				estacion.setConectores(listaConectores);

				puntosCarga = puntosCarga + 1;

				estacion.setNumeroPuntosCarga(puntosCarga);
				estDao.altaEstacion(estacion);

			}

		}

		model.addAttribute("listaConectores", estDao.findEstacionById(usuario.getEstacione().getIdEstacion()).getConectores());
		return "redirect:/empresa/verPuntosCarga";
	}

	/**
	 * Este metodo con su anotacion GetMapping actúa como un acceso directo para @RequestMapping (method
	 * = RequestMethod.GET). Nos permite acceder a la pagina de historial de cliente
	 * 
	 * @param model Es usado para definir un modelo en una aplicacion Spring MVC
	 * @param username Nos proporciona el username para ver el historial
	 * @return jsp de la pantalla con el historial del cliente
	 */
	@GetMapping("/verHistorial/{username}")

	public String verHistorial(Model model, @PathVariable String username) {
		Usuario usuario = (Usuario) sesion.getAttribute("usuario");
		BigDecimal totalGastado = new BigDecimal(0);
		for (Reserva e : resDao.findReservasPorEstacionAndCliente(usuario.getEstacione().getIdEstacion(), username)) {
			if (e.getEstado().equals("Terminada")) {
				totalGastado = totalGastado.add(e.getPrecioTotal());
			}

		}
		model.addAttribute("usuario", usuDao.findUsuarioByDni(username));
		model.addAttribute("totalGastado", totalGastado);
		model.addAttribute("listaReservasPorCliente", resDao.findReservasPorEstacionAndCliente(usuario.getEstacione().getIdEstacion(), username));
		return "empresa/historialCliente";
	}

	/**
	 * Este metodo con su anotacion PostMapping actúa como un acceso directo para @RequestMapping
	 * (method = RequestMethod.POST). Nos permite buscar un cliente por el nombre
	 * 
	 * @param model  Es usado para definir un modelo en una aplicacion Spring MVC
	 * @param nombre Nos proporciona el nombre para buscar
	 * @return jsp de la pantalla ver Clientes solo con el la lista de clientes que coinciden con el
	 *         nombre buscado
	 */
	@PostMapping("/buscarCliente")
	public String buscarCliente(Model model, String nombre) {

		model.addAttribute("listaClientes", usuDao.findClientesByNombre(nombre));
		return "empresa/verClientes";
	}

	/**
	 * Este metodo con su anotacion GetMapping actúa como un acceso directo para @RequestMapping (method
	 * = RequestMethod.GET). Nos permite cancelar una reserva
	 * 
	 * @param model     Es usado para definir un modelo en una aplicacion Spring MVC
	 * @param idReserva Nos proporciona el idEmpresa para cancelar una reserva
	 * @return Nos retorna a la direccion /empresa
	 */
	@GetMapping("/cancelarReserva/{idReserva}")
	public String cancelarReserva(Model model, @PathVariable int idReserva) {

		resDao.cancelarReserva(idReserva);
		Usuario usuario = (Usuario) sesion.getAttribute("usuario");
		model.addAttribute("listaReservasPendientes", resDao.findReservasPorEstacionAndEstadoPendiente(usuario.getEstacione().getIdEstacion()));
		model.addAttribute("listaReservasTotales", resDao.findReservasPorEmpresa(usuario.getEstacione().getIdEstacion()));
		return "redirect:/empresa/";
	}
	

}

package fp.charging.points.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fp.charging.points.modelo.beans.Conectore;
import fp.charging.points.modelo.beans.Reserva;
import fp.charging.points.modelo.beans.Usuario;
import fp.charging.points.modelo.beans.Vehiculo;
import fp.charging.points.modelo.dao.IntConectoreDao;
import fp.charging.points.modelo.dao.IntEstacioneDao;
import fp.charging.points.modelo.dao.IntReservaDao;
import fp.charging.points.modelo.dao.IntUsuarioDao;
import fp.charging.points.modelo.dao.IntVehiculoDao;

@Controller

@RequestMapping("cliente")
public class ClienteController {

	@Autowired
	private IntUsuarioDao usuDao;
	@Autowired
	HttpSession sesion;
	@Autowired
	private IntEstacioneDao estDao;
	@Autowired
	private IntConectoreDao conDao;
	@Autowired
	private IntReservaDao resDao;
	@Autowired
	private IntVehiculoDao vehDao;

	@GetMapping("/")

	public String index(Model model) {
		Usuario usuario = (Usuario) sesion.getAttribute("usuario");
		model.addAttribute("listaReservasPendientes", resDao.findReservasPorUsuarioAndEstadoPendiente(usuario.getUsername()));
		model.addAttribute("listaReservasPorCliente", resDao.findReservaPorUsuario(usuario.getUsername()));
		return "principal/index";
	}

	@GetMapping("/datosCliente")
	public String datosCliente(Model model) {

		Usuario usuario = (Usuario) sesion.getAttribute("usuario");

		model.addAttribute("usuario", usuDao.findUsuarioByDni(usuario.getUsername()));

		return "cliente/datosCliente";
	}
	
	
	
	@PostMapping("/modificarDatosCliente")
	public String modificarDatosCliente(Model model,Usuario usuario) {
		//Usuario usuario=(Usuario) sesion.getAttribute("usuario");
		System.out.println(usuario.getUsername());
		Usuario usuario2=usuDao.findUsuarioByDni(usuario.getUsername());
		usuario2.setPassword(usuario.getPassword());
		usuario2.setNombre(usuario.getNombre());
		usuario2.setApellidos(usuario.getApellidos());
		usuario2.setDireccion(usuario.getDireccion());
		usuDao.modificarDatosCliente(usuario2);
	
		
		return "principal/index";
	}
	
	@PostMapping("/buscarEstacionesLibres")
	public String verEstacionesLibres(Model model, @RequestParam String fecha) throws ParseException {
		//Formateamos la fecha que nos devuelve como String para poder introducirla  como Date
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		Date dataFormateada = formato.parse(fecha);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		
		model.addAttribute("listaEstacionesLibres",estDao.findAll());
		model.addAttribute("fecha", sdf.format(dataFormateada));
		return "cliente/verEstacionesLibres";
	}
	
	@GetMapping("/reservar/{idEstacion}/{idConector}/{fechaServicio}/{horasCarga}")
	public String reservar (Model model , @PathVariable int idEstacion,@PathVariable int idConector, @PathVariable String fechaServicio, @PathVariable BigDecimal horasCarga ) throws ParseException {
		
		Reserva reserva=new Reserva();
		
		reserva.setEstacione(estDao.findEstacionById(idEstacion));
		reserva.setHorasCarga(horasCarga);
		Conectore conector= conDao.findConectorById(idConector);
		reserva.setDescripcion(conector.getNombre());
		SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
		Date dataFormateada = formato.parse(fechaServicio);
		reserva.setFechaServicio(dataFormateada);
		
		model.addAttribute("reserva", reserva);
		return "cliente/nuevaReserva";
	}
	
	@PostMapping("/reservar")
	public String reservar(Model model,Reserva reserva,@RequestParam int idEstacion, @RequestParam String fecha, @RequestParam String horas) throws ParseException {
		
		Usuario usuario = (Usuario) sesion.getAttribute("usuario");
		
		SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
		Date dataFormateada = formato.parse(fecha);
		reserva.setFechaServicio(dataFormateada);
		reserva.setFechaReserva(new Date());
		if(horas.equals("Mañana")) {
			reserva.setHorasCarga(new BigDecimal(1));
		}else {
			reserva.setHorasCarga(new BigDecimal(2));
		}
		reserva.setEstacione(estDao.findEstacionById(idEstacion));
		reserva.setEstado("Pendiente");
		reserva.setUsuario(usuario);
		reserva.setPagado("No");
		
		resDao.altaReserva(reserva);
		
		model.addAttribute("listaReservasPendientes", resDao.findReservasPorUsuarioAndEstadoPendiente(usuario.getUsername()));
		model.addAttribute("listaReservasPorCliente", resDao.findReservaPorUsuario(usuario.getUsername()));
		
		return "redirect:/cliente/";
	}
	
	@GetMapping("/cancelarReserva/{idReserva}")
	public String cancelarReserva(Model model,@PathVariable int idReserva) {
		
		resDao.cancelarReserva(idReserva);
		Usuario usuario = (Usuario) sesion.getAttribute("usuario");
		model.addAttribute("listaReservasPendientes", resDao.findReservasPorUsuarioAndEstadoPendiente(usuario.getUsername()));
		model.addAttribute("listaReservasPorCliente", resDao.findReservaPorUsuario(usuario.getUsername()));
		return "redirect:/cliente/";
	}
	
	@GetMapping("/verVehiculo")
	public String verVehiculo(Model model) {
		Usuario usuario = (Usuario) sesion.getAttribute("usuario");
		model.addAttribute("vehiculo", usuario.getVehiculo());
		return "cliente/verVehiculo";
	}
	
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

}

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
	
	@GetMapping("/")
	
	public String index(Model model) {
		
		Usuario usuario = (Usuario) sesion.getAttribute("usuario");
		model.addAttribute("listaReservasPendientes", resDao.findReservasPorEstacionAndEstadoPendiente(usuario.getEstacione().getIdEstacion()));
		model.addAttribute("listaReservasTotales", resDao.findReservasPorEmpresa(usuario.getEstacione().getIdEstacion()));
		//model.addAttribute("listaRecargasPorEmpresa", resDao.findReservasPorEmpresa(usuario.getEstacione().getIdEstacion()));
		return "principal/index";
	}
	
	@GetMapping("/verClientes")
	
	public String verClientes(Model model) {
		Usuario usuario = (Usuario) sesion.getAttribute("usuario");
		model.addAttribute("listaClientes", usuDao.findUsuariosCliente());
		
		
		return "empresa/verClientes";
	}
	
	
	@GetMapping("/verRecargas")
	
	public String verRecargas(Model model) {
		Usuario usuario = (Usuario) sesion.getAttribute("usuario");
		model.addAttribute("listaReservas", resDao.findReservasPorEmpresa(usuario.getEstacione().getIdEstacion()));
		
		return "empresa/verRecargas";
	}
	@GetMapping("/verPuntosCarga")
	
	public String verPuntosCarga(Model model) {
		Usuario usuario = (Usuario) sesion.getAttribute("usuario");
		List<Conectore> listaConectores=estDao.findEstacionById(usuario.getEstacione().getIdEstacion()).getConectores();
		System.out.println("Tamaño lista conectores " + listaConectores.size());
		Estacione estacion=estDao.findEstacionById(usuario.getEstacione().getIdEstacion());
		int puntosCarga=estacion.getNumeroPuntosCarga();
		System.out.println("Puntos carga antes de añadir :" + puntosCarga);
		model.addAttribute("listaConectores", listaConectores);
		return "empresa/verPuntosCarga";
	}
	@GetMapping("/eliminarConector/{idConector}")
	public String eliminarConector(Model model , @PathVariable int idConector) {
		//CUIDADO, EL USUARIO AL ESTAR EN SESION, NOS TRAE LOS DATOS DE SESION Y NO ACTUALIZAN, UTILIZAR SOLO PARA SACAR LA IDESTACION
		Usuario usuario = (Usuario) sesion.getAttribute("usuario");
		Estacione estacion=estDao.findEstacionById(usuario.getEstacione().getIdEstacion());
		int puntosCarga=estacion.getNumeroPuntosCarga();
		List<Conectore> listaConectores=estacion.getConectores();
		Conectore conector=conDao.findConectorById(idConector);
		if(listaConectores.contains(conector)) {
			listaConectores.remove(conector);
			estacion.setConectores(listaConectores);
			puntosCarga=puntosCarga-1;
			estacion.setNumeroPuntosCarga(puntosCarga);
			estDao.altaEstacion(estacion);
			
		}
		
		model.addAttribute("listaConectores", estDao.findEstacionById(usuario.getEstacione().getIdEstacion()).getConectores());
		return "redirect:/empresa/verPuntosCarga";
	}
	
	@GetMapping ("/anadirConector")
	public String anadirConector(Model model) {
		
		
		return "empresa/anadirConector";
	}
	@PostMapping("/anadirConector")
	public String añadirConector(Model model, int idConector) {
		Usuario usuario = (Usuario) sesion.getAttribute("usuario");
		Estacione estacion=estDao.findEstacionById(usuario.getEstacione().getIdEstacion());
		
		List<Conectore> listaConectores=estacion.getConectores();
		
		Conectore conector=conDao.findConectorById(idConector);
		
		
		int puntosCarga=estacion.getNumeroPuntosCarga();
		System.out.println("Puntos carga antes de añadir :" + puntosCarga);
		if (listaConectores.size()==0) {
			listaConectores.add(conector);
			estacion.setConectores(listaConectores);
			
			puntosCarga=puntosCarga+1;
			
			estacion.setNumeroPuntosCarga(puntosCarga);
			estDao.altaEstacion(estacion);
			
		}else {
			if(listaConectores.contains(conector)) {
				System.out.println("El conector ya esta en la lista");
			}else {
				System.out.println("El conector no esta en la lista");
				listaConectores.add(conector);
				estacion.setConectores(listaConectores);
		
				puntosCarga=puntosCarga+1;
				
				estacion.setNumeroPuntosCarga(puntosCarga);
				estDao.altaEstacion(estacion);
				
			}
			
		}
		
		model.addAttribute("listaConectores",  estDao.findEstacionById(usuario.getEstacione().getIdEstacion()).getConectores());
		return "redirect:/empresa/verPuntosCarga";
	}
	
	@GetMapping ("/verHistorial/{username}")
	
	public String verHistorial(Model model,@PathVariable String username) {
		Usuario usuario = (Usuario) sesion.getAttribute("usuario");
		BigDecimal totalGastado=new BigDecimal(0);
		for(Reserva e:resDao.findReservasPorEstacionAndCliente(usuario.getEstacione().getIdEstacion(), username)) {
			totalGastado=totalGastado.add(e.getPrecioTotal());
			
		}
		model.addAttribute("usuario", usuDao.findUsuarioByDni(username));
		model.addAttribute("totalGastado", totalGastado);
		model.addAttribute("listaReservasPorCliente", resDao.findReservasPorEstacionAndCliente(usuario.getEstacione().getIdEstacion(), username));
		return "empresa/historialCliente";
	}
	
	
	
	

}

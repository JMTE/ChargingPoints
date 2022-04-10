package fp.charging.points.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import fp.charging.points.modelo.beans.Perfile;
import fp.charging.points.modelo.beans.Reserva;
import fp.charging.points.modelo.beans.Usuario;
import fp.charging.points.modelo.dao.IntEstacioneDao;
import fp.charging.points.modelo.dao.IntReservaDao;
import fp.charging.points.modelo.dao.IntUsuarioDao;

@Controller
@RequestMapping("administrador")
public class AdministradorController {
	
	@Autowired
	
	private IntUsuarioDao usuDao;
	
	@Autowired
	private IntReservaDao resDao;
	
	@Autowired
	private IntEstacioneDao estDao;
	
	@GetMapping("/")
	public String inicio(Model model) {
		
		model.addAttribute("listaUsuariosUltimos", usuDao.find10UltimosUsuariosRegistrados());
		return "principal/index";
	}
	
	@GetMapping("/verUsuarios")
	
	public String verUsuarios(Model model) {
		
		model.addAttribute("listaUsuarios", usuDao.findAll());
		return "administrador/verUsuarios";
	}
	@GetMapping("/verClientes")
		
		public String verClientes(Model model) {
			
			model.addAttribute("listaClientes", usuDao.findUsuariosCliente());
			return "administrador/verClientes";
		}
	@GetMapping("/verEmpresas")
	
	public String verEmpresas(Model model) {
		
		model.addAttribute("listaEmpresas", usuDao.findUsuariosEmpresa());
		return "administrador/verEmpresas";
	}
	
	@GetMapping("/verReservas")
	
	public String verReservas(Model model) {
		
		model.addAttribute("listaReservas", resDao.findAll());
		return "administrador/verReservas";
	}
	
	@GetMapping("/historialUsuario/{username}")
	
	public String verHistorialUsuario(Model model, @PathVariable String username) {
		
		
		BigDecimal totalGastado=new BigDecimal(0);
		for(Reserva e:resDao.findReservasPorUsuarioAndEstadoTerminada(username)) {
			totalGastado=totalGastado.add(e.getPrecioTotal());
			
		}
		model.addAttribute("listaReservaPorUsuario", resDao.findReservaPorUsuario(username));
		model.addAttribute("totalGastado", totalGastado);
		return "administrador/historialUsuario";
		
		
	}
	@GetMapping("/eliminarUsuario/{username}")
	public String eliminarUsuario(Model model,@PathVariable String username) {
		
		for(Perfile e:usuDao.findUsuarioByDni(username).getPerfiles()) {
			if (e.getIdPerfil()!=1 || usuDao.findUsuariosAdministradores().size()>1) {
				
				usuDao.borrarUsuario(username);
				
				model.addAttribute("listaUsuarios", usuDao.findAll());
				
				
			}else {
				model.addAttribute("mensaje", "No se puede eliminar el único perfil administrador");
				model.addAttribute("listaUsuarios", usuDao.findAll());
				
				}
		}
		return "redirect:/administrador/verUsuarios";
		
	}
	
	
	

}

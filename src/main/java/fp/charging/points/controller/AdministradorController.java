package fp.charging.points.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fp.charging.points.modelo.dao.IntUsuarioDao;

@Controller
@RequestMapping("administrador")
public class AdministradorController {
	
	@Autowired
	
	private IntUsuarioDao usuDao;
	
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
	
	public String verReservas() {
		
		return "administrador/verReservas";
	}

}

package fp.charging.points.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fp.charging.points.modelo.beans.Usuario;
import fp.charging.points.modelo.dao.IntReservaDao;

@Controller
@RequestMapping("empresa")
public class EmpresaController {
	
	@Autowired
	private IntReservaDao resDao;
	
	@Autowired
	private HttpSession sesion;
	
	@GetMapping("/")
	
	public String index(Model model) {
		
		Usuario usuario = (Usuario) sesion.getAttribute("usuario");
		
		model.addAttribute("listaRecargasPorEmpresa", resDao.findReservasPorEmpresa(usuario.getEstacione().getIdEstacion()));
		return "principal/index";
	}
	
	@GetMapping("/verClientes")
	
	public String verClientes() {
		
		return "empresa/verClientes";
	}
	
	
	@GetMapping("/verRecargas")
	
	public String verRecargas() {
		
		return "empresa/verRecargas";
	}
	
	

}

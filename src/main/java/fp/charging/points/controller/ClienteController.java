package fp.charging.points.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fp.charging.points.modelo.beans.Usuario;
import fp.charging.points.modelo.beans.Vehiculo;
import fp.charging.points.modelo.dao.IntUsuarioDao;

@Controller

@RequestMapping("cliente")
public class ClienteController {

	@Autowired
	private IntUsuarioDao usuDao;
	@Autowired
	HttpSession sesion;

	@GetMapping("/")

	public String index() {

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

}

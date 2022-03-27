package fp.charging.points.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("empresa")
public class EmpresaController {
	
	
	@GetMapping("/")
	
	public String index() {
		
		
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

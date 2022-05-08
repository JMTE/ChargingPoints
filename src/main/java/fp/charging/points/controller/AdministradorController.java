package fp.charging.points.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

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
		
		Usuario usuario = usuDao.findUsuarioByDni(username);
		BigDecimal totalGastado=new BigDecimal(0);
		if(usuario.getEstacione()==null) {
			
			
			for(Reserva e:resDao.findReservasPorUsuarioAndEstadoTerminada(username)) {
				totalGastado=totalGastado.add(e.getPrecioTotal());
			}
			
			model.addAttribute("listaReservaPorUsuario", resDao.findReservaPorUsuario(username));
			model.addAttribute("totalGastado", totalGastado);
			return "administrador/historialUsuario";
		}else {
			int idEstacion=usuDao.findUsuarioByDni(username).getEstacione().getIdEstacion();
			model.addAttribute("listaReservas", resDao.findReservasPorEmpresa(idEstacion));
			return "administrador/historialEmpresa";
			
		}
		
		
		
		
	}
	@GetMapping("/eliminarUsuario/{username}")
	public String eliminarUsuario(Model model,@PathVariable String username) {
		
		for(Perfile e:usuDao.findUsuarioByDni(username).getPerfiles()) {
			//Si el usuario es cliente
			if (e.getIdPerfil()==3) {
				System.out.println("Es cliente");
				if(resDao.findReservaPorUsuario(username).size()>1) {
					for(Reserva i: resDao.findReservaPorUsuario(username)){
						resDao.cancelarReserva(i.getIdReserva());
					}
				}
				
				usuDao.borrarUsuario(username);
				
				model.addAttribute("listaUsuarios", usuDao.findAll());
				//Si el perfil es empresa
				
			}else if(e.getIdPerfil()==2) {
				//Eliminamos las reservas de esa estacion
					int idEstacion=usuDao.findUsuarioByDni(username).getEstacione().getIdEstacion();
					if(resDao.findReservaPorUsuario(username).size()>1) {
						for(Reserva a:resDao.findReservasPorEmpresa(idEstacion)) {
							resDao.cancelarReserva(a.getIdReserva());
						}
					}
					
					//Eliminamos los conectores de esa estacion
					estDao.findEstacionById(idEstacion).setConectores(null);
					//Eliminamos la estacion y el usuario
					usuDao.borrarUsuarioEstacion(username, idEstacion);
					//Si el usuario es administrador
					model.addAttribute("listaUsuarios", usuDao.findAll());
			}else if(e.getIdPerfil()==1 && usuDao.findUsuariosAdministradores().size()>1) {
				
					usuDao.borrarUsuario(username);
				
					model.addAttribute("listaUsuarios", usuDao.findAll());
				
			}else {
				
				model.addAttribute("mensajeNoEliminar", "No se puede eliminar el único perfil administrador");
				model.addAttribute("listaUsuarios", usuDao.findAll());
				
				}
		}
		return "/administrador/verUsuarios";
		
	}
	
	@GetMapping("/historialEmpresa/{username}")
	
	public String historialEmpresa(Model model, @PathVariable String username) {
		int idEstacion=usuDao.findUsuarioByDni(username).getEstacione().getIdEstacion();
		model.addAttribute("listaReservas", resDao.findReservasPorEmpresa(idEstacion));
		return "administrador/historialEmpresa";
	}
	
	@GetMapping("/cancelarReserva/{idReserva}/{idEstacion}")
	public String cancelarReserva(Model model,@PathVariable int idReserva, @PathVariable int idEstacion) {
		
		resDao.cancelarReserva(idReserva);
		
		model.addAttribute("listaReservas", resDao.findReservasPorEmpresa(idEstacion));
		return "redirect:/administrador/verEmpresas";
	}
	
	@PostMapping("/buscarUsuario")
	public String buscarUsuario(Model model, String nombre) {
		System.out.println(nombre);
		System.out.println(usuDao.findUsuariosByNombre(nombre));
		model.addAttribute("listaUsuarios", usuDao.findUsuariosByNombre(nombre));
		return "administrador/verUsuarios";
	}
	@PostMapping("/buscarCliente")
	public String buscarCliente(Model model, String nombre) {
		
		model.addAttribute("listaClientes", usuDao.findClientesByNombre(nombre));
		return "administrador/verClientes";
	}
	@PostMapping("/buscarEmpresa")
	public String buscarEmpresa(Model model, String nombre) {
		
		model.addAttribute("listaEmpresas", usuDao.findEmpresasByNombre(nombre));
		return "administrador/verEmpresas";
	}
	@GetMapping("/altaAdmin")
	public String altaAdmin(Model model) {
		return "administrador/altaAdmin";
	}
	@PostMapping("/altaAdmin")
	public String altaAdmin(Model model , Usuario usuario) {
		Perfile perfil=new Perfile();
		perfil.setIdPerfil(1);
		List<Perfile> listaPerfiles=new ArrayList<Perfile>();
		listaPerfiles.add(perfil);
		usuario.setPerfiles(listaPerfiles);
		usuario.setEnabled(1);
		usuario.setPassword(pwenco.encode(usuario.getPassword()));
		usuario.setFechaRegistro(new Date());
		if(usuDao.altaUsuario(usuario)==0) {
			model.addAttribute("mensaje", "Este username ya existe");
			return "administrador/altaAdmin";
		}else {
			//Añadimos el usuario a la BBDD
			usuDao.altaUsuario(usuario);
		}
		model.addAttribute("listaUsuariosUltimos", usuDao.find10UltimosUsuariosRegistrados());
		return "principal/index";
	}
	
	
	

}

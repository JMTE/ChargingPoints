package fp.charging.points.controller;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import fp.charging.points.modelo.beans.Conectore;
import fp.charging.points.modelo.beans.Estacione;
import fp.charging.points.modelo.beans.Perfile;
import fp.charging.points.modelo.beans.Reserva;
import fp.charging.points.modelo.beans.Usuario;
import fp.charging.points.modelo.dao.IntEstacioneDao;
import fp.charging.points.modelo.dao.IntUsuarioDao;



@Controller
public class HomeController {
	
	
		@Autowired
		private IntUsuarioDao usuDao;
		
		@Autowired
		private IntEstacioneDao estDao;
		
		//Creamos un objeto de PasswordEncoder definido en la clase de DatSecurity para encriptar las contraseñas cuando registramos un usuario
		@Autowired
		private PasswordEncoder pwenco;
		
		//Creamos una listaCarrito que la introduciremos en sesion.
		List<Reserva>listaCarrito=new ArrayList<Reserva>();
		
		//Creamos una lista para el numero que va a aparecer al lado del ver Carrito en el index
		List<String>numeroCarrito=new ArrayList<String>();
	
		
		//Esto lo utilizamos para encriptar las contraseñas que teniamos sin encriptar en la BBDD
		@GetMapping("/pwd")
		@ResponseBody
		public String generarEncriptado() {
		String password = "1234";
		String encriptado = pwenco.encode(password);
		return encriptado;
		}
		
		@GetMapping("/login")
		public String login(Model model) {
			
			return "principal/formLogin";
		}
	
	
		
		@GetMapping("/index")
		
		
		public String index(Authentication aut, Model model, HttpSession misesion, HttpSession listaSesion, HttpSession sesionNumeroCarrito) {
			
			//Introducimos el usuario autentificado en un objeto de la clase usuario que lo buscamos con nuestro metodo de buscar usuario por username
			Usuario usuario = usuDao.findUsuarioByDni(aut.getName());
			
			//Creamos un string rol para definir en que direccion entraremos segun el usuario que introduzcamos 
			String rol =null;
			
				//Introducimos en sesion el usuario , la listaCarrito y  la lista que utilizamos para representar el numero al lado de ver Carrito.
				misesion.setAttribute("usuario", usuario);
				listaSesion.setAttribute("listaCarrito", listaCarrito);
				sesionNumeroCarrito.setAttribute("numeroCarrito", numeroCarrito);
				
			//Buscamos el rol que esta autentificado y lo guardamos en nuestra variable rol
			for (GrantedAuthority ele: aut.getAuthorities()) {
				
				rol=ele.getAuthority();
			}
				
			System.out.println(rol);
			//model.addAttribute("mensaje", aut.getAuthorities());
			
			
			//Dependiendo del usuario y su rol, entraremos en una dirección u otra.
			if (rol.equalsIgnoreCase("CLIEN")) {
				
				return "redirect:cliente/";	
				
			}else if (rol.equalsIgnoreCase("ADMIN")) {
			
			return "redirect:administrador/";
			
			}else if (rol.equalsIgnoreCase("EMPRE")) {
				System.out.println("Estoy aqui");
				return "redirect:empresa/";
				
			}else {
				//y vamos a index con la lista de libros que son novedad
				
				return "principal/index";
			}
			
		
				
	}
		
		
		//Aqui personalizamos nuestra forma de cerrar sesion para volver a la pantalla de inicio de registrar o loguear despues de hacer logout
		@GetMapping("/salir")
		
		public String logout(HttpServletRequest request){
		SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
		logoutHandler.logout(request,null,null);
		//Borramos los items de la lista que contiene los libros del carrito y la lista que tenemos para representar el numero al lado del ver Carrito
		listaCarrito.clear();
		numeroCarrito.clear();
		return "redirect:/index";
		}
		
		
		//Si pulsamos en registro nos lleva a un formulario para registrarnos
		@GetMapping("/registro")
		public String registrar(Model model) {
			
			
			return "principal/registroUsuario";
			 
			
		}
		
		//Mediante el postmapping traemos los datos del usuario que queremos registrar
		@PostMapping("/registro")
		public String proregistrar(Model model, Usuario usuario, @RequestParam int perfil) {
			System.out.println(usuario.getApellidos());
			//Ponemos el enabled del usuario a 1
			usuario.setEnabled(1);
			//Si es empresa, añadimos nuesta estacion a la base de datos
			if(perfil==2) {
				Estacione estacion=new Estacione();
				estacion.setDireccion(usuario.getDireccion());
				estacion.setNumeroPuntosCarga(0);
				List<Conectore>lista=new ArrayList<Conectore>();
				/*Conectore conector=new Conectore();
				conector.setIdConector(1);
				lista.add(conector);*/
				estacion.setConectores(lista);
				estDao.altaEstacion(estacion);
				String direccion=usuario.getDireccion();
				usuario.setEstacione(estDao.findEstacionByDireccion(direccion));
				
			}else {
				
				
			}
			
			
			//Ahora vamos a indicar los perfiles que tiene el nuevo usuario
			List<Perfile> listaPerfilesUsuario= new ArrayList<Perfile>();
			
			Perfile perfilUsuario=new Perfile();
			//Le asignamos perfil cliente
			
			
			perfilUsuario.setIdPerfil(perfil);
			
			
			
			
			listaPerfilesUsuario.add(perfilUsuario);
			
			//Le introducimos al usuario la lista de perfiles que tiene
			usuario.setPerfiles(listaPerfilesUsuario);
			
			
			/*La contraseña encriptada, en tiempo de registrar al usuario, se almacena 
			  también encriptada en la base de datos. */
			usuario.setPassword(pwenco.encode(usuario.getPassword()));
			
			
			//Añadimos la fecha de alta 
			usuario.setFechaRegistro(new Date());
			
		
			if(usuDao.altaUsuario(usuario)==0) {
				model.addAttribute("mensaje", "Este username ya existe");
				return "principal/registroUsuario";
			}else {
				//Añadimos el usuario a la BBDD
				usuDao.altaUsuario(usuario);
				
			
				
				
				return "redirect:/index";
			}
			
			
			
			 
			
		}
		
}
		
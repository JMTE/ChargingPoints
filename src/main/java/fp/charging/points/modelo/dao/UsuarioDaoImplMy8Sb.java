package fp.charging.points.modelo.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fp.charging.points.modelo.beans.Usuario;
import fp.charging.points.repository.IntEstacioneRepository;
import fp.charging.points.repository.IntUsuarioRepository;

@Service
public class UsuarioDaoImplMy8Sb implements IntUsuarioDao {

	@Autowired
	private IntUsuarioRepository usuRepo;
	
	@Autowired
	private IntEstacioneRepository estRepo;
	
	@Override
	public Usuario findUsuarioByDni(String dni) {
		// TODO Auto-generated method stub
		return usuRepo.findById(dni).orElse(null);
	}

	@Override
	public int altaUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		
		int filas=0;
		
		try {
			usuRepo.save(usuario);
			filas=1;
			
			
		}catch (Exception e){
			e.printStackTrace();
		}
		return filas;
	}

	@Override
	public List<Usuario> findAll() {
		// TODO Auto-generated method stub
		return usuRepo.findAll();
	}

	@Override
	public List<Usuario> findUsuariosEmpresa() {
		// TODO Auto-generated method stub
		return usuRepo.findUsuariosEmpresas();
	}

	@Override
	public List<Usuario> findUsuariosCliente() {
		// TODO Auto-generated method stub
		return usuRepo.findUsuariosClientes();
	}

	@Override
	public List<Usuario> find10UltimosUsuariosRegistrados() {
		// TODO Auto-generated method stub
		
		List<Usuario> lista= new ArrayList<Usuario>();
		lista=usuRepo.findAll();
		
	
		Collections.sort(lista, new Comparator<Usuario>() {
		public int compare(Usuario o1, Usuario o2) { 
			return o1.getFechaRegistro().compareTo(o2.getFechaRegistro());
			}

		});

		
		Collections.reverse(lista);
		if (lista.size()>=3) {
			List<Usuario> lista10Ultimos=lista.subList(0, 3);
			
			return lista10Ultimos;
		}else {
			return usuRepo.findAll();
		}
		
	}

	@Override
	public int modificarDatosCliente(Usuario usuario) {
		int filas=0;
		try {
			usuRepo.save(usuario);
			filas=1;
		}catch (Exception e){
			e.printStackTrace();
		}
		return filas;
	}

	@Override
	public int borrarUsuario(String username) {
		System.out.println("entro aqui");
		int filas=0;
		try {
			usuRepo.deleteById(username);
			filas=1;
			
		}catch (Exception e){
			e.printStackTrace();
		}
		return filas;
	}

	@Override
	public List<Usuario> findUsuariosAdministradores() {
		// TODO Auto-generated method stub
		return usuRepo.findUsuariosAdministradores();
	}

	@Override
	public List<Usuario> findUsuariosByNombre(String nombre) {
		// TODO Auto-generated method stub
		return usuRepo.findUsuariosByNombre(nombre);
	}

	@Override
	public List<Usuario> findEmpresasByNombre(String nombre) {
		// TODO Auto-generated method stub
		return usuRepo.findEmpresasByNombre(nombre);
	}

	@Override
	public List<Usuario> findClientesByNombre(String nombre) {
		// TODO Auto-generated method stub
		return usuRepo.findClientesByNombre(nombre);
	}

	@Override
	public int borrarUsuarioEstacion(String username, int idEstacion) {
		// TODO Auto-generated method stub
		int filas=0;
		try {
			usuRepo.deleteById(username);
			estRepo.deleteById(idEstacion);
			filas=1;
			
		}catch (Exception e){
			e.printStackTrace();
		}
		return filas;
	}

}

package fp.charging.points.modelo.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fp.charging.points.modelo.beans.Usuario;
import fp.charging.points.repository.IntUsuarioRepository;

@Service
public class UsuarioDaoImplMy8Sb implements IntUsuarioDao {

	@Autowired
	private IntUsuarioRepository usuRepo;
	
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
		
		List<Usuario> lista10Ultimos=lista.subList(0, 3);
		
		return lista10Ultimos;
	}

}

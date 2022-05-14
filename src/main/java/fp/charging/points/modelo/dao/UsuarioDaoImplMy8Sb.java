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

/**
 * 
 * Esta clase define el Servicio para todos los casos de uso de las reservas
 * 
 * @author JMTE
 * @since 20/05/2022
 * @version 1.0
 * 
 * 
 * 
 */
@Service
public class UsuarioDaoImplMy8Sb implements IntUsuarioDao {

	@Autowired
	private IntUsuarioRepository usuRepo;

	@Autowired
	private IntEstacioneRepository estRepo;

	/**
	 * 
	 * Metodo para buscar usuario por su username
	 * 
	 * @param dni El username del usuario
	 * @return Usuario
	 * 
	 */
	@Override
	public Usuario findUsuarioByDni(String dni) {
		// TODO Auto-generated method stub
		return usuRepo.findById(dni).orElse(null);
	}

	/**
	 * 
	 * Metodo para dar de alta usuario
	 * 
	 * @param usuario El usuario a dar de alta
	 * @return filas
	 * 
	 */
	@Override
	public int altaUsuario(Usuario usuario) {
		// TODO Auto-generated method stub

		int filas = 0;

		try {
			usuRepo.save(usuario);
			filas = 1;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return filas;
	}

	/**
	 * 
	 * Metodo para buscar todos los usuarios
	 * 
	 * @return lista de usuarios
	 * 
	 */
	@Override
	public List<Usuario> findAll() {
		// TODO Auto-generated method stub
		return usuRepo.findAll();
	}

	/**
	 * 
	 * Metodo para buscar usuarios que sean empresa
	 * 
	 * @return lista de Usuarios
	 * 
	 */
	@Override
	public List<Usuario> findUsuariosEmpresa() {
		// TODO Auto-generated method stub
		return usuRepo.findUsuariosEmpresas();
	}

	/**
	 * 
	 * Metodo para buscar usuarios que sean cliente
	 * 
	 * @return lista de Usuarios
	 * 
	 */
	@Override
	public List<Usuario> findUsuariosCliente() {
		// TODO Auto-generated method stub
		return usuRepo.findUsuariosClientes();
	}

	/**
	 * 
	 * Metodo para buscar los ultimos 3 usuarios registrados
	 * 
	 * @return lista de Usuarios
	 * 
	 */
	@Override
	public List<Usuario> find10UltimosUsuariosRegistrados() {
		// TODO Auto-generated method stub

		List<Usuario> lista = new ArrayList<Usuario>();
		lista = usuRepo.findAll();

		Collections.sort(lista, new Comparator<Usuario>() {
			public int compare(Usuario o1, Usuario o2) {
				return o1.getFechaRegistro().compareTo(o2.getFechaRegistro());
			}

		});

		Collections.reverse(lista);
		if (lista.size() >= 3) {
			List<Usuario> lista10Ultimos = lista.subList(0, 3);

			return lista10Ultimos;
		} else {
			return usuRepo.findAll();
		}

	}

	/**
	 * 
	 * Metodo para modificar los datos del usuario cliente
	 * 
	 * @param usuario Usuario a modificar sus datos
	 * @return filas
	 * 
	 */
	@Override
	public int modificarDatosCliente(Usuario usuario) {
		int filas = 0;
		try {
			usuRepo.save(usuario);
			filas = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filas;
	}

	/**
	 * 
	 * Metodo para borrar usuario
	 * 
	 * @param username Usuario a eliminar
	 * @return filas
	 * 
	 */
	@Override
	public int borrarUsuario(String username) {
		System.out.println("entro aqui");
		int filas = 0;
		try {
			usuRepo.deleteById(username);
			filas = 1;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return filas;
	}

	/**
	 * 
	 * Metodo para buscar usuarios Administrador
	 * 
	 * @return Lista de Usuarios
	 * 
	 */
	@Override
	public List<Usuario> findUsuariosAdministradores() {
		// TODO Auto-generated method stub
		return usuRepo.findUsuariosAdministradores();
	}

	/**
	 * 
	 * Metodo para buscar usuarios por nombre
	 * 
	 * @param nombre Nombre del usuario a buscar
	 * @return Lista de Usuarios
	 * 
	 */
	@Override
	public List<Usuario> findUsuariosByNombre(String nombre) {
		// TODO Auto-generated method stub
		return usuRepo.findUsuariosByNombre(nombre);
	}

	/**
	 * 
	 * Metodo para buscar empresas por nombre
	 * 
	 * @param nombre Nombre de la empresa a buscar
	 * @return Lista de Empresas
	 * 
	 */
	@Override
	public List<Usuario> findEmpresasByNombre(String nombre) {
		// TODO Auto-generated method stub
		return usuRepo.findEmpresasByNombre(nombre);
	}

	/**
	 * 
	 * Metodo para buscar clientes por nombre
	 * 
	 * @param nombre Nombre del cliente a buscar
	 * @return Lista de Clientes
	 * 
	 */
	@Override
	public List<Usuario> findClientesByNombre(String nombre) {
		// TODO Auto-generated method stub
		return usuRepo.findClientesByNombre(nombre);
	}

	/**
	 * 
	 * Metodo para borrar Usuario
	 * 
	 * @param username   Username del usuario a borrar
	 * @param idEstacion Estacion a borrar en caso de ser empresa
	 * @return Lista de Usuarios
	 * 
	 */
	@Override
	public int borrarUsuarioEstacion(String username, int idEstacion) {
		// TODO Auto-generated method stub
		int filas = 0;
		try {
			usuRepo.deleteById(username);
			estRepo.deleteById(idEstacion);
			filas = 1;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return filas;
	}

}

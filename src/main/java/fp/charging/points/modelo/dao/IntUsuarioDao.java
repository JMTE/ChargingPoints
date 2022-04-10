package fp.charging.points.modelo.dao;

import java.util.List;

import fp.charging.points.modelo.beans.Usuario;

public interface IntUsuarioDao {
	
	List<Usuario> findAll();

	Usuario findUsuarioByDni(String dni);
	
	int altaUsuario(Usuario usuario);
	
	int borrarUsuario(String username);
	
	List<Usuario> findUsuariosEmpresa();
	
	List<Usuario> findUsuariosCliente();
	
	List<Usuario> findUsuariosAdministradores();
	
	List<Usuario> find10UltimosUsuariosRegistrados();
	
	int modificarDatosCliente(Usuario usuario);
	
}

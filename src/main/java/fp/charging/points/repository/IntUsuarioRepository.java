package fp.charging.points.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fp.charging.points.modelo.beans.Usuario;

public interface IntUsuarioRepository extends JpaRepository<Usuario, String>{
	
	//Buscar usuarios que solo sean empresas con un inner join
		@Query("select DISTINCT u from Usuario u inner join u.perfiles p where p.idPerfil=2")
		List<Usuario>findUsuariosEmpresas();
		
		//Buscar usuarios que solo sean clientes con un inner join
		@Query("select DISTINCT u from Usuario u inner join u.perfiles p where p.idPerfil=3")
		List<Usuario>findUsuariosClientes();

}

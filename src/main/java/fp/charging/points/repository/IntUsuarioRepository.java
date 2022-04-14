package fp.charging.points.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



import fp.charging.points.modelo.beans.Usuario;

public interface IntUsuarioRepository extends JpaRepository<Usuario, String>{
	
	//Buscar usuarios que solo sean empresas con un inner join
		@Query("select DISTINCT u from Usuario u inner join u.perfiles p where p.idPerfil=2")
		List<Usuario>findUsuariosEmpresas();
		
		//Buscar usuarios que solo sean clientes con un inner join
		@Query("select DISTINCT u from Usuario u inner join u.perfiles p where p.idPerfil=3")
		List<Usuario>findUsuariosClientes();
		
		//Buscar usuarios que solo sean administradores con un inner join
		@Query("select DISTINCT u from Usuario u inner join u.perfiles p where p.idPerfil=1")
		List<Usuario>findUsuariosAdministradores();
		
		@Query("select u from Usuario u where u.nombre like  %:nombre% ")
		List<Usuario> findUsuariosByNombre(@Param("nombre") String nombre);
		
		@Query("select DISTINCT u from Usuario u inner join u.perfiles p where p.idPerfil=3 and u.nombre like  %:nombre% ")
		List<Usuario> findClientesByNombre(@Param("nombre") String nombre);
		
		@Query("select DISTINCT u from Usuario u inner join u.perfiles p where p.idPerfil=2 and u.nombre like  %:nombre% ")
		List<Usuario> findEmpresasByNombre(@Param("nombre") String nombre);
		
		
		

}

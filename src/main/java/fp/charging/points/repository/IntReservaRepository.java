package fp.charging.points.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fp.charging.points.modelo.beans.Reserva;

public interface IntReservaRepository extends JpaRepository<Reserva, Integer>{
	
	@Query("select r from Reserva r where estacione.idEstacion=?1")
	public List<Reserva> findReservaPorEstacion(@Param("idEstacion") int idEstacion);
	
	@Query("select r from Reserva r where usuario.username=?1")
	public List<Reserva> findReservaPorUsuario(@Param("username") String username);
	

}

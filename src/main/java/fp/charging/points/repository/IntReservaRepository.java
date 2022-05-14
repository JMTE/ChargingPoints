package fp.charging.points.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fp.charging.points.modelo.beans.Reserva;

public interface IntReservaRepository extends JpaRepository<Reserva, Integer> {
	// Con esta query buscamos reservas por el idEstacion
	@Query("select r from Reserva r where estacione.idEstacion=?1")
	public List<Reserva> findReservaPorEstacion(@Param("idEstacion") int idEstacion);

	// Con esta query buscamos reservas con el username
	@Query("select r from Reserva r where usuario.username=?1")
	public List<Reserva> findReservaPorUsuario(@Param("username") String username);

	// Con esta query buscamos reservas por username y con estado pendiente
	@Query("select r from Reserva r where usuario.username=?1 and estado='Pendiente'")
	public List<Reserva> findReservaPorUsuarioAndEstado(@Param("username") String username);

	// Con esta query buscamos reservas por username y estado terminada
	@Query("select r from Reserva r where usuario.username=?1 and estado='Terminada'")
	public List<Reserva> findReservaPorUsuarioAndEstadoTerminada(@Param("username") String username);

	// Con esta query buscamos reservas por idEstacion y estado pendiente
	@Query("select r from Reserva r where estacione.idEstacion=?1 and estado='Pendiente'")
	public List<Reserva> findReservasPorEstacionAndEstadoPendiente(@Param("idEstacion") int idEstacion);

}

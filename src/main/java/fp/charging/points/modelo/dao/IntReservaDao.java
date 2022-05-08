package fp.charging.points.modelo.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;



import fp.charging.points.modelo.beans.Reserva;

public interface IntReservaDao {
	
	List<Reserva> findAll();
	
	Reserva findReservaById(int idReserva);
	
	List<Reserva> findReservasPorEmpresa(int idEstacion);
	
	List<Reserva> findReservaPorUsuario(String username);
	
	int altaReservas(List<Reserva> reservas);
	
	List<Reserva> findReservasPorUsuarioAndEstadoPendiente(String username);
	
	List<Reserva> findReservasPorEstacionAndEstadoPendiente(int idEstacion);
	
	List<Reserva> findReservasPorUsuarioAndEstadoTerminada(String username);
	
	int cancelarReserva(int idReserva);
	
	int modificarReserva(int idReserva);
	
	List<Reserva> findReservasPorEstacionAndCliente(int idEstacion, String username);
	
	
	int addReservaCarrito(Reserva reserva, List<Reserva> lista);
	
	int findReservaPorEstacionFechaServicioHorasCargaDescripcion(int idEstacion,Date fechaServicio, BigDecimal horasCarga, String descripcion);
	
	

}

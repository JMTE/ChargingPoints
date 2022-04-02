package fp.charging.points.modelo.dao;

import java.util.List;

import fp.charging.points.modelo.beans.Reserva;

public interface IntReservaDao {
	
	List<Reserva> findAll();
	
	List<Reserva> findReservasPorEmpresa(int idEstacion);
	
	List<Reserva> findReservaPorUsuario(String username);
	
	

}

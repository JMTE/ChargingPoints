package fp.charging.points.modelo.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fp.charging.points.modelo.beans.Reserva;
import fp.charging.points.repository.IntReservaRepository;

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
public class ReservaDaoImplMy8Sb implements IntReservaDao {

	@Autowired
	private IntReservaRepository resRepo;

	/**
	 * 
	 * Metodo para buscar todas las reservas
	 * 
	 * @return Lista de todas las reservas
	 * 
	 */
	@Override
	public List<Reserva> findAll() {
		// TODO Auto-generated method stub
		return resRepo.findAll();
	}

	/**
	 * 
	 * Metodo para buscar reservas por su idEstacion
	 * 
	 * @param idEstacion Estacion de la que se quiere encontrar sus reservas
	 * @return lista de Reservas
	 * 
	 */
	@Override
	public List<Reserva> findReservasPorEmpresa(int idEstacion) {
		// TODO Auto-generated method stub
		System.out.println(idEstacion);
		System.out.println(resRepo.findReservaPorEstacion(idEstacion));
		return resRepo.findReservaPorEstacion(idEstacion);
	}

	/**
	 * 
	 * Metodo para buscar reservas por usuario
	 * 
	 * @param username Usuario del que se quieren encontrar las reservas
	 * @return lista de Reservas
	 * 
	 */
	@Override
	public List<Reserva> findReservaPorUsuario(String username) {
		// TODO Auto-generated method stub
		return resRepo.findReservaPorUsuario(username);
	}

	/**
	 * 
	 * Metodo para dar de alta una reserva actualizando la lista de reservas
	 * 
	 * @param reservas Lista de reservas que se quiere añadir
	 * @return filas
	 * 
	 */
	@Override
	public int altaReservas(List<Reserva> reservas) {
		// TODO Auto-generated method stub
		int filas = 0;
		for (Reserva r : reservas) {
			try {
				resRepo.save(r);
				filas = 1;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return filas;
	}

	/**
	 * 
	 * Metodo para buscar reservas por usuario y estado pendiente
	 * 
	 * @param username Usuario del que se quiere buscar las reservas
	 * @return lista de Reservas
	 * 
	 */
	@Override
	public List<Reserva> findReservasPorUsuarioAndEstadoPendiente(String username) {
		// TODO Auto-generated method stub
		return resRepo.findReservaPorUsuarioAndEstado(username);
	}

	/**
	 * 
	 * Metodo para buscar reservas por usuario y estado terminada
	 * 
	 * @param username Usuario del que se quiere buscar las reservas
	 * @return lista de Reservas
	 * 
	 */
	@Override
	public List<Reserva> findReservasPorUsuarioAndEstadoTerminada(String username) {
		// TODO Auto-generated method stub
		return resRepo.findReservaPorUsuarioAndEstadoTerminada(username);
	}

	/**
	 * 
	 * Metodo para eliminar reserva por idReserva
	 * 
	 * @param idReserva Reserva a cancelar
	 * @return filas
	 * 
	 */
	@Override
	public int cancelarReserva(int idReserva) {
		int filas = 0;
		try {
			resRepo.deleteById(idReserva);
			filas = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filas;
	}

	/**
	 * 
	 * Metodo para buscar reservas por su idEstacion y estado pendiente
	 * 
	 * @param idEstacion Estacion de la que buscar sus reservas
	 * @return lista de Reservas
	 * 
	 */
	@Override
	public List<Reserva> findReservasPorEstacionAndEstadoPendiente(int idEstacion) {
		// TODO Auto-generated method stub
		return resRepo.findReservasPorEstacionAndEstadoPendiente(idEstacion);
	}

	/**
	 * 
	 * Metodo para buscar reservas por su idEstacion y por usuario
	 * 
	 * @param idEstacion Estacion para buscar las reservas
	 * @param username   Usuario para buscar las reservas
	 * @return lista de Reservas
	 * 
	 */

	@Override
	public List<Reserva> findReservasPorEstacionAndCliente(int idEstacion, String username) {
		// TODO Auto-generated method stub
		System.out.println(idEstacion + username);
		List<Reserva> lista = new ArrayList<Reserva>();
		for (Reserva ele : resRepo.findAll()) {
			if (ele.getUsuario().getUsername().equals(username) && ele.getEstacione().getIdEstacion() == idEstacion) {
				lista.add(ele);
			}
		}

		return lista;
	}

	/**
	 * 
	 * Metodo para añadir reserva al carrito
	 * 
	 * @param reserva Reserva a añadir al carrito
	 * @param lista   Lista a actualizar del carrito
	 * @return int
	 * 
	 */
	@Override
	public int addReservaCarrito(Reserva reserva, List<Reserva> lista) {
		lista.add(reserva);
		return lista != null ? 1 : 0;

	}

	/**
	 * 
	 * Metodo para buscar reserva por estacion, por fecha de servicio, por franja horaria y por conector
	 * 
	 * @param idEstacion    Estacion de la reserva
	 * @param fechaServicio Fecha del servicio de la reserva
	 * @param horasCarga    Franja horaria de la reserva (mañana/tarde)
	 * @param descripcion   Tipo de conector de la reserva
	 * @return filas
	 * 
	 */

	@Override
	public int findReservaPorEstacionFechaServicioHorasCargaDescripcion(int idEstacion, Date fechaServicio, BigDecimal horasCarga,
			String descripcion) {
		List<Reserva> listaReservaPorEstacion = resRepo.findReservaPorEstacion(idEstacion);
		int filas = 0;
		Reserva reserva = new Reserva();

		for (Reserva ele : listaReservaPorEstacion) {

			if (ele.getFechaServicio().equals(fechaServicio) && ele.getDescripcion().equals(descripcion)
					&& Integer.valueOf(ele.getHorasCarga().intValueExact()).equals(Integer.valueOf(horasCarga.intValueExact()))) {
				filas = 1;

			}
		}

		return filas;
	}

	/**
	 * 
	 * Metodo para buscar una reserva por su idReserva
	 * 
	 * @param idReserva idReserva de la reserva a buscar
	 * @return Reserva
	 * 
	 */
	@Override
	public Reserva findReservaById(int idReserva) {
		// TODO Auto-generated method stub
		return resRepo.findById(idReserva).orElse(null);
	}

	/**
	 * 
	 * Metodo para modificar una reserva por su idReserva
	 * 
	 * @param idReserva idReserva de la reserva a modificar
	 * @return filas
	 * 
	 */
	@Override
	public int modificarReserva(int idReserva) {
		int filas = 0;
		try {
			resRepo.save(resRepo.findById(idReserva).orElse(null));
			filas = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filas;
	}

}

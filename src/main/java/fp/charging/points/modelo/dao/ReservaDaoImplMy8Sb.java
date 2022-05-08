package fp.charging.points.modelo.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fp.charging.points.modelo.beans.Reserva;
import fp.charging.points.repository.IntReservaRepository;

@Service
public class ReservaDaoImplMy8Sb implements IntReservaDao{

	@Autowired
	private IntReservaRepository resRepo;
	
	@Override
	public List<Reserva> findAll() {
		// TODO Auto-generated method stub
		return resRepo.findAll();
	}

	@Override
	public List<Reserva> findReservasPorEmpresa(int idEstacion) {
		// TODO Auto-generated method stub
		System.out.println(idEstacion);
		System.out.println(resRepo.findReservaPorEstacion(idEstacion));
		return resRepo.findReservaPorEstacion(idEstacion);
	}

	@Override
	public List<Reserva> findReservaPorUsuario(String username) {
		// TODO Auto-generated method stub
		return resRepo.findReservaPorUsuario(username);
	}

	@Override
	public int altaReservas(List<Reserva> reservas) {
		// TODO Auto-generated method stub
		int filas=0;
		for(Reserva r:reservas) {
			try {
				resRepo.save(r);
				filas=1;
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		
		return filas;
	}

	@Override
	public List<Reserva> findReservasPorUsuarioAndEstadoPendiente(String username) {
		// TODO Auto-generated method stub
		return resRepo.findReservaPorUsuarioAndEstado(username);
	}
	
	@Override
	public List<Reserva> findReservasPorUsuarioAndEstadoTerminada(String username) {
		// TODO Auto-generated method stub
		return resRepo.findReservaPorUsuarioAndEstadoTerminada(username);
	}

	@Override
	public int cancelarReserva(int idReserva) {
		int filas=0;
		try {
			resRepo.deleteById(idReserva);
			filas=1;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return filas;
	}

	@Override
	public List<Reserva> findReservasPorEstacionAndEstadoPendiente(int idEstacion) {
		// TODO Auto-generated method stub
		return resRepo.findReservasPorEstacionAndEstadoPendiente(idEstacion);
	}

	@Override
	public List<Reserva> findReservasPorEstacionAndCliente(int idEstacion, String username) {
		// TODO Auto-generated method stub
		System.out.println(idEstacion + username);
		List<Reserva> lista=new ArrayList<Reserva>();
		for(Reserva ele:resRepo.findAll()) {
			if(ele.getUsuario().getUsername().equals(username)&&ele.getEstacione().getIdEstacion()==idEstacion) {
				lista.add(ele);
			}
		}
		
		return lista;
	}

	@Override
	public int addReservaCarrito(Reserva reserva, List<Reserva> lista) {
		lista.add(reserva);
		return lista !=null?1:0;
		
	}

	@Override
	public int findReservaPorEstacionFechaServicioHorasCargaDescripcion(int idEstacion, Date fechaServicio, BigDecimal horasCarga, String descripcion) {
		List<Reserva> listaReservaPorEstacion=resRepo.findReservaPorEstacion(idEstacion);
		int filas=0;
		Reserva reserva=new Reserva();
		
		for(Reserva ele:listaReservaPorEstacion) {
			
			if(ele.getFechaServicio().equals(fechaServicio)&&ele.getDescripcion().equals(descripcion)
					&&Integer.valueOf(ele.getHorasCarga().intValueExact()).equals(Integer.valueOf(horasCarga.intValueExact()))) {
					filas=1;
				
				
			}
		}
		
		return filas;
	}

	@Override
	public Reserva findReservaById(int idReserva) {
		// TODO Auto-generated method stub
		return resRepo.findById(idReserva).orElse(null);
	}

	@Override
	public int modificarReserva(int idReserva) {
		int filas=0;
		try {
			resRepo.save(resRepo.findById(idReserva).orElse(null));
			filas=1;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return filas;
	}

	

}

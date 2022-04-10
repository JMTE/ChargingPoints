package fp.charging.points.modelo.dao;

import java.util.ArrayList;
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
	public int altaReserva(Reserva reserva) {
		// TODO Auto-generated method stub
		
		int filas=0;
		try {
			resRepo.save(reserva);
			filas=1;
		}catch(Exception e) {
			e.printStackTrace();
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

}

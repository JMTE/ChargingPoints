package fp.charging.points.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fp.charging.points.modelo.beans.Vehiculo;
import fp.charging.points.repository.IntVehiculoRepository;

@Service
public class VehiculoDaoImplMy8Sb implements IntVehiculoDao{

	@Autowired
	private IntVehiculoRepository vehRepo;
	@Override
	public List<Vehiculo> findAll() {
		// TODO Auto-generated method stub
		return vehRepo.findAll();
	}

	@Override
	public Vehiculo findVehiculoById(String matricula) {
		// TODO Auto-generated method stub
		return vehRepo.findById(matricula).orElse(null);
	}

	@Override
	public int altaVehiculo(Vehiculo vehiculo) {
		int filas=0;
		try {
			vehRepo.save(vehiculo);
			filas=1;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return filas;
	}

	@Override
	public int eliminarVehiculo(String matricula) {
		int filas=0;
		try {
			vehRepo.deleteById(matricula);
			filas=1;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return filas;
		
	}

}

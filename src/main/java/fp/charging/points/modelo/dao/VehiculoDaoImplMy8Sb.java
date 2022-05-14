package fp.charging.points.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fp.charging.points.modelo.beans.Vehiculo;
import fp.charging.points.repository.IntVehiculoRepository;

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
public class VehiculoDaoImplMy8Sb implements IntVehiculoDao {

	@Autowired
	private IntVehiculoRepository vehRepo;

	/**
	 * 
	 * Metodo para buscar todos los vehiculos
	 * 
	 * 
	 * @return Lista de Vehiculos
	 * 
	 */
	@Override
	public List<Vehiculo> findAll() {
		// TODO Auto-generated method stub
		return vehRepo.findAll();
	}

	/**
	 * 
	 * Metodo para buscar vehiculo por su matricula
	 * 
	 * @param matricula La matricula del vehiculo
	 * @return Vehiculo
	 * 
	 */
	@Override
	public Vehiculo findVehiculoById(String matricula) {
		// TODO Auto-generated method stub
		return vehRepo.findById(matricula).orElse(null);
	}

	/**
	 * 
	 * Metodo para dar de alta un vehiculo
	 * 
	 * @param vehiculo El vehiculo a dar de alta
	 * @return filas
	 * 
	 */
	@Override
	public int altaVehiculo(Vehiculo vehiculo) {
		int filas = 0;
		try {
			vehRepo.save(vehiculo);
			filas = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filas;
	}

	/**
	 * 
	 * Metodo para eliminar un vehiculo
	 * 
	 * @param matricula La matricula del vehiculo a eliminar
	 * @return filas
	 * 
	 */
	@Override
	public int eliminarVehiculo(String matricula) {
		int filas = 0;
		try {
			vehRepo.deleteById(matricula);
			filas = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filas;

	}

}

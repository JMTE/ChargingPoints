package fp.charging.points.modelo.dao;

import java.util.List;

import fp.charging.points.modelo.beans.Vehiculo;

public interface IntVehiculoDao {
	
	List<Vehiculo> findAll();
	
	Vehiculo findVehiculoById(String matricula);
	
	int altaVehiculo(Vehiculo vehiculo);
	
	int eliminarVehiculo(String matricula);
	

}

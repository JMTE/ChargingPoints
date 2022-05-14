package fp.charging.points.modelo.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fp.charging.points.modelo.beans.Estacione;
import fp.charging.points.repository.IntEstacioneRepository;

/**
 * 
 * Esta clase define el Servicio para todos los casos de uso de las estaciones
 * 
 * @author JMTE
 * @since 20/05/2022
 * @version 1.0
 * 
 * 
 * 
 */
@Service

public class EstacionesDaoImplMy8Sb implements IntEstacioneDao {

	@Autowired
	IntEstacioneRepository estRepo;

	/**
	 * 
	 * Metodo para buscar todas las estaciones
	 * 
	 * @return Lista de todas las estaciones
	 * 
	 */
	@Override
	public List<Estacione> findAll() {
		// TODO Auto-generated method stub
		return estRepo.findAll();
	}

	/**
	 * 
	 * Metodo para buscar una estacion por su idEstacion
	 * 
	 * @param idEstacion idEstacion de la estacion a buscar
	 * @return Estacione
	 * 
	 */
	@Override
	public Estacione findEstacionById(int idEstacion) {
		// TODO Auto-generated method stub
		return estRepo.findById(idEstacion).orElse(null);
	}

	/**
	 * 
	 * Metodo para buscar una estacion por su direccion
	 * 
	 * @param direccion de la estacion a buscar
	 * @return Estacione
	 * 
	 */
	@Override
	public Estacione findEstacionByDireccion(String direccion) {
		Estacione estacion = new Estacione();
		for (Estacione e : estRepo.findAll()) {
			if (e.getDireccion().equals(direccion)) {
				estacion = e;
			}
		}
		return estacion;
	}

	/**
	 * 
	 * Metodo para dar de alta una estacion
	 * 
	 * @param estacion Estacion a dar de alta
	 * @return filas
	 * 
	 */
	@Override
	public int altaEstacion(Estacione estacion) {
		int filas = 0;
		try {
			estRepo.save(estacion);
			filas = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filas;
	}

	/**
	 * 
	 * Metodo para borrar una estacion
	 * 
	 * @param idEstacion Estacion a eliminar
	 * @return filas
	 * 
	 */
	@Override
	public int borrarEstacion(int idEstacion) {
		int filas = 0;
		try {
			estRepo.deleteById(idEstacion);
			filas = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filas;
	}

}

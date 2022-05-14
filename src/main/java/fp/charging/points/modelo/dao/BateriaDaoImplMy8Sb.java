package fp.charging.points.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fp.charging.points.modelo.beans.Bateria;
import fp.charging.points.repository.IntBateriaRepository;

/**
 * 
 * Esta clase define el Servicio para todos los casos de uso de las baterias
 * 
 * @author JMTE
 * @since 20/05/2022
 * @version 1.0
 * 
 * 
 * 
 */
@Service
public class BateriaDaoImplMy8Sb implements IntBateriaDao {

	@Autowired
	private IntBateriaRepository batRepo;

	/**
	 * 
	 * Metodo para buscar todas las baterias
	 * 
	 * @return Lista de todas las baterias
	 * 
	 */
	@Override
	public List<Bateria> findAll() {
		// TODO Auto-generated method stub
		return batRepo.findAll();
	}

	/**
	 * 
	 * Metodo para buscar una bateria por su idBateria
	 * 
	 * @param idBateria idBateria de la bateria a buscar
	 * @return Bateria
	 * 
	 */
	@Override
	public Bateria findBateriaById(int idBateria) {
		// TODO Auto-generated method stub
		System.out.println(idBateria);
		return batRepo.findById(idBateria).orElse(null);
	}

}

package fp.charging.points.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fp.charging.points.modelo.beans.Conectore;
import fp.charging.points.repository.IntConectoreRepository;

/**
 * 
 * Esta clase define el Servicio para todos los casos de uso de las conectores
 * 
 * @author  JMTE
 * @since 20/05/2022
 * @version 1.0
 * 
 * 
 * 
 */
@Service
public class ConectorDaoImplMy8Sb implements IntConectoreDao {

	@Autowired
	private IntConectoreRepository conRepo;

	/**
	 * 
	 * Metodo para buscar todos los conectores
	 * 
	 * @return Lista de todos los conectores
	 * 
	 */
	@Override
	public List<Conectore> findAll() {
		// TODO Auto-generated method stub
		return conRepo.findAll();
	}

	/**
	 * 
	 * Metodo para buscar un conector por su idConector
	 * 
	 * @param idConector idConector a buscar
	 * @return Conectore
	 * 
	 */
	@Override
	public Conectore findConectorById(int idConector) {
		// TODO Auto-generated method stub
		return conRepo.findById(idConector).orElse(null);
	}

}

package fp.charging.points.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fp.charging.points.modelo.beans.Bateria;
import fp.charging.points.repository.IntBateriaRepository;

@Service
public class BateriaDaoImplMy8Sb implements IntBateriaDao {

	@Autowired
	private IntBateriaRepository batRepo;
	@Override
	public List<Bateria> findAll() {
		// TODO Auto-generated method stub
		return batRepo.findAll();
	}

	@Override
	public Bateria findBateriaById(int idBateria) {
		// TODO Auto-generated method stub
		System.out.println(idBateria);
		return batRepo.findById(idBateria).orElse(null);
	}

}

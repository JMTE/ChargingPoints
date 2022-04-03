package fp.charging.points.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fp.charging.points.modelo.beans.Conectore;
import fp.charging.points.repository.IntConectoreRepository;

@Service
public class ConectorDaoImplMy8Sb implements IntConectoreDao {

	@Autowired
	private IntConectoreRepository conRepo;
	@Override
	public List<Conectore> findAll() {
		// TODO Auto-generated method stub
		return conRepo.findAll();
	}

	@Override
	public Conectore findConectorById(int idConector) {
		// TODO Auto-generated method stub
		return conRepo.findById(idConector).orElse(null);
	}

}

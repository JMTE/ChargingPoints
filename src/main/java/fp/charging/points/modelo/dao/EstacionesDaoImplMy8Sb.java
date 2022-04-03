package fp.charging.points.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fp.charging.points.modelo.beans.Estacione;
import fp.charging.points.repository.IntEstacioneRepository;

@Service
public class EstacionesDaoImplMy8Sb implements IntEstacioneDao{

	@Autowired
	IntEstacioneRepository estRepo;
	@Override
	public List<Estacione> findAll() {
		// TODO Auto-generated method stub
		return estRepo.findAll();
	}
	@Override
	public Estacione findEstacionById(int idEstacion) {
		// TODO Auto-generated method stub
		return estRepo.findById(idEstacion).orElse(null);
	}

}

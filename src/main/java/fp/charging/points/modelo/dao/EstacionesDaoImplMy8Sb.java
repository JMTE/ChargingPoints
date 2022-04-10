package fp.charging.points.modelo.dao;

import java.util.List;

import javax.transaction.Transactional;

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
	@Override
	public Estacione findEstacionByDireccion(String direccion) {
		Estacione estacion=new Estacione();
		for(Estacione e:estRepo.findAll()) {
			if(e.getDireccion().equals(direccion)) {
				estacion=e;
			}
		}
		return estacion;
	}
	@Override
	public int altaEstacion(Estacione estacion) {
		int filas=0;
		try {
			estRepo.save(estacion);
			filas=1;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return filas;
	}
	@Override
	public int borrarEstacion(int idEstacion) {
		int filas=0;
		try {
			estRepo.deleteById(idEstacion);
			filas=1;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return filas;
	}

}

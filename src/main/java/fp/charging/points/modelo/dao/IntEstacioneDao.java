package fp.charging.points.modelo.dao;

import java.util.List;

import fp.charging.points.modelo.beans.Estacione;

public interface IntEstacioneDao {

	List<Estacione> findAll();
	Estacione findEstacionById(int idEstacion);
}

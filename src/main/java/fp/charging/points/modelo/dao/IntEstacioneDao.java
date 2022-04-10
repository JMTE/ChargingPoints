package fp.charging.points.modelo.dao;

import java.util.List;

import fp.charging.points.modelo.beans.Estacione;

public interface IntEstacioneDao {

	List<Estacione> findAll();
	Estacione findEstacionById(int idEstacion);
	Estacione findEstacionByDireccion(String direccion);
	int altaEstacion(Estacione estacion);
	int borrarEstacion(int idEstacion);
}

package fp.charging.points.modelo.dao;

import java.util.List;

import fp.charging.points.modelo.beans.Bateria;

public interface IntBateriaDao {
	
	List<Bateria> findAll();
	
	Bateria findBateriaById(int idBateria);

}

package fp.charging.points.modelo.dao;

import java.util.List;

import fp.charging.points.modelo.beans.Conectore;

public interface IntConectoreDao {
	
	List<Conectore> findAll();
	Conectore findConectorById(int idConector);
	
	

}

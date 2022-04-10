package fp.charging.points.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fp.charging.points.modelo.beans.Estacione;
import fp.charging.points.modelo.beans.Reserva;

public interface IntEstacioneRepository extends JpaRepository<Estacione, Integer>{

	
}

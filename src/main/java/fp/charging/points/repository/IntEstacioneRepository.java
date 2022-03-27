package fp.charging.points.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fp.charging.points.modelo.beans.Estacione;

public interface IntEstacioneRepository extends JpaRepository<Estacione, Integer>{

}

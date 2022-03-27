package fp.charging.points.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fp.charging.points.modelo.beans.Bateria;

public interface IntBateriaRepository extends JpaRepository<Bateria, Integer>{

}

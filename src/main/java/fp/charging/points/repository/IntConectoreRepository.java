package fp.charging.points.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fp.charging.points.modelo.beans.Conectore;

public interface IntConectoreRepository extends JpaRepository<Conectore, Integer> {

}

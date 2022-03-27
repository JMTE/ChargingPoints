package fp.charging.points.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fp.charging.points.modelo.beans.Reserva;

public interface IntPerfileRepository extends JpaRepository<Reserva, Integer>{

}

package fp.charging.points.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fp.charging.points.modelo.beans.Vehiculo;

public interface IntVehiculoRepository extends JpaRepository<Vehiculo, String> {

}

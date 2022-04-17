package fp.charging.points.modelo.beans;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the conectores database table.
 * 
 */
@Entity
@Table(name="conectores")
@NamedQuery(name="Conectore.findAll", query="SELECT c FROM Conectore c")
public class Conectore implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_conector")
	private int idConector;

	private String descripcion;

	private String nombre;

	//bi-directional many-to-one association to Vehiculo
	@OneToMany(mappedBy="conectore")
	private List<Vehiculo> vehiculos;

	public Conectore() {
	}

	public int getIdConector() {
		return this.idConector;
	}

	public void setIdConector(int idConector) {
		this.idConector = idConector;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Vehiculo> getVehiculos() {
		return this.vehiculos;
	}

	public void setVehiculos(List<Vehiculo> vehiculos) {
		this.vehiculos = vehiculos;
	}

	public Vehiculo addVehiculo(Vehiculo vehiculo) {
		getVehiculos().add(vehiculo);
		vehiculo.setConectore(this);

		return vehiculo;
	}

	public Vehiculo removeVehiculo(Vehiculo vehiculo) {
		getVehiculos().remove(vehiculo);
		vehiculo.setConectore(null);

		return vehiculo;
	}

}
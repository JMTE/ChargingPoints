package fp.charging.points.modelo.beans;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the baterias database table.
 * 
 */
@Entity
@Table(name="baterias")
@NamedQuery(name="Bateria.findAll", query="SELECT b FROM Bateria b")
public class Bateria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_bateria")
	private int idBateria;

	@Column(name="ciclo_de_vida")
	private int cicloDeVida;

	@Column(name="densidad_energetica")
	private String densidadEnergetica;

	private String nombre;

	//bi-directional many-to-one association to Vehiculo
	@OneToMany(mappedBy="bateria")
	private List<Vehiculo> vehiculos;

	public Bateria() {
	}

	public int getIdBateria() {
		return this.idBateria;
	}

	public void setIdBateria(int idBateria) {
		this.idBateria = idBateria;
	}

	public int getCicloDeVida() {
		return this.cicloDeVida;
	}

	public void setCicloDeVida(int cicloDeVida) {
		this.cicloDeVida = cicloDeVida;
	}

	public String getDensidadEnergetica() {
		return this.densidadEnergetica;
	}

	public void setDensidadEnergetica(String densidadEnergetica) {
		this.densidadEnergetica = densidadEnergetica;
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
		vehiculo.setBateria(this);

		return vehiculo;
	}

	public Vehiculo removeVehiculo(Vehiculo vehiculo) {
		getVehiculos().remove(vehiculo);
		vehiculo.setBateria(null);

		return vehiculo;
	}

}
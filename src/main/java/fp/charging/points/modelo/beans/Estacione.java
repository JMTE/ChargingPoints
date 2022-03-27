package fp.charging.points.modelo.beans;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the estaciones database table.
 * 
 */
@Entity
@Table(name="estaciones")
@NamedQuery(name="Estacione.findAll", query="SELECT e FROM Estacione e")
public class Estacione implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_estacion")
	private int idEstacion;

	private String direccion;

	@Column(name="numero_puntos_carga")
	private int numeroPuntosCarga;

	//uni-directional many-to-many association to Conectore
	@ManyToMany
	@JoinTable(
		name="conector_en_estacion"
		, joinColumns={
			@JoinColumn(name="id_estacion")
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_conector")
			}
		)
	private List<Conectore> conectores;

	public Estacione() {
	}

	public int getIdEstacion() {
		return this.idEstacion;
	}

	public void setIdEstacion(int idEstacion) {
		this.idEstacion = idEstacion;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getNumeroPuntosCarga() {
		return this.numeroPuntosCarga;
	}

	public void setNumeroPuntosCarga(int numeroPuntosCarga) {
		this.numeroPuntosCarga = numeroPuntosCarga;
	}

	public List<Conectore> getConectores() {
		return this.conectores;
	}

	public void setConectores(List<Conectore> conectores) {
		this.conectores = conectores;
	}

}
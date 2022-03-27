package fp.charging.points.modelo.beans;

import java.io.Serializable;
import javax.persistence.*;


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

}
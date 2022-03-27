package fp.charging.points.modelo.beans;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the vehiculos database table.
 * 
 */
@Entity
@Table(name="vehiculos")
@NamedQuery(name="Vehiculo.findAll", query="SELECT v FROM Vehiculo v")
public class Vehiculo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String matricula;

	private int autonomia;

	private String nombre;

	private BigDecimal potencia;

	//uni-directional many-to-one association to Bateria
	@ManyToOne
	@JoinColumn(name="id_bateria")
	private Bateria bateria;

	//uni-directional many-to-one association to Conectore
	@ManyToOne
	@JoinColumn(name="id_conector")
	private Conectore conectore;

	public Vehiculo() {
	}

	public String getMatricula() {
		return this.matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public int getAutonomia() {
		return this.autonomia;
	}

	public void setAutonomia(int autonomia) {
		this.autonomia = autonomia;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public BigDecimal getPotencia() {
		return this.potencia;
	}

	public void setPotencia(BigDecimal potencia) {
		this.potencia = potencia;
	}

	public Bateria getBateria() {
		return this.bateria;
	}

	public void setBateria(Bateria bateria) {
		this.bateria = bateria;
	}

	public Conectore getConectore() {
		return this.conectore;
	}

	public void setConectore(Conectore conectore) {
		this.conectore = conectore;
	}

}
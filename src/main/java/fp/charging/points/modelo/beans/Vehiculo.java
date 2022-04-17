package fp.charging.points.modelo.beans;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


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

	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="vehiculo")
	private List<Usuario> usuarios;

	//bi-directional many-to-one association to Bateria
	@ManyToOne
	@JoinColumn(name="id_bateria")
	private Bateria bateria;

	//bi-directional many-to-one association to Conectore
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

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario addUsuario(Usuario usuario) {
		getUsuarios().add(usuario);
		usuario.setVehiculo(this);

		return usuario;
	}

	public Usuario removeUsuario(Usuario usuario) {
		getUsuarios().remove(usuario);
		usuario.setVehiculo(null);

		return usuario;
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
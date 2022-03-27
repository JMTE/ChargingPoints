package fp.charging.points.modelo.beans;

import java.io.Serializable;
import javax.persistence.*;


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

}
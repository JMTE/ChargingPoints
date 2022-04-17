package fp.charging.points.modelo.beans;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the reservas database table.
 * 
 */
@Entity
@Table(name="reservas")
@NamedQuery(name="Reserva.findAll", query="SELECT r FROM Reserva r")
public class Reserva implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_reserva")
	private int idReserva;

	private String descripcion;

	private String estado;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_reserva")
	private Date fechaReserva;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_servicio")
	private Date fechaServicio;

	@Column(name="horas_carga")
	private BigDecimal horasCarga;

	private String pagado;

	@Column(name="precio_total")
	private BigDecimal precioTotal;

	//uni-directional many-to-one association to Estacione
	@ManyToOne
	@JoinColumn(name="id_estacion")
	private Estacione estacione;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="username")
	private Usuario usuario;

	public Reserva() {
	}

	public int getIdReserva() {
		return this.idReserva;
	}

	public void setIdReserva(int idReserva) {
		this.idReserva = idReserva;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFechaReserva() {
		return this.fechaReserva;
	}

	public void setFechaReserva(Date fechaReserva) {
		this.fechaReserva = fechaReserva;
	}

	public Date getFechaServicio() {
		return this.fechaServicio;
	}

	public void setFechaServicio(Date fechaServicio) {
		this.fechaServicio = fechaServicio;
	}

	public BigDecimal getHorasCarga() {
		return this.horasCarga;
	}

	public void setHorasCarga(BigDecimal horasCarga) {
		this.horasCarga = horasCarga;
	}

	public String getPagado() {
		return this.pagado;
	}

	public void setPagado(String pagado) {
		this.pagado = pagado;
	}

	public BigDecimal getPrecioTotal() {
		return this.precioTotal;
	}

	public void setPrecioTotal(BigDecimal precioTotal) {
		this.precioTotal = precioTotal;
	}

	public Estacione getEstacione() {
		return this.estacione;
	}

	public void setEstacione(Estacione estacione) {
		this.estacione = estacione;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
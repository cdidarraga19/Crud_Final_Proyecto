package com.sena.disocc.app.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the usuarios database table.
 * 
 */
@Entity
@Table(name="usuarios")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_USUARIO")
	private int idUsuario;

	@Column(name="`CONTRASE√ĎA`")
	private String contrase_ďa;

	private String correo;

	private String direccion;

	@Column(name="NUMERO_DOCUMENTO")
	private int numeroDocumento;

	@Column(name="PRIMER_APELLIDO")
	private String primerApellido;

	@Column(name="PRIMER_NOMBRE")
	private String primerNombre;

	@Column(name="SEGUNDO_APELLIDO")
	private String segundoApellido;

	@Column(name="SEGUNDO_NOMBRE")
	private String segundoNombre;

	private int telefono;

	//bi-directional many-to-one association to Peticione
	@OneToMany(mappedBy="usuario")
	private List<Peticione> peticiones;

	//bi-directional many-to-one association to Respuesta
	@OneToMany(mappedBy="usuario")
	private List<Respuesta> respuestas;

	//bi-directional many-to-one association to Role
	@ManyToOne
	@JoinColumn(name="FK_ID_ROL")
	private Role role;

	//bi-directional many-to-one association to TiposDocumento
	@ManyToOne
	@JoinColumn(name="FK_TIPO_DOCUMENTO")
	private TiposDocumento tiposDocumento;

	//bi-directional many-to-one association to Estado
	@ManyToOne
	@JoinColumn(name="FK_ID_ESTADO")
	private Estado estado;

	//bi-directional many-to-one association to Venta
	@OneToMany(mappedBy="usuario")
	private List<Venta> ventas;

	public Usuario() {
	}

	public int getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getContrase_ďa() {
		return this.contrase_ďa;
	}

	public void setContrase_ďa(String contrase_ďa) {
		this.contrase_ďa = contrase_ďa;
	}

	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getNumeroDocumento() {
		return this.numeroDocumento;
	}

	public void setNumeroDocumento(int numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getPrimerApellido() {
		return this.primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getPrimerNombre() {
		return this.primerNombre;
	}

	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	public String getSegundoApellido() {
		return this.segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public String getSegundoNombre() {
		return this.segundoNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public int getTelefono() {
		return this.telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public List<Peticione> getPeticiones() {
		return this.peticiones;
	}

	public void setPeticiones(List<Peticione> peticiones) {
		this.peticiones = peticiones;
	}

	public Peticione addPeticione(Peticione peticione) {
		getPeticiones().add(peticione);
		peticione.setUsuario(this);

		return peticione;
	}

	public Peticione removePeticione(Peticione peticione) {
		getPeticiones().remove(peticione);
		peticione.setUsuario(null);

		return peticione;
	}

	public List<Respuesta> getRespuestas() {
		return this.respuestas;
	}

	public void setRespuestas(List<Respuesta> respuestas) {
		this.respuestas = respuestas;
	}

	public Respuesta addRespuesta(Respuesta respuesta) {
		getRespuestas().add(respuesta);
		respuesta.setUsuario(this);

		return respuesta;
	}

	public Respuesta removeRespuesta(Respuesta respuesta) {
		getRespuestas().remove(respuesta);
		respuesta.setUsuario(null);

		return respuesta;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public TiposDocumento getTiposDocumento() {
		return this.tiposDocumento;
	}

	public void setTiposDocumento(TiposDocumento tiposDocumento) {
		this.tiposDocumento = tiposDocumento;
	}

	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public List<Venta> getVentas() {
		return this.ventas;
	}

	public void setVentas(List<Venta> ventas) {
		this.ventas = ventas;
	}

	public Venta addVenta(Venta venta) {
		getVentas().add(venta);
		venta.setUsuario(this);

		return venta;
	}

	public Venta removeVenta(Venta venta) {
		getVentas().remove(venta);
		venta.setUsuario(null);

		return venta;
	}

}
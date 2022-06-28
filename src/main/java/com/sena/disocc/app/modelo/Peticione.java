package com.sena.disocc.app.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the peticiones database table.
 * 
 */
@Entity
@Table(name="peticiones")
@NamedQuery(name="Peticione.findAll", query="SELECT p FROM Peticione p")
public class Peticione implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_PETICION")
	private int idPeticion;

	private String descripcion;

	//bi-directional many-to-one association to TipoPeticione
	@ManyToOne
	@JoinColumn(name="FK_ID_TIPO_PETICION")
	private TipoPeticione tipoPeticione;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="FK_ID_USUARIO")
	private Usuario usuario;

	//bi-directional many-to-one association to Respuesta
	@OneToMany(mappedBy="peticione")
	private List<Respuesta> respuestas;

	public Peticione() {
	}

	public int getIdPeticion() {
		return this.idPeticion;
	}

	public void setIdPeticion(int idPeticion) {
		this.idPeticion = idPeticion;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public TipoPeticione getTipoPeticione() {
		return this.tipoPeticione;
	}

	public void setTipoPeticione(TipoPeticione tipoPeticione) {
		this.tipoPeticione = tipoPeticione;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Respuesta> getRespuestas() {
		return this.respuestas;
	}

	public void setRespuestas(List<Respuesta> respuestas) {
		this.respuestas = respuestas;
	}

	public Respuesta addRespuesta(Respuesta respuesta) {
		getRespuestas().add(respuesta);
		respuesta.setPeticione(this);

		return respuesta;
	}

	public Respuesta removeRespuesta(Respuesta respuesta) {
		getRespuestas().remove(respuesta);
		respuesta.setPeticione(null);

		return respuesta;
	}

}
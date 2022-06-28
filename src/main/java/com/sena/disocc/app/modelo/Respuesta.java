package com.sena.disocc.app.modelo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the respuestas database table.
 * 
 */
@Entity
@Table(name="respuestas")
@NamedQuery(name="Respuesta.findAll", query="SELECT r FROM Respuesta r")
public class Respuesta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_RESPUESTA")
	private int idRespuesta;

	private String descripcion;

	//bi-directional many-to-one association to Peticione
	@ManyToOne
	@JoinColumn(name="FK_ID_PETICION")
	private Peticione peticione;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="FK_ID_USUARIO")
	private Usuario usuario;

	public Respuesta() {
	}

	public int getIdRespuesta() {
		return this.idRespuesta;
	}

	public void setIdRespuesta(int idRespuesta) {
		this.idRespuesta = idRespuesta;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Peticione getPeticione() {
		return this.peticione;
	}

	public void setPeticione(Peticione peticione) {
		this.peticione = peticione;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
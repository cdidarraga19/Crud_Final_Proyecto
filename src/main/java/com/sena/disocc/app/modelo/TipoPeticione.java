package com.sena.disocc.app.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tipo_peticiones database table.
 * 
 */
@Entity
@Table(name="tipo_peticiones")
@NamedQuery(name="TipoPeticione.findAll", query="SELECT t FROM TipoPeticione t")
public class TipoPeticione implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_TIPO_PETICION")
	private int idTipoPeticion;

	private String nombre;

	//bi-directional many-to-one association to Peticione
	@OneToMany(mappedBy="tipoPeticione")
	private List<Peticione> peticiones;

	public TipoPeticione() {
	}

	public int getIdTipoPeticion() {
		return this.idTipoPeticion;
	}

	public void setIdTipoPeticion(int idTipoPeticion) {
		this.idTipoPeticion = idTipoPeticion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Peticione> getPeticiones() {
		return this.peticiones;
	}

	public void setPeticiones(List<Peticione> peticiones) {
		this.peticiones = peticiones;
	}

	public Peticione addPeticione(Peticione peticione) {
		getPeticiones().add(peticione);
		peticione.setTipoPeticione(this);

		return peticione;
	}

	public Peticione removePeticione(Peticione peticione) {
		getPeticiones().remove(peticione);
		peticione.setTipoPeticione(null);

		return peticione;
	}

}
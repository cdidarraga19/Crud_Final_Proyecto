package com.sena.disocc.app.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tipos_documentos database table.
 * 
 */
@Entity
@Table(name="tipos_documentos")
@NamedQuery(name="TiposDocumento.findAll", query="SELECT t FROM TiposDocumento t")
public class TiposDocumento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_TIPO_DOCUMENTO")
	private int idTipoDocumento;

	@Column(name="NOMBRE_DOCUMENTO")
	private String nombreDocumento;

	private String siglas;

	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="tiposDocumento")
	private List<Usuario> usuarios;

	public TiposDocumento() {
	}

	public int getIdTipoDocumento() {
		return this.idTipoDocumento;
	}

	public void setIdTipoDocumento(int idTipoDocumento) {
		this.idTipoDocumento = idTipoDocumento;
	}

	public String getNombreDocumento() {
		return this.nombreDocumento;
	}

	public void setNombreDocumento(String nombreDocumento) {
		this.nombreDocumento = nombreDocumento;
	}

	public String getSiglas() {
		return this.siglas;
	}

	public void setSiglas(String siglas) {
		this.siglas = siglas;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario addUsuario(Usuario usuario) {
		getUsuarios().add(usuario);
		usuario.setTiposDocumento(this);

		return usuario;
	}

	public Usuario removeUsuario(Usuario usuario) {
		getUsuarios().remove(usuario);
		usuario.setTiposDocumento(null);

		return usuario;
	}

}
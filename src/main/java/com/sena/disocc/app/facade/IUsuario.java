package com.sena.disocc.app.facade;

import java.util.List;
import com.sena.disocc.app.modelo.Usuario;
public interface IUsuario {

	public List<Usuario> findAll();
	public Usuario findById (int idUsuario);
	public void CrearUsu (Usuario usuario);
	public void ActualizarUsu (Usuario usuario);
	public void EliminarUsu (int idUsuario);
}

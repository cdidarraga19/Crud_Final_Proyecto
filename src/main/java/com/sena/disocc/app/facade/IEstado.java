package com.sena.disocc.app.facade;

import java.util.List;

import com.sena.disocc.app.modelo.Estado;

public interface IEstado {

	public List<Estado> findAll();
	public Estado findById (int idEstado);
	public void CrearEstado (Estado estado);
	public void ActualizarEstado (Estado estado);
	public void EliminarEstado (int idEstado);

}

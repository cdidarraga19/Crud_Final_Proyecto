package com.sena.disocc.app.facade;

import java.util.List;

import com.sena.disocc.app.modelo.Permiso;

public interface IPermiso {
	
	public List<Permiso> findAll ();
	public Permiso finById (int idPermiso);
	public void crearPermiso (Permiso permiso);
	public void actualizarPermiso (Permiso permiso);
	public void eliminarPermiso (int idPermiso);
}

package com.sena.disocc.app.facade;

import java.util.List;

import com.sena.disocc.app.modelo.Role;

public interface IRol {

	public List<Role> findAll();
	public Role findById (int idRol);
	public void CrearRole (Role rol);
	public void ActualizarRole (Role rol);
	public void EliminarRole (int idRol);

}

package com.sena.disocc.app.facade;

import java.util.List;

import com.sena.disocc.app.modelo.Proveedore;

public interface IProveedor {
	
	public List<Proveedore> findAll();
	public Proveedore findById (int idProveedor);
	public void CrearProveedore (Proveedore proveedor);
	public void ActualizarProveedore (Proveedore proveedor);
	public void EliminarProveedore (int idProveedor);
}

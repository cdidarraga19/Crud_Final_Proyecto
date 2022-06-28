package com.sena.disocc.app.facade;

import java.util.List;

import com.sena.disocc.app.modelo.Inventario;

public interface IInventario {
	public List<Inventario> findAll();
	public Inventario findById (int idInventario);
	public void CrearInventario (Inventario inventario);
	public void ActualizarInventario (Inventario inventario);
	public void EliminarInventario (int idInventario);
}


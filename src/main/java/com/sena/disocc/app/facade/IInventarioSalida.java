package com.sena.disocc.app.facade;

import java.util.List;

import com.sena.disocc.app.modelo.InventarioSalida;

public interface IInventarioSalida {
	public List<InventarioSalida> findAll();
	public InventarioSalida findById (int id);
	public void CrearInventarioSalida (InventarioSalida inventariosalida);
	public void ActualizarInventarioSalida (InventarioSalida inventariosalida);
	public void EliminarInventarioSalida (int id);
}

package com.sena.disocc.app.facade;

import java.util.List;

import com.sena.disocc.app.modelo.InventarioEntrada;

public interface IInventarioEntrada {
	public List<InventarioEntrada> findAll();
	public InventarioEntrada findById (int id);
	public void CrearInventarioEntrada (InventarioEntrada inventarioentrada);
	public void ActualizarInventarioEntrada (InventarioEntrada inventarioentrada);
	public void EliminarInventarioEntrada (int id);
}

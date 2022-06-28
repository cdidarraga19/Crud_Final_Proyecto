package com.sena.disocc.app.facade;

import java.util.List;

import com.sena.disocc.app.modelo.Compra;

public interface ICompra {
	public List<Compra> findAll();
	public Compra findById(int idCompra);
	public void CrearCompra(Compra compra);
	public void ActualizarCompra(Compra compra);
	public void EliminarCompra(int idCompra);
}

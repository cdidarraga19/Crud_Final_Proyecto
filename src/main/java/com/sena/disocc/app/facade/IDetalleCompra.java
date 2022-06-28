package com.sena.disocc.app.facade;

import java.util.List;

import com.sena.disocc.app.modelo.DetallesCompra;

public interface IDetalleCompra {
	public List<DetallesCompra> findAll();
	public DetallesCompra findById(int id);
	public void CrearDetalleCompra(DetallesCompra detallescompra);
	public void ActualizarDetalleCompra(DetallesCompra detallescompra);
	public void EliminarDetalleCompra(int id);
}

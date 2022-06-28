package com.sena.disocc.app.facade;

import java.util.List;

import com.sena.disocc.app.modelo.Venta;

public interface IVenta {
	public List<Venta> findAll();
	public Venta findById(int idVenta);
	public void CrearVenta (Venta venta);
	public void ActualizarVenta (Venta venta);
	public void EliminarVenta (int idVenta);
}

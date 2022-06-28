package com.sena.disocc.app.facade;

import java.util.List;

import com.sena.disocc.app.modelo.DetallesVenta;

public interface IDetalleVenta {
	public List<DetallesVenta> findAll();
	public DetallesVenta findById(int idDetalleVenta);
	public void CrearDetalleVenta(DetallesVenta detalleventa);
	public void ActualizarDetalleVenta(DetallesVenta detalleventa);
	public void EliminarDetalleVenta(int idDetalleVenta);
}

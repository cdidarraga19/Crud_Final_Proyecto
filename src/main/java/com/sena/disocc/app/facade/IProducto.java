package com.sena.disocc.app.facade;

import java.util.List;

import com.sena.disocc.app.modelo.Producto;

public interface IProducto {

	List<Producto> finAll();
	public Producto findById (int idProducto);
	public void crearProducto (Producto producto);
	public void actualizarProducto (Producto producto);
	public void eliminarProducto (int idProducto);
	
}

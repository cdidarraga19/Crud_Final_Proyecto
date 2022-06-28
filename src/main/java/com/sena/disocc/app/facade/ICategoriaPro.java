package com.sena.disocc.app.facade;

import java.util.List;
import com.sena.disocc.app.modelo.CategoriasProducto;

public interface ICategoriaPro {
	
	public List<CategoriasProducto> findAll();
	public CategoriasProducto findById(int idCategoria);
	public void crearCategoriaP (CategoriasProducto categoriaP);
	public void actualizarCategoriaP (CategoriasProducto categoriaP);
	public void eliminarCategoriaP (int idCategoria);

}

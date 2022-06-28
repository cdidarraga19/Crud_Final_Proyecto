package com.sena.disocc.app.facade;

import java.util.List;
import com.sena.disocc.app.modelo.Marca;

public interface IMarca {

	List<Marca> findAll();
	public Marca  finById(int idMarca);
	public void crearMarca (Marca marca);
	public void actualizarMarca (Marca marca);
	public void eliminarMarca (int idMarca);
}

package com.sena.disocc.app.facade;

import java.util.List;

import com.sena.disocc.app.modelo.Peticione;
public interface IPeticion {
	
	public List<Peticione> findAll();
	public Peticione findById(int idPeticion);
	public void CrearPeticion (Peticione peticion);
	public void ActualizarPeticion (Peticione peticion);
	public void EliminarPeticion (int idPeticion);
	

}

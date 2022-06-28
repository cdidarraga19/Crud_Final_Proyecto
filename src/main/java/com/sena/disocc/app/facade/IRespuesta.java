package com.sena.disocc.app.facade;

import java.util.List;

import com.sena.disocc.app.modelo.Respuesta;

public interface IRespuesta {
	
	public List<Respuesta> findAll();
	public Respuesta findById (int idRespuesta);
	public void crearRespuesta(Respuesta respuesta);
	public void actualizaRespuesta (Respuesta respuesta);
	public void eliminarRespuesta (int idRespuesta);

}

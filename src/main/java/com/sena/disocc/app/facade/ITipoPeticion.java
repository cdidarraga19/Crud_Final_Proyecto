package com.sena.disocc.app.facade;

import java.util.List;

import com.sena.disocc.app.modelo.TipoPeticione;

public interface ITipoPeticion {
	
	public List<TipoPeticione> findAll();
	public TipoPeticione findById (int idTipoPeticion );
	public  void CrearTipoPeticion (TipoPeticione tipoPeticion);
	public void ActualizarTipoPeticion (TipoPeticione tipoPeticion);
	public void EliminarTipoPeticion (int idTipoPeticion);
	

}

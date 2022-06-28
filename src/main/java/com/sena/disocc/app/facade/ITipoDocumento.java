package com.sena.disocc.app.facade;

import java.util.List;

import com.sena.disocc.app.modelo.TiposDocumento;

public interface ITipoDocumento {
	
	public List<TiposDocumento> findAll();
	public TiposDocumento findById (int idTipoDocumento);
	public void CrearTipoDoc (TiposDocumento tipoD);
	public void ActualizarTipoDoc (TiposDocumento tipoD);
	public void EliminarTipoDoc (int idTipoDocumento);
	

}

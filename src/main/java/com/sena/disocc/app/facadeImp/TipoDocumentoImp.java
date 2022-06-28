package com.sena.disocc.app.facadeImp;

import java.util.*;

import javax.persistence.*;

import com.sena.disocc.app.utilities.JPAUtil;
import com.sena.disocc.app.facade.ITipoDocumento;
import com.sena.disocc.app.modelo.*;

public class TipoDocumentoImp implements ITipoDocumento{
	
	EntityManager entity = JPAUtil.getEntityManagerFactory().createEntityManager();
	
	@Override
	public List<TiposDocumento> findAll() {
		List<TiposDocumento> listaTipoDocumento = new ArrayList <>();
		try {
		Query q =this.entity.createQuery("SELECT tp FROM TiposDocumento tp");
		listaTipoDocumento = q.getResultList();
		return listaTipoDocumento;
		} catch(Exception e) {
			JPAUtil.shutdown();
		}
		return listaTipoDocumento;

	}
	
	@Override
	public void CrearTipoDoc (TiposDocumento tipodoc) {
		try {
		this.entity.getTransaction().begin();
		this.entity.persist(tipodoc);
		this.entity.getTransaction().commit();
		}catch (Exception e) {
			JPAUtil.shutdown();
		}
		
		
	}
	
	@Override
	public void ActualizarTipoDoc (TiposDocumento tipodoc) {
		try {
		this.entity.getTransaction().begin();
		this.entity.merge(tipodoc);
		this.entity.getTransaction().commit();
		}catch (Exception e) {
			JPAUtil.shutdown();
		}
	}
	
	@Override
	public void EliminarTipoDoc(int idTipoDocumento) {
		try {
			TiposDocumento tipoD =new TiposDocumento();
			tipoD =this.entity.find(TiposDocumento.class, idTipoDocumento);
			
			this.entity.getTransaction().begin();
			this.entity.remove(tipoD);
			this.entity.getTransaction().commit();
		}catch (Exception e) {
			JPAUtil.shutdown();
		}
	}
	
	@Override
	public TiposDocumento findById (int idTipoDocumento) {
		
		TiposDocumento tipoD =new TiposDocumento();
		tipoD =this.entity.find(TiposDocumento.class, idTipoDocumento);
		return tipoD;
	}
	

}

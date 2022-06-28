package com.sena.disocc.app.facadeImp;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.sena.disocc.app.facade.ITipoPeticion;
import com.sena.disocc.app.modelo.TipoPeticione;
import com.sena.disocc.app.utilities.JPAUtil;

public class TipoPeticionImp implements ITipoPeticion {

	EntityManager entity = JPAUtil.getEntityManagerFactory().createEntityManager();

	
	@Override
	public List<TipoPeticione> findAll() {
		List<TipoPeticione> listaTipoPeticion = new ArrayList<TipoPeticione>();
		Query q = this.entity.createQuery("SELECT usu FROM TipoPeticione usu");
		listaTipoPeticion = q.getResultList();
		return listaTipoPeticion;
	}

	@Override
	public TipoPeticione findById(int idTipoPeticion) {
		TipoPeticione tipoPeticion = new TipoPeticione();
		tipoPeticion = this.entity.find(TipoPeticione.class, idTipoPeticion);
		return tipoPeticion;
	}

	@Override
	public void CrearTipoPeticion(TipoPeticione tipoPeticion) {
		try {
			this.entity.getTransaction().begin();
			this.entity.persist(tipoPeticion);
			this.entity.getTransaction().commit();
		} catch (Exception e) {
			JPAUtil.shutdown();
		}

	}

	@Override
	public void ActualizarTipoPeticion(TipoPeticione tipoPeticion) {
		try {
			this.entity.getTransaction().begin();
			this.entity.merge(tipoPeticion);
			this.entity.getTransaction().commit();
		} catch (Exception e) {
			JPAUtil.shutdown();
		}

	}

	@Override
	public void EliminarTipoPeticion(int idTipoPeticion) {
		try {
			TipoPeticione tipoPeticion = new TipoPeticione();
			tipoPeticion=this.entity.find(TipoPeticione.class, idTipoPeticion);
			
			this.entity.getTransaction().begin();
			this.entity.remove(tipoPeticion);
			this.entity.getTransaction().commit();
		} catch (Exception e) {
			JPAUtil.shutdown();
		}

	}

}

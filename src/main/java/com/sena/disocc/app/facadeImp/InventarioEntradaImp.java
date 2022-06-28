package com.sena.disocc.app.facadeImp;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.sena.disocc.app.facade.IInventarioEntrada;
import com.sena.disocc.app.modelo.InventarioEntrada;
import com.sena.disocc.app.utilities.JPAUtil;

public class InventarioEntradaImp implements IInventarioEntrada {
	
	EntityManager entity = JPAUtil.getEntityManagerFactory().createEntityManager();

	@Override
	public List<InventarioEntrada> findAll() {
		List<InventarioEntrada> listaInventarioEntrada = new ArrayList<>();
		Query q = this.entity.createQuery("SELECT i FROM InventarioEntrada i");
		listaInventarioEntrada = q.getResultList();
		return listaInventarioEntrada;
	}

	@Override
	public InventarioEntrada findById(int id) {
		InventarioEntrada inventarioEntrada = new InventarioEntrada();
		inventarioEntrada = this.entity.find(InventarioEntrada.class, id);
		return inventarioEntrada;
	}

	@Override
	public void CrearInventarioEntrada(InventarioEntrada inventarioentrada) {
		try {
			this.entity.getTransaction().begin();
			this.entity.persist(inventarioentrada);
			this.entity.getTransaction().commit();
		} catch (Exception e) {
			JPAUtil.shutdown();
		}
		
	}

	@Override
	public void ActualizarInventarioEntrada(InventarioEntrada inventarioentrada) {
		try {
			this.entity.getTransaction().begin();
			this.entity.merge(inventarioentrada);
			this.entity.getTransaction().commit();
		} catch (Exception e) {
			JPAUtil.shutdown();
		}
		
	}

	@Override
	public void EliminarInventarioEntrada(int id) {
		try {
			InventarioEntrada inventarioEntrada = new InventarioEntrada();
			inventarioEntrada = this.entity.find(InventarioEntrada.class, id);

			this.entity.getTransaction().begin();
			this.entity.remove(inventarioEntrada);
			this.entity.getTransaction().commit();
		} catch (Exception e) {
			JPAUtil.shutdown();
		}
		
	}

}

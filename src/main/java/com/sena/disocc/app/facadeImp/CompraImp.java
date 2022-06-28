package com.sena.disocc.app.facadeImp;

import java.util.ArrayList;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.sena.disocc.app.facade.ICompra;
import com.sena.disocc.app.modelo.Compra;

import com.sena.disocc.app.utilities.JPAUtil;


public class CompraImp implements ICompra {
	
	EntityManager entity = JPAUtil.getEntityManagerFactory().createEntityManager();

	@Override
	public List<Compra> findAll() {
		List<Compra> listaCompra = new ArrayList<>();
		Query q = this.entity.createQuery("SELECT c FROM Compra c");
		listaCompra = q.getResultList();
		return listaCompra;
	}

	@Override
	public Compra findById(int idCompra) {
		Compra compra = new Compra();
		compra = this.entity.find(Compra.class, idCompra);
		return compra;
	}

	@Override
	public void CrearCompra(Compra compra) {
		try {
			this.entity.getTransaction().begin();
			this.entity.persist(compra);
			this.entity.getTransaction().commit();
		} catch (Exception e) {
			JPAUtil.shutdown();
		}

		
	}

	@Override
	public void ActualizarCompra(Compra compra) {
		try {
			this.entity.getTransaction().begin();
			this.entity.merge(compra);
			this.entity.getTransaction().commit();
		} catch (Exception e) {
			JPAUtil.shutdown();
		}
		
	}

	@Override
	public void EliminarCompra(int idCompra) {
		try {
			Compra compra = new Compra();
			compra = this.entity.find(Compra.class, idCompra);

			this.entity.getTransaction().begin();
			this.entity.remove(compra);
			this.entity.getTransaction().commit();
		} catch (Exception e) {
			JPAUtil.shutdown();
		}
		
	}

}

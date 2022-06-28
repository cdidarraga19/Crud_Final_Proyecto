package com.sena.disocc.app.facadeImp;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.sena.disocc.app.facade.IDetalleCompra;
import com.sena.disocc.app.modelo.DetallesCompra;
import com.sena.disocc.app.utilities.JPAUtil;

public class DetalleCompraImp implements IDetalleCompra {

	EntityManager entity = JPAUtil.getEntityManagerFactory().createEntityManager();
	
	@Override
	public List<DetallesCompra> findAll() {
		List<DetallesCompra> listaDetalleCompra = new ArrayList<>();
		Query q = this.entity.createQuery("SELECT d FROM DetallesCompra d");
		listaDetalleCompra = q.getResultList();
		return listaDetalleCompra;
	}

	@Override
	public DetallesCompra findById(int id) {
		DetallesCompra detallecompra = new DetallesCompra();
		detallecompra = this.entity.find(DetallesCompra.class, id);
		return detallecompra;
	}

	@Override
	public void CrearDetalleCompra(DetallesCompra detallescompra) {
		try {
			this.entity.getTransaction().begin();
			this.entity.persist(detallescompra);
			this.entity.getTransaction().commit();
		} catch (Exception e) {
			JPAUtil.shutdown();
		}
		
	}

	@Override
	public void ActualizarDetalleCompra(DetallesCompra detallescompra) {
		try {
			this.entity.getTransaction().begin();
			this.entity.merge(detallescompra);
			this.entity.getTransaction().commit();
		} catch (Exception e) {
			JPAUtil.shutdown();
		}
		
	}

	@Override
	public void EliminarDetalleCompra(int id) {
		try {
			DetallesCompra detallecompra = new DetallesCompra();
			detallecompra = this.entity.find(DetallesCompra.class, id);

			this.entity.getTransaction().begin();
			this.entity.remove(detallecompra);
			this.entity.getTransaction().commit();
		} catch (Exception e) {
			JPAUtil.shutdown();
		}
		
	}

}

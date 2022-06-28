package com.sena.disocc.app.facadeImp;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.sena.disocc.app.facade.IDetalleVenta;
import com.sena.disocc.app.modelo.DetallesVenta;
import com.sena.disocc.app.utilities.JPAUtil;

public class DetalleVentaImp implements IDetalleVenta {
	
	EntityManager entity = JPAUtil.getEntityManagerFactory().createEntityManager();

	@Override
	public List<DetallesVenta> findAll() {
		List<DetallesVenta> listaDetalleVenta = new ArrayList<>();
		Query q = this.entity.createQuery("SELECT d FROM DetallesVenta d");
		listaDetalleVenta = q.getResultList();
		return listaDetalleVenta;
	}

	@Override
	public DetallesVenta findById(int idDetalleVenta) {
		DetallesVenta detalleventa = new DetallesVenta();
		detalleventa = this.entity.find(DetallesVenta.class, idDetalleVenta);
		return detalleventa;
	}

	@Override
	public void CrearDetalleVenta(DetallesVenta detalleventa) {
		try {
			this.entity.getTransaction().begin();
			this.entity.persist(detalleventa);
			this.entity.getTransaction().commit();
		} catch (Exception e) {
			JPAUtil.shutdown();
		}
		
	}

	@Override
	public void ActualizarDetalleVenta(DetallesVenta detalleventa) {
		try {
			this.entity.getTransaction().begin();
			this.entity.merge(detalleventa);
			this.entity.getTransaction().commit();
		} catch (Exception e) {
			JPAUtil.shutdown();
		}
		
	}

	@Override
	public void EliminarDetalleVenta(int idDetalleVenta) {
		try {
			DetallesVenta detalleventa = new DetallesVenta();
			detalleventa = this.entity.find(DetallesVenta.class, idDetalleVenta);

			this.entity.getTransaction().begin();
			this.entity.remove(detalleventa);
			this.entity.getTransaction().commit();
		} catch (Exception e) {
			JPAUtil.shutdown();
		}
		
	}

}

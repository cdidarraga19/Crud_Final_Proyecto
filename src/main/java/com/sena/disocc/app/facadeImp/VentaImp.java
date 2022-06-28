package com.sena.disocc.app.facadeImp;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.sena.disocc.app.facade.IVenta;
import com.sena.disocc.app.modelo.Venta;
import com.sena.disocc.app.utilities.JPAUtil;

public class VentaImp implements IVenta {

	EntityManager entity = JPAUtil.getEntityManagerFactory().createEntityManager();
	
	@Override
	public List<Venta> findAll() {
		List<Venta> listaVenta = new ArrayList<>();
		Query q = this.entity.createQuery("SELECT v FROM Venta v");
		listaVenta = q.getResultList();
		return listaVenta;
	}

	@Override
	public Venta findById(int idVenta) {
		Venta venta = new Venta();
		venta = this.entity.find(Venta.class, idVenta);
		return venta;
	}

	@Override
	public void CrearVenta(Venta venta) {
		try {
			this.entity.getTransaction().begin();
			this.entity.persist(venta);
			this.entity.getTransaction().commit();
		} catch (Exception e) {
			JPAUtil.shutdown();
		}
		
	}

	@Override
	public void ActualizarVenta(Venta venta) {
		try {
			this.entity.getTransaction().begin();
			this.entity.merge(venta);
			this.entity.getTransaction().commit();
		} catch (Exception e) {
			JPAUtil.shutdown();
		}
		
	}

	@Override
	public void EliminarVenta(int idVenta) {
		try {
			Venta venta = new Venta();
			venta = this.entity.find(Venta.class, idVenta);

			this.entity.getTransaction().begin();
			this.entity.remove(venta);
			this.entity.getTransaction().commit();
		} catch (Exception e) {
			JPAUtil.shutdown();
		}
		
	}

}

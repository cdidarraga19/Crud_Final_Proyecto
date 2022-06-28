package com.sena.disocc.app.facadeImp;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.sena.disocc.app.facade.IInventario;
import com.sena.disocc.app.modelo.Inventario;
import com.sena.disocc.app.modelo.Proveedore;
import com.sena.disocc.app.utilities.JPAUtil;

public class InventarioImp implements IInventario {

	EntityManager entity = JPAUtil.getEntityManagerFactory().createEntityManager();
	
	@Override
	public List<Inventario> findAll() {
		List<Inventario> listaInventario = new ArrayList<>();
		Query q = this.entity.createQuery("SELECT i FROM Inventario i");
		listaInventario = q.getResultList();
		return listaInventario;
	}

	@Override
	public Inventario findById(int idInventario) {
		Inventario inventario = new Inventario();
		inventario = this.entity.find(Inventario.class, idInventario);
		return inventario;
	}

	@Override
	public void CrearInventario(Inventario inventario) {
		try {
			this.entity.getTransaction().begin();
			this.entity.persist(inventario);
			this.entity.getTransaction().commit();
		} catch (Exception e) {
			JPAUtil.shutdown();
		}
		
	}

	@Override
	public void ActualizarInventario(Inventario inventario) {
		try {
			this.entity.getTransaction().begin();
			this.entity.merge(inventario);
			this.entity.getTransaction().commit();
		} catch (Exception e) {
			JPAUtil.shutdown();
		}
		
	}

	@Override
	public void EliminarInventario(int idInventario) {
		try {
			Inventario inventario = new Inventario();
			inventario = this.entity.find(Inventario.class, idInventario);

			this.entity.getTransaction().begin();
			this.entity.remove(inventario);
			this.entity.getTransaction().commit();
		} catch (Exception e) {
			JPAUtil.shutdown();
		}
		
	}

}

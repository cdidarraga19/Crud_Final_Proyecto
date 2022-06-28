package com.sena.disocc.app.facadeImp;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.sena.disocc.app.facade.IInventarioSalida;
import com.sena.disocc.app.modelo.InventarioSalida;
import com.sena.disocc.app.utilities.JPAUtil;

public class InventarioSalidaImp implements IInventarioSalida {

	EntityManager entity = JPAUtil.getEntityManagerFactory().createEntityManager();

	@Override
	public List<InventarioSalida> findAll() {
		List<InventarioSalida> listaInventarioSalida = new ArrayList<>();
		Query q = this.entity.createQuery("SELECT i FROM InventarioSalida i");
		listaInventarioSalida = q.getResultList();
		return listaInventarioSalida;
	}

	@Override
	public InventarioSalida findById(int id) {
		InventarioSalida inventarioSalida = new InventarioSalida();
		inventarioSalida = this.entity.find(InventarioSalida.class, id);
		return inventarioSalida;
	}

	@Override
	public void CrearInventarioSalida(InventarioSalida inventariosalida) {
		try {
			this.entity.getTransaction().begin();
			this.entity.persist(inventariosalida);
			this.entity.getTransaction().commit();
		} catch (Exception e) {
			JPAUtil.shutdown();
		}

	}

	@Override
	public void ActualizarInventarioSalida(InventarioSalida inventariosalida) {
		try {
			this.entity.getTransaction().begin();
			this.entity.merge(inventariosalida);
			this.entity.getTransaction().commit();
		} catch (Exception e) {
			JPAUtil.shutdown();
		}

	}

	@Override
	public void EliminarInventarioSalida(int id) {
		try {
			InventarioSalida inventarioSalida = new InventarioSalida();
			inventarioSalida = this.entity.find(InventarioSalida.class, id);

			this.entity.getTransaction().begin();
			this.entity.remove(inventarioSalida);
			this.entity.getTransaction().commit();
		} catch (Exception e) {
			JPAUtil.shutdown();
		}

	}

}

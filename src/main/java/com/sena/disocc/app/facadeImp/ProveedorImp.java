package com.sena.disocc.app.facadeImp;

import java.util.ArrayList;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.sena.disocc.app.facade.IProveedor;
import com.sena.disocc.app.modelo.Proveedore;
import com.sena.disocc.app.utilities.JPAUtil;

public class ProveedorImp implements IProveedor {

	EntityManager entity = JPAUtil.getEntityManagerFactory().createEntityManager();

	@Override
	public List<Proveedore> findAll() {
		List<Proveedore> listaProveedor = new ArrayList<>();
		Query q = this.entity.createQuery("SELECT p FROM Proveedore p");
		listaProveedor = q.getResultList();
		return listaProveedor;
	}

	@Override
	public Proveedore findById(int idProveedor) {
		Proveedore proveedor = new Proveedore();
		proveedor = this.entity.find(Proveedore.class, idProveedor);
		return proveedor;
	}

	@Override
	public void CrearProveedore(Proveedore proveedor) {
		try {
			this.entity.getTransaction().begin();
			this.entity.persist(proveedor);
			this.entity.getTransaction().commit();
		} catch (Exception e) {
			JPAUtil.shutdown();
		}

	}

	@Override
	public void ActualizarProveedore(Proveedore proveedor) {
		try {
			this.entity.getTransaction().begin();
			this.entity.merge(proveedor);
			this.entity.getTransaction().commit();
		} catch (Exception e) {
			JPAUtil.shutdown();
		}

	}

	@Override
	public void EliminarProveedore(int idProveedor) {
		try {
			Proveedore proveedor = new Proveedore();
			proveedor = this.entity.find(Proveedore.class, idProveedor);

			this.entity.getTransaction().begin();
			this.entity.remove(proveedor);
			this.entity.getTransaction().commit();
		} catch (Exception e) {
			JPAUtil.shutdown();
		}

	}

}

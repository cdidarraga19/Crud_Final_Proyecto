package com.sena.disocc.app.facadeImp;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.sena.disocc.app.facade.IPermiso;
import com.sena.disocc.app.modelo.Permiso;
import com.sena.disocc.app.utilities.JPAUtil;

public class PermisoImp implements IPermiso {

	EntityManager entity = JPAUtil.getEntityManagerFactory().createEntityManager();

	@Override
	public List<Permiso> findAll() {
		List<Permiso> listaPermiso = new ArrayList<>();
		Query q =this.entity.createQuery("SELECT per FROM Permiso per");
		listaPermiso = q.getResultList();
		return listaPermiso;
	}

	@Override
	public Permiso finById(int idPermiso) {
		Permiso permiso = new Permiso();
		permiso = this.entity.find(Permiso.class, idPermiso);
		return permiso;
	}

	@Override
	public void crearPermiso(Permiso permiso) {
		try {
		this.entity.getTransaction().begin();
		this.entity.persist(permiso);
		this.entity.getTransaction().commit();
		
		} catch (Exception e) {
			JPAUtil.shutdown();
		}

	}

	@Override
	public void actualizarPermiso(Permiso permiso) {
		try {
			this.entity.getTransaction().begin();
			this.entity.merge(permiso);
			this.entity.getTransaction().commit();
		} catch (Exception e) {
			JPAUtil.shutdown();
		}

	}

	@Override
	public void eliminarPermiso(int idPermiso) {
		try {
			Permiso permiso = new Permiso();
			permiso = this.entity.find(Permiso.class, idPermiso);
			this.entity.getTransaction().begin();
			this.entity.remove(permiso);
			this.entity.getTransaction().commit();
		} catch (Exception e) {
			JPAUtil.shutdown();
		}

	}

}

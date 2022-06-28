package com.sena.disocc.app.facadeImp;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.sena.disocc.app.facade.IRol;
import com.sena.disocc.app.modelo.Role;
import com.sena.disocc.app.utilities.JPAUtil;

public class RolImp implements IRol {
	
	EntityManager entity = JPAUtil.getEntityManagerFactory().createEntityManager();
	

	@Override
	public List<Role> findAll() {
		
			List<Role> listaRol = new ArrayList <>();
			Query q = this.entity.createQuery("SELECT r FROM Role r");
			listaRol = q.getResultList();
			return listaRol;
			
	}

	@Override
	public Role findById(int idRol) {
		
		Role rol = new Role();
		rol= this.entity.find(Role.class, idRol);	
		return rol;
		
	}

	@Override
	public void CrearRole(Role rol) {
		
			try {
			this.entity.getTransaction().begin();
			this.entity.persist(rol);
			this.entity.getTransaction().commit();
			}catch (Exception e) {
				JPAUtil.shutdown();
			}
		

	}

	@Override
	public void ActualizarRole(Role rol) {
		try {
			this.entity.getTransaction().begin();
			this.entity.merge(rol);
			this.entity.getTransaction().commit();
		}catch (Exception e) {
			JPAUtil.shutdown();
		}
		

	}

	@Override
	public void EliminarRole(int idRol) {
		try {
		Role rol = new Role();
		rol = this.entity.find(Role.class, idRol);
		
		this.entity.getTransaction().begin();
		this.entity.remove(rol);
		this.entity.getTransaction().commit();
		}catch (Exception e) {
			JPAUtil.shutdown();
		}

	}

}

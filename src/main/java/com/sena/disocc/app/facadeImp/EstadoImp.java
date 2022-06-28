package com.sena.disocc.app.facadeImp;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.sena.disocc.app.facade.IEstado;
import com.sena.disocc.app.modelo.Estado;
import com.sena.disocc.app.utilities.JPAUtil;

public class EstadoImp implements IEstado {

	EntityManager entity = JPAUtil.getEntityManagerFactory().createEntityManager();
	
	@Override
	public List<Estado> findAll() {
		    List<Estado> listaEstado = new ArrayList<>();
			Query q =this.entity.createQuery("SELECT es FROM Estado es");
			listaEstado = q.getResultList();
			return listaEstado;
		
	}

	@Override
	public Estado findById(int idEstado) {
		Estado estado = new Estado();
		estado = this.entity.find(Estado.class, idEstado);
		return estado;
	}

	@Override
	public void CrearEstado(Estado estado) {
		
		try {
			this.entity.getTransaction().begin();
			this.entity.persist(estado);
			this.entity.getTransaction().commit();
		} catch (Exception e) {
			JPAUtil.shutdown();
		}
	}

	@Override
	public void ActualizarEstado(Estado estado) {
		try {
			
			this.entity.getTransaction().begin();
			this.entity.merge(estado);
			this.entity.getTransaction().commit();
			
		} catch (Exception e) {
			JPAUtil.shutdown();
		}

	}

	@Override
	public void EliminarEstado(int idEstado) {
		try {
			Estado estado = new Estado();
			estado = this.entity.find(Estado.class, idEstado);
			
			this.entity.getTransaction().begin();
			this.entity.remove(estado);
			this.entity.getTransaction().commit();
			
		} catch (Exception e) {
			JPAUtil.shutdown();
		}

	}

}

package com.sena.disocc.app.facadeImp;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.sena.disocc.app.facade.IRespuesta;
import com.sena.disocc.app.modelo.Respuesta;
import com.sena.disocc.app.utilities.JPAUtil;

public class RespuestaImp implements IRespuesta {
	
	EntityManager entity = JPAUtil.getEntityManagerFactory().createEntityManager();

	
	@Override
	public List<Respuesta> findAll() {
		List<Respuesta> listaRespuesta = new ArrayList<>();
		Query q = this.entity.createQuery("SELECT rp FROM Respuesta rp");
		listaRespuesta = q.getResultList();
		return listaRespuesta;
	}

	@Override
	public Respuesta findById(int idRespuesta) {
		Respuesta respuesta = new Respuesta();
		respuesta = this.entity.find(Respuesta.class, idRespuesta);
		return respuesta;
	}

	@Override
	public void crearRespuesta(Respuesta respuesta) {
		try {
			this.entity.getTransaction().begin();
			this.entity.persist(respuesta);
			this.entity.getTransaction().commit();
		} catch (Exception e) {
			JPAUtil.shutdown();
		}

	}

	@Override
	public void actualizaRespuesta(Respuesta respuesta) {
		try {
			this.entity.getTransaction().begin();
			this.entity.merge(respuesta);
			this.entity.getTransaction().commit();
		} catch (Exception e) {
			JPAUtil.shutdown();
		}

	}

	@Override
	public void eliminarRespuesta(int idRespuesta) {
		try {
			Respuesta respuesta = new Respuesta();
			respuesta = this.entity.find(Respuesta.class, idRespuesta);
			
			this.entity.getTransaction().begin();
			this.entity.remove(respuesta);
			this.entity.getTransaction().commit();
		} catch (Exception e) {
			JPAUtil.shutdown();
		}

	}

}

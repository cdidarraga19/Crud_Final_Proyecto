package com.sena.disocc.app.facadeImp;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.sena.disocc.app.facade.IPeticion;
import com.sena.disocc.app.modelo.Peticione;
import com.sena.disocc.app.utilities.JPAUtil;

public class PeticionImp implements IPeticion {

	EntityManager entity = JPAUtil.getEntityManagerFactory().createEntityManager();

	@Override
	public List<Peticione> findAll() {
		
		List<Peticione> listaPeticion = new ArrayList<Peticione>();
		Query q =this.entity.createQuery("SELECT pt FROM Peticione pt");
		listaPeticion = q.getResultList();
		return listaPeticion;
	}

	@Override
	public Peticione findById(int idPeticion) {
		Peticione peticion = new Peticione();
		peticion = this.entity.find(Peticione.class, idPeticion);
		return peticion;
	}

	@Override
	public void CrearPeticion(Peticione peticion) {
		try {
			this.entity.getTransaction().begin();
			this.entity.persist(peticion);
			this.entity.getTransaction().commit();
		}catch (Exception e) {
			JPAUtil.shutdown();
		}

	}

	@Override
	public void ActualizarPeticion(Peticione peticion) {
		try {
			this.entity.getTransaction().begin();
			this.entity.merge(peticion);
			this.entity.getTransaction().commit();
		} catch (Exception e) {
			JPAUtil.shutdown();
		}

	}

	@Override
	public void EliminarPeticion(int idPeticion) {
		try {
			Peticione peticion = new Peticione();
			peticion = this.entity.find(Peticione.class,idPeticion);
			
			this.entity.getTransaction().begin();
			this.entity.remove(peticion);
			this.entity.getTransaction().commit();
		} catch (Exception e) {
			JPAUtil.shutdown();
		}
	}

}

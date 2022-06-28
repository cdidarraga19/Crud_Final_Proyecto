package com.sena.disocc.app.facadeImp;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.sena.disocc.app.facade.IMarca;
import com.sena.disocc.app.modelo.Marca;
import com.sena.disocc.app.utilities.JPAUtil;

public class MarcaImp implements IMarca {

	EntityManager entity = JPAUtil.getEntityManagerFactory().createEntityManager();

	@Override
	public List<Marca> findAll() {
		List<Marca> listaMarca = new ArrayList<>();
		Query q =this.entity.createQuery("SELECT ma FROM Marca ma");
		listaMarca = q.getResultList();
		return listaMarca;
	}

	@Override
	public Marca finById(int idMarca) {
		Marca marca = new Marca();
		marca =this.entity.find(Marca.class, idMarca);
		return marca;
	}

	@Override
	public void crearMarca(Marca marca) {
		try {
			this.entity.getTransaction().begin();
			this.entity.persist(marca);
			this.entity.getTransaction().commit();
		} catch (Exception e) {
			JPAUtil.shutdown();
		}

	}

	@Override
	public void actualizarMarca(Marca marca) {
		try {
			this.entity.getTransaction().begin();
			this.entity.merge(marca);
			this.entity.getTransaction().commit();
		} catch (Exception e) {
			JPAUtil.shutdown();
		}

	}

	@Override
	public void eliminarMarca(int idMarca) {
		try {
			Marca marca = new Marca();
			marca =this.entity.find(Marca.class, idMarca);
			
			this.entity.getTransaction().begin();
			this.entity.remove(marca);
			this.entity.getTransaction().commit();
		} catch (Exception e) {
			JPAUtil.shutdown();
		}

	}

}

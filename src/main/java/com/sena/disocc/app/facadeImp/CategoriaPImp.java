package com.sena.disocc.app.facadeImp;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.sena.disocc.app.facade.ICategoriaPro;
import com.sena.disocc.app.modelo.CategoriasProducto;
import com.sena.disocc.app.utilities.JPAUtil;

public class CategoriaPImp implements ICategoriaPro {

	EntityManager entity = JPAUtil.getEntityManagerFactory().createEntityManager();

	@Override
	public List<CategoriasProducto> findAll() {
		List<CategoriasProducto> listaCategoriaP = new ArrayList<CategoriasProducto>();
		Query q =this.entity.createQuery("SELECT cp FROM CategoriasProducto cp");
		listaCategoriaP = q.getResultList();
		return listaCategoriaP;
	}

	@Override
	public CategoriasProducto findById(int idCategoria) {
		CategoriasProducto categoriaP = new CategoriasProducto();
		categoriaP = this.entity.find(CategoriasProducto.class, idCategoria);
		return categoriaP;
		
		}

	@Override
	public void crearCategoriaP(CategoriasProducto categoriaP) {
		try {
			this.entity.getTransaction().begin();
			this.entity.persist(categoriaP);
			this.entity.getTransaction().commit();
		} catch (Exception e) {
			JPAUtil.shutdown();
		}

	}

	@Override
	public void actualizarCategoriaP(CategoriasProducto categoriaP) {
		try {
			this.entity.getTransaction().begin();
			this.entity.merge(categoriaP);
			this.entity.getTransaction().commit();
		} catch (Exception e) {
			JPAUtil.shutdown();
		}
	}

	@Override
	public void eliminarCategoriaP(int idCategoria) {
		try {
			CategoriasProducto categoriaP = new CategoriasProducto();
			categoriaP = this.entity.find(CategoriasProducto.class, idCategoria);
			
			this.entity.getTransaction().begin();
			this.entity.remove(categoriaP);
			this.entity.getTransaction().commit();
		} catch (Exception e) {
			JPAUtil.shutdown();
		}

	}

}

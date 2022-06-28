package com.sena.disocc.app.facadeImp;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.sena.disocc.app.facade.IProducto;
import com.sena.disocc.app.modelo.Producto;
import com.sena.disocc.app.modelo.Usuario;
import com.sena.disocc.app.utilities.JPAUtil;

public class ProductoImp implements IProducto {

	EntityManager entity = JPAUtil.getEntityManagerFactory().createEntityManager();
	List<Producto> listaProducto = new ArrayList<Producto>();

	@Override
	public List<Producto> finAll() {
	Query q = this.entity.createQuery("SELECT pro FROM Producto pro");
	listaProducto = q.getResultList();
	return listaProducto;
	}

	@Override
	public Producto findById(int idProducto) {
		Producto producto = new Producto();
		producto = this.entity.find(Producto.class, idProducto);
		return producto;
	}

	@Override
	public void crearProducto(Producto producto) {
		
		try {
			this.entity.getTransaction().begin();
			this.entity.persist(producto);
			this.entity.getTransaction().commit();
		} catch (Exception e) {
			JPAUtil.shutdown();
		}
	}

	@Override
	public void actualizarProducto(Producto producto) {
		try {
			this.entity.getTransaction().begin();
			this.entity.merge(producto);
			this.entity.getTransaction().commit();
		} catch (Exception e) {
			JPAUtil.shutdown();
		}

	}

	@Override
	public void eliminarProducto(int idProducto) {
		try {
			Producto producto = new Producto();
			producto=this.entity.find(Producto.class, idProducto);
			this.entity.getTransaction().begin();
			this.entity.remove(producto);
			this.entity.getTransaction().commit();
		} catch (Exception e) {
			JPAUtil.shutdown();
		}

	}
	
	public List <Producto> exportPorMarca (int idMarca){
		try {
			this.entity.getTransaction().begin();
			Query q =this.entity.createQuery("SELECT pro FROM Producto pro WHERE pro.marca.idMarca="+idMarca);
			this.listaProducto = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			this.entity.clear();		
		}
		return listaProducto;
	}
	
	public List <Producto> exportPorCatego (int idCate){
		try {
			this.entity.getTransaction().begin();
			Query q =this.entity.createQuery("SELECT pro FROM Producto pro WHERE pro.categoriasProducto.idCategoria="+idCate);
			this.listaProducto = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			this.entity.clear();		
		}
		return listaProducto;
	}
	
	public List <Producto> exportPorMarcaCatego (int idMarca, int idCate){
		try {
			this.entity.getTransaction().begin();
			Query q =this.entity.createQuery("SELECT pro FROM Producto pro WHERE pro.marca.idMarca="+idMarca+"and pro.categoriasProducto.idCategoria"+idCate);
			this.listaProducto = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			this.entity.clear();		
		}
		return listaProducto;
	}

}

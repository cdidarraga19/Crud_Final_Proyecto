package com.sena.disocc.app.facadeImp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import com.sena.disocc.app.modelo.Role;

import com.sena.disocc.app.facade.IUsuario;
import com.sena.disocc.app.modelo.Usuario;
import com.sena.disocc.app.utilities.JPAUtil;

public class UsuarioImp implements IUsuario {

	EntityManager entity = JPAUtil.getEntityManagerFactory().createEntityManager();
	private Query q;
	List<Usuario> listaUsuario = new ArrayList<Usuario>();
	
	@Override
	public List<Usuario> findAll() {
		Query q = this.entity.createQuery("SELECT usu FROM Usuario usu");
		listaUsuario = q.getResultList();
		return listaUsuario;
	}

	@Override
	public Usuario findById(int idUsuario) {
		Usuario usuario = new Usuario();
		usuario = this.entity.find(Usuario.class, idUsuario);
		return usuario;
	}

	@Override
	public void CrearUsu(Usuario usuario) {
		try {
			this.entity.getTransaction().begin();
			this.entity.persist(usuario);
			this.entity.getTransaction().commit();
		} catch (Exception e) {
			JPAUtil.shutdown();
		}

	}

	@Override
	public void ActualizarUsu(Usuario usuario) {
		try {
			this.entity.getTransaction().begin();
			this.entity.merge(usuario);
			this.entity.getTransaction().commit();
		} catch (Exception e) {
			JPAUtil.shutdown();
		}

	}

	@Override
	public void EliminarUsu(int idUsuario) {
		try {
		Usuario usuario = new Usuario();
		usuario=this.entity.find(Usuario.class,idUsuario);
		
		this.entity.getTransaction().begin();
		this.entity.remove(usuario);
		this.entity.getTransaction().commit();
		
		}catch (Exception e) {
			JPAUtil.shutdown();
		}

	}
	
	public List <Usuario> exportRolEstado (int idRol, int idEstado){
		try {
			this.entity.getTransaction().begin();
			Query q =this.entity.createQuery("SELECT usu FROM Usuario usu WHERE usu.role.idRol="+idRol+ "and usu.estado.idEstado="+idEstado);
			this.listaUsuario = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			this.entity.clear();		
		}
		return listaUsuario;
	}
	
	public List <Usuario> exportPorRol (int idRol){
		try {
			this.entity.getTransaction().begin();
			Query q =this.entity.createQuery("SELECT usu FROM Usuario usu WHERE usu.role.idRol="+idRol);
			this.listaUsuario = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			this.entity.clear();		
		}
		return listaUsuario;
	}
	
	public List <Usuario> exportPorEstado (int idEstado){
		try {
			this.entity.getTransaction().begin();
			Query q =this.entity.createQuery("SELECT usu FROM Usuario usu WHERE usu.estado.idEstado="+idEstado);
			this.listaUsuario = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			this.entity.clear();		
		}
		return listaUsuario;
	}
	
	
	public String validarUsuario (Usuario usuario) {
		String roles ="none ";
		try {
			this.entity.getTransaction().begin();
			 q=this.entity.createQuery("SELECT usu FROM Usuario usu WHERE usu.contrase_ďa='"+usuario.getContrase_ďa()+"' AND usu.correo='"+usuario.getCorreo()+"'");
			 this.listaUsuario =q.getResultList();
			 for (Usuario us: this.listaUsuario) {
				 System.out.print("us ->"+us.toString());
				 RolImp rolImp = new RolImp();
				 Role role = new Role();
				 
				 role = rolImp.findById(us.getRole().getIdRol());
				 System.out.print("nombre rol "+ role.getNomRol());
				 roles = role.getNomRol();
				 
			 }
			 this.entity.getTransaction().commit();
			 
						
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		finally {
			this.entity.clear();
			this.q=null;
		}
		return roles;
	}
		
}



package com.sena.disocc.app.controlador;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import com.sena.disocc.app.facadeImp.PeticionImp;
import com.sena.disocc.app.facadeImp.TipoDocumentoImp;
import com.sena.disocc.app.facadeImp.TipoPeticionImp;
import com.sena.disocc.app.facadeImp.UsuarioImp;
import com.sena.disocc.app.modelo.Peticione;
import com.sena.disocc.app.modelo.TipoPeticione;
import com.sena.disocc.app.modelo.Usuario;
import com.sena.disocc.app.utilities.ExportarPeticiones;

@ManagedBean (name="peticionBean")
@RequestScoped
public class PeticionBean {
	Peticione peticion = new Peticione();
	
	List<Peticione> listaPeticion = new ArrayList<Peticione>();
	List<TipoPeticione> listaTipoPeticion = new ArrayList<TipoPeticione>();
	List <Usuario> listaUsuario = new ArrayList <Usuario>();
	
	private Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
	
	private int idTipoPetiFK;
	private int idUsuarioFK;
	
	public Peticione getPeticion() {
		return peticion;
	}
	public void setPeticion(Peticione peticion) {
		this.peticion = peticion;
	}
	public List<Peticione> getListaPeticion() {
		return listaPeticion;
	}
	public void setListaPeticion(List<Peticione> listaPeticion) {
		this.listaPeticion = listaPeticion;
	}
	public List<TipoPeticione> getListaTipoPeticion() {
		return listaTipoPeticion;
	}
	public void setListaTipoPeticion(List<TipoPeticione> listaTipoPeticion) {
		this.listaTipoPeticion = listaTipoPeticion;
	}
	public List<Usuario> getListaUsuario() {
		return listaUsuario;
	}
	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}
	public int getIdTipoPetiFK() {
		return idTipoPetiFK;
	}
	public void setIdTipoPetiFK(int idTipoPetiFK) {
		this.idTipoPetiFK = idTipoPetiFK;
	}
	public int getIdUsuarioFK() {
		return idUsuarioFK;
	}
	public void setIdUsuarioFK(int idUsuarioFK) {
		this.idUsuarioFK = idUsuarioFK;
	}
	
	
	public PeticionBean() {
		this.llenarUsuario();
		this.llenarTipoPeticion();
	}
	
	public void llenarTipoPeticion() {
		TipoPeticionImp tipopeticionImp = new TipoPeticionImp();
		this.listaTipoPeticion =tipopeticionImp.findAll();
		
	}
	public void llenarUsuario() {
		UsuarioImp usuarioImp = new UsuarioImp();
		this.listaUsuario = usuarioImp.findAll();
	}
	
	public List<Peticione> findAll(){
		
		PeticionImp peticionImp = new PeticionImp();
		this.listaPeticion=peticionImp.findAll();
		return this.listaPeticion;
	}
	
	public String crearPeticion(Peticione peticion) {
		PeticionImp peticionImp = new PeticionImp();
		
		TipoPeticionImp tipopeticionImp = new TipoPeticionImp();
		TipoPeticione tipoPeticion = new TipoPeticione();
		tipoPeticion = tipopeticionImp.findById(idTipoPetiFK);
		peticion.setTipoPeticione(tipoPeticion);
		
		UsuarioImp usuarioImp = new UsuarioImp();
		Usuario usuario = new Usuario();
		
		usuario = usuarioImp.findById(idUsuarioFK);
		peticion.setUsuario(usuario);
		
		peticionImp.CrearPeticion(peticion);
		return "/faces/Peticion/listaPeticion.xhtml?faces-redirect=true";	
	}
	
	public String crear () {
		System.out.print("Entro a CREAR");
		this.sessionMap.put("peticion", peticion);
		return "/faces/Peticion/crearPeticion.xhtml?faces-redirect=true";
 
	}
	
	public String actualizar (int idPeticion) {
		
		PeticionImp peticionImp = new PeticionImp();
		Peticione peticion = new Peticione();
		
		peticion= peticionImp.findById(idPeticion);
		System.out.print("Entro a EDITAR "+peticion);
		
		this.sessionMap.put("peticion", peticion);
		return "/faces/Peticion/editarPeticion.xhtml?faces-redirect=true";
	}
	
	public String actualizarPeticion (Peticione peticion) {
		
		PeticionImp peticionImp = new PeticionImp();
		peticionImp.ActualizarPeticion(peticion);
		return "/faces/Peticion/listaPeticion.xhtml?faces-redirect=true";	
		
	}
	
	public String eliminarPeticion (int idPeticion) {
		
		PeticionImp peticionImp = new PeticionImp();
		peticionImp.EliminarPeticion(idPeticion);
		System.out.print("ELIMINO");
		return "/faces/Peticion/listaPeticion.xhtml?faces-redirect=true";	
		
	}
	
	public void exportar () throws IOException {
		
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		response.setContentType("application/octet-stream");
		DateFormat dateFormtter = new SimpleDateFormat("yyyy-MM-dd");
		String curretDateTime =dateFormtter.format(new Date ());
		String headerKey ="Content-Disposition";
		String headerValue ="attachemnt; filename=LIstadePeticiones" + curretDateTime + ".xlsx";
		response.setHeader (headerKey,headerValue);
		
		PeticionImp peticionImp = new PeticionImp();
		this.listaPeticion = peticionImp.findAll();
		
		ExportarPeticiones ex = new ExportarPeticiones(this.listaPeticion);
		ex.export(response);
	}
	
	
	
	

}

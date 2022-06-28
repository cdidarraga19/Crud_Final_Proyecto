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
import com.sena.disocc.app.facadeImp.RespuestaImp;
import com.sena.disocc.app.facadeImp.UsuarioImp;
import com.sena.disocc.app.modelo.Peticione;
import com.sena.disocc.app.modelo.Respuesta;
import com.sena.disocc.app.modelo.Usuario;
import com.sena.disocc.app.utilities.ExportarRespuestas;

@ManagedBean (name="respuestaBean")
@RequestScoped
public class RespuestaBean {	
	Respuesta respuesta = new Respuesta();
	
	List<Respuesta> listaRespuesta = new ArrayList<Respuesta>();
	List <Peticione> listaPeticione = new ArrayList<Peticione>();
	List<Usuario> listaUsuario = new ArrayList<Usuario>();
	
	private Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
	
	private int idUsuarioFK;
	private int idPeticionFK;
	
	
	public Respuesta getRespuesta() {
		return respuesta;
	}
	public void setRespuesta(Respuesta respuesta) {
		this.respuesta = respuesta;
	}
	public List<Respuesta> getListaRespuesta() {
		return listaRespuesta;
	}
	public void setListaRespuesta(List<Respuesta> listaRespuesta) {
		this.listaRespuesta = listaRespuesta;
	}
	public List<Peticione> getListaPeticione() {
		return listaPeticione;
	}
	public void setListaPeticione(List<Peticione> listaPeticione) {
		this.listaPeticione = listaPeticione;
	}
	public List<Usuario> getListaUsuario() {
		return listaUsuario;
	}
	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}
	public int getIdUsuarioFK() {
		return idUsuarioFK;
	}
	public void setIdUsuarioFK(int idUsuarioFK) {
		this.idUsuarioFK = idUsuarioFK;
	}
	public int getIdPeticionFK() {
		return idPeticionFK;
	}
	public void setIdPeticionFK(int idPeticionFK) {
		this.idPeticionFK = idPeticionFK;
	}
	
	public RespuestaBean() {
		this.llenarUsuario();
		this.llenarPeticion();
	}
	
	public void llenarUsuario() {
		UsuarioImp usuarioImp = new UsuarioImp();
		this.listaUsuario =usuarioImp.findAll();
	}
	
	public void llenarPeticion() {
		PeticionImp peticionImp = new PeticionImp();
		this.listaPeticione = peticionImp.findAll();
	}
	
	public List<Respuesta> findAll(){
		
		RespuestaImp respuestaImp = new RespuestaImp();
		this.listaRespuesta=respuestaImp.findAll();
		return this.listaRespuesta;
	}
	
	public String crearRespuesta(Respuesta respuesta) {
		
		RespuestaImp respuestaImp = new RespuestaImp();
		
		PeticionImp peticionImp = new PeticionImp();
		Peticione peticion = new Peticione();
		peticion = peticionImp.findById(idPeticionFK);
		respuesta.setPeticione(peticion);
		
		UsuarioImp usuarioImp = new UsuarioImp();
		Usuario usuario = new Usuario();
		usuario = usuarioImp.findById(idUsuarioFK);
		respuesta.setUsuario(usuario);
		
		respuestaImp.crearRespuesta(respuesta);
		return "/faces/Respuesta/listaRespuesta.xhtml?faces-redirect=true";
	}
	
	public String crear () {
		System.out.print("Entro a CREAR");
		this.sessionMap.put("respuesta", respuesta);
		return "/faces/Respuesta/crearRespuesta.xhtml?faces-redirect=true";
	}
	
	public String actualizar (int idRespuesta) {
		RespuestaImp respuestaImp = new RespuestaImp();
		Respuesta respuesta = new Respuesta();
		
		respuesta=respuestaImp.findById(idRespuesta);
		System.out.print("Entro a EDITAR "+respuesta);
		this.sessionMap.put("respuesta", respuesta);
		return "/faces/Respuesta/editarRespuesta.xhtml?faces-redirect=true";
	}
	
	public String actualizaRespuesta (Respuesta respuesta) {
		RespuestaImp respuestaImp = new RespuestaImp();
		respuestaImp.actualizaRespuesta(respuesta);
		return "/faces/Respuesta/listaRespuesta.xhtml?faces-redirect=true";
	}
	
	public String eliminarRespuesta (int idRespuesta) {
		RespuestaImp respuestaImp = new RespuestaImp();
		respuestaImp.eliminarRespuesta(idRespuesta);
		System.out.print("ELIMINO");
		return "/faces/Respuesta/listaRespuesta.xhtml?faces-redirect=true";
	}
	
	public void exportar () throws IOException {
		
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		response.setContentType("application/octet-stream");
		DateFormat dateFormtter = new SimpleDateFormat("yyyy-MM-dd");
		String curretDateTime =dateFormtter.format(new Date ());
		String headerKey ="Content-Disposition";
		String headerValue ="attachemnt; filename=ListaRespuestas" + curretDateTime + ".xlsx";
		response.setHeader (headerKey,headerValue);
		
		RespuestaImp respuestaImp = new RespuestaImp();
		this.listaRespuesta = respuestaImp.findAll();
		
		ExportarRespuestas ex = new ExportarRespuestas(this.listaRespuesta);
		ex.export(response);
	}

}

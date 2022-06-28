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

import com.sena.disocc.app.facadeImp.EstadoImp;
import com.sena.disocc.app.modelo.Estado;
import com.sena.disocc.app.utilities.ExportarEstado;

@ManagedBean (name="estadoBean")
@RequestScoped
public class EstadoBean {
	
	Estado estado = new Estado();
	List <Estado> listastado = new ArrayList<Estado>();
	private Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
	
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public List<Estado> getListastado() {
		return listastado;
	}
	public void setListastado(List<Estado> listastado) {
		this.listastado = listastado;
	}
	 
	public List<Estado> findAll(){
		EstadoImp estadoImp = new EstadoImp();
		this.listastado=estadoImp.findAll();
		return this.listastado;
		
	}
	
	public 	String crear () {
		
		System.out.print("Entro a CREAR");
		this.sessionMap.put("estado", estado);
		return "/faces/Estados/crearEstado.xhtml?faces-redirect=true";
	}
	
	public String crearEstado(Estado estado) {
		EstadoImp estadoImp = new EstadoImp();
		estadoImp.CrearEstado(estado);
		return "/faces/Estados/listaEstado.xhtml?faces-redirect=true";
	}
	
	public String actualizar (int idEstado) {
		EstadoImp estadoImp = new EstadoImp();
		Estado estado = new Estado();
		
		estado=estadoImp.findById(idEstado);
		System.out.print("Entro a EDITAR "+estado);
		this.sessionMap.put("estado", estado);
		return "/faces/Estados/editarEstado.xhtml?faces-redirect=true";
	}
	
	public String actualizarEstado (Estado estado) {
		EstadoImp estadoImp = new EstadoImp();
		estadoImp.ActualizarEstado(estado);
		return "/faces/Estados/listaEstado.xhtml?faces-redirect=true";		
	}
	
	public String eliminarEstado (int idEstado) {
		EstadoImp estadoImp = new EstadoImp();
		estadoImp.EliminarEstado(idEstado);
		System.out.print("ELIMINO");
		return "/faces/Estados/listaEstado.xhtml?faces-redirect=true";		
	}
	
	public void exportar () throws IOException {
		
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		response.setContentType("application/octet-stream");
		DateFormat dateFormtter = new SimpleDateFormat("yyyy-MM-dd");
		String curretDateTime =dateFormtter.format(new Date ());
		String headerKey ="Content-Disposition";
		String headerValue ="attachemnt; filename=ListaEstado" + curretDateTime + ".xlsx";
		response.setHeader (headerKey,headerValue);
		
		EstadoImp estadoImp = new EstadoImp();
		this.listastado =estadoImp.findAll();
		
		ExportarEstado exportarEstado = new ExportarEstado(this.listastado);
		exportarEstado.export(response);
	}

}

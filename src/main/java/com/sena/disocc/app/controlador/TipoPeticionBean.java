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

import com.sena.disocc.app.facadeImp.TipoPeticionImp;
import com.sena.disocc.app.modelo.TipoPeticione;
import com.sena.disocc.app.utilities.ExportarTipoPeticion;

@ManagedBean (name ="tipoPeticionBean")
@RequestScoped
public class TipoPeticionBean {
	TipoPeticione tipoPeticion = new TipoPeticione();
	List<TipoPeticione> listaTipoPeticion = new ArrayList<TipoPeticione>();
	private Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
	
	public TipoPeticione getTipoPeticion() {
		return tipoPeticion;
	}
	public void setTipoPeticion(TipoPeticione tipoPeticion) {
		this.tipoPeticion = tipoPeticion;
	}
	public List<TipoPeticione> getListaTipoPeticion() {
		return listaTipoPeticion;
	}
	public void setListaTipoPeticion(List<TipoPeticione> listaTipoPeticion) {
		this.listaTipoPeticion = listaTipoPeticion;
	}

	public List<TipoPeticione> findAll(){
		TipoPeticionImp tipopeticionImp = new TipoPeticionImp();
		this.listaTipoPeticion = tipopeticionImp.findAll();
		return this.listaTipoPeticion;
	}
	
	public String crear () {
		
		System.out.print("Entro a CREAR");
		this.sessionMap.put("tipoPeticion", tipoPeticion);
		return "/faces/TipoPeticion/crearTipoPeticion.xhtml?faces-redirect=true";
	}
	
	public String crearTipoPeticion(TipoPeticione tipoPeticion) {
		TipoPeticionImp tipopeticionImp = new TipoPeticionImp();
		tipopeticionImp.CrearTipoPeticion(tipoPeticion);
		return "/faces/TipoPeticion/listaTipoPeticion.xhtml?faces-redirect=true";
		
	}
	
	public String actualizar (int idTipoPeticion) {
		TipoPeticionImp tipopeticionImp = new TipoPeticionImp();
		TipoPeticione tipoPeticione = new TipoPeticione();
		
		tipoPeticion = tipopeticionImp.findById(idTipoPeticion);
		System.out.print("Entro a EDITAR "+tipoPeticion);
		this.sessionMap.put("tipoPeticion", tipoPeticion);
		return "/faces/TipoPeticion/editarTipoPeticion.xhtml?faces-redirect=true";
	}
	
	public String actualizarTipoPeticion (TipoPeticione tipoPeticion) {
		TipoPeticionImp tipopeticionImp = new TipoPeticionImp();
		tipopeticionImp.ActualizarTipoPeticion(tipoPeticion);
		System.out.print("ELIMINO");
		return "/faces/TipoPeticion/listaTipoPeticion.xhtml?faces-redirect=true";

	}
	
	public String EliminarTipoPeticion (int idTipoPeticion) {
		TipoPeticionImp tipopeticionImp = new TipoPeticionImp();
		tipopeticionImp.EliminarTipoPeticion(idTipoPeticion);
		System.out.print("ELIMINO");
		return "/faces/TipoPeticion/listaTipoPeticion.xhtml?faces-redirect=true";
		
	}
	
	public void exportar () throws IOException {
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		response.setContentType("application/octet-stream");
		DateFormat dateFormtter = new SimpleDateFormat("yyyy-MM-dd");
		String curretDateTime =dateFormtter.format(new Date ());
		String headerKey ="Content-Disposition";
		String headerValue ="attachemnt; filename=TipodePeticin" + curretDateTime + ".xlsx";
		response.setHeader (headerKey,headerValue);
		
		TipoPeticionImp tipopeticionImp = new TipoPeticionImp();
		this.listaTipoPeticion = tipopeticionImp.findAll();
		
		ExportarTipoPeticion ex = new ExportarTipoPeticion(this.listaTipoPeticion);
		ex.export(response);
	}
	
}

package com.sena.disocc.app.controlador;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import com.sena.disocc.app.facadeImp.TipoDocumentoImp;
import com.sena.disocc.app.modelo.TiposDocumento;
import com.sena.disocc.app.utilities.ExportarTipoDoc;

@ManagedBean (name="tipodocumentoBean")
@RequestScoped
public class TipoDocumentoBean {

	TiposDocumento tipodoc = new TiposDocumento();
	List<TiposDocumento> listaTipoDoc = new ArrayList<TiposDocumento>();
	private Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
	
	public TiposDocumento getTipodoc() {
		return tipodoc;
	}
	public void setTipodoc(TiposDocumento tipodoc) {
		this.tipodoc = tipodoc;
	}
	public List<TiposDocumento> getListaTipoDoc() {
		return listaTipoDoc;
	}
	public void setListaTipoDoc(List<TiposDocumento> listaTipoDoc) {
		this.listaTipoDoc = listaTipoDoc;
	}
	
	public List<TiposDocumento> findAll(){
	
		TipoDocumentoImp tipodImp = new TipoDocumentoImp();
		this.listaTipoDoc=tipodImp.findAll();
		return this.listaTipoDoc;
	}
	
	public String crearTD(TiposDocumento tipodoc) {
		TipoDocumentoImp tipodImp = new TipoDocumentoImp();
		tipodImp.CrearTipoDoc(tipodoc);
		return "/faces/tiposDocumentos/listaTD.xhtml?faces-redirect=true";
	}
	
	public String insertar () {
		System.out.print("Entro a CREAR");
		this.sessionMap.put("tipodoc", tipodoc);
		return "/faces/tiposDocumentos/crearTD.xhtml?faces-redirect=true";
	}
	
	public String editar (int idTipoDocumento) {
		TipoDocumentoImp tipodImp = new TipoDocumentoImp();
		TiposDocumento tipodoc = new TiposDocumento();
		
		tipodoc=tipodImp.findById(idTipoDocumento);
		System.out.print("Entro a EDITAR "+tipodoc);

		this.sessionMap.put("tipodoc", tipodoc);
		return "/faces/tiposDocumentos/editarTD.xhtml?faces-redirect=true";
	}

	
	public String editarTD(TiposDocumento tipodoc) {
		TipoDocumentoImp tipodImp = new TipoDocumentoImp();
		tipodImp.ActualizarTipoDoc(tipodoc);
		return "/faces/tiposDocumentos/listaTD.xhtml?faces-redirect=true";
	}
	
	public String eliminarTD(int idTipoDocumento ) {
		TipoDocumentoImp tipodImp = new TipoDocumentoImp();
		tipodImp.EliminarTipoDoc(idTipoDocumento);
		System.out.print("ELIMINO");
		return "/faces/tiposDocumentos/listaTD.xhtml?faces-redirect=true";
			
	}
	
	public void exportar () throws IOException {
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		response.setContentType("application/octet-stream");
		DateFormat dateFormtter = new SimpleDateFormat("yyyy-MM-dd");
		String curretDateTime =dateFormtter.format(new Date ());
		String headerKey ="Content-Disposition";
		String headerValue ="attachemnt; filename=ListaTipodocumentos" + curretDateTime + ".xlsx";
		response.setHeader (headerKey,headerValue);
		
		TipoDocumentoImp tipodImp = new TipoDocumentoImp();
		this.listaTipoDoc = tipodImp.findAll();
		
		ExportarTipoDoc exportarTipoDoc = new ExportarTipoDoc(this.listaTipoDoc);
		exportarTipoDoc.export(response);
	}
	
	
}

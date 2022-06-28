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

import com.sena.disocc.app.facadeImp.PermisoImp;
import com.sena.disocc.app.modelo.Permiso;
import com.sena.disocc.app.utilities.ExportarPermisos;

@ManagedBean (name="permisoBean")
@RequestScoped
public class PermisoBean {
	
	Permiso permiso = new Permiso();
	List<Permiso> listaPermiso = new ArrayList<Permiso>();
	private Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
	
	public Permiso getPermiso() {
		return permiso;
	}
	public void setPermiso(Permiso permiso) {
		this.permiso = permiso;
	}
	public List<Permiso> getListaPermiso() {
		return listaPermiso;
	}
	public void setListaPermiso(List<Permiso> listaPermiso) {
		this.listaPermiso = listaPermiso;
	}
	
	public List<Permiso> findAll(){
		PermisoImp permisoImp = new PermisoImp();
		this.listaPermiso=permisoImp.findAll();
		return this.listaPermiso;	
	}
	
	public String crear () {
		System.out.print("Entro a CREAR");
		this.sessionMap.put("permiso", permiso);
		return "/faces/Permiso/crearPermiso.xhtml?faces-redirect=true";
	}
	
	public String crearPermiso(Permiso permiso) {
		
		PermisoImp permisoImp = new PermisoImp();
		permisoImp.crearPermiso(permiso);
		return "/faces/Permiso/listaPermiso.xhtml?faces-redirect=true";

	}
	
	public String actualizar (int idPermiso) {
		
		PermisoImp permisoImp = new PermisoImp();
		Permiso permiso = new Permiso();
		
		permiso=permisoImp.finById(idPermiso);
		System.out.print("Entro a EDITAR "+permiso);
		this.sessionMap.put("permiso", permiso);
		return "/faces/Permiso/editarPermiso.xhtml?faces-redirect=true";
	}
	
	public String actualizarPermiso(Permiso permiso) {
		PermisoImp permisoImp = new PermisoImp();
		permisoImp.actualizarPermiso(permiso);
		return "/faces/Permiso/listaPermiso.xhtml?faces-redirect=true";

	}
	
	public String eliminarPermiso(int idPermiso) {
		PermisoImp permisoImp = new PermisoImp();
		permisoImp.eliminarPermiso(idPermiso);
		System.out.print("ELIMINO");
		return "/faces/Permiso/listaPermiso.xhtml?faces-redirect=true";

	}
	
	public void exportar () throws IOException {
		
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		response.setContentType("application/octet-stream");
		DateFormat dateFormtter = new SimpleDateFormat("yyyy-MM-dd");
		String curretDateTime =dateFormtter.format(new Date ());
		String headerKey ="Content-Disposition";
		String headerValue ="attachemnt; filename=ListaPermisos" + curretDateTime + ".xlsx";
		response.setHeader (headerKey,headerValue);
		
		PermisoImp permisoImp = new PermisoImp();
		this.listaPermiso= permisoImp.findAll();
		
		ExportarPermisos ex = new ExportarPermisos(this.listaPermiso);
		ex.export(response);
	}
	
}

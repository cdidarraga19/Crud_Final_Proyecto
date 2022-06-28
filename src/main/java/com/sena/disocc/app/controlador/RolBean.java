package com.sena.disocc.app.controlador;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import com.sena.disocc.app.facadeImp.RolImp;
import com.sena.disocc.app.modelo.Role;
import com.sena.disocc.app.utilities.ExportarRol;

@ManagedBean (name="rolBean")
@RequestScoped
public class RolBean {
	
	Role rol = new Role();
	List<Role> listaRol = new ArrayList<Role>();
	private Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
	public Role getRol() {
		return rol;
	}
	public void setRol(Role rol) {
		this.rol = rol;
	}
	public List<Role> getListaRol() {
		return listaRol;
	}
	public void setListaRol(List<Role> listaRol) {
		this.listaRol = listaRol;
	}

	public List<Role> findAll(){
		RolImp rolImp = new RolImp();
		this.listaRol=rolImp.findAll();
		return this.listaRol;
	}
	
	public String crear () {
		System.out.print("Entro a CREAR");
		this.sessionMap.put("rol", rol);
		return "/faces/Roles/crearRol.xhtml?faces-redirect=true";
	}
	
	public String crearRol(Role rol){
		RolImp rolImp = new RolImp();
		rolImp.CrearRole(rol);
		return "/faces/Roles/listaRol.xhtml?faces-redirect=true";
	}
	
	public String editar (int idRol) {
		RolImp rolImp = new RolImp();
		Role rol = new Role();
		
		rol=rolImp.findById(idRol);
		System.out.print("Entro a EDITAR "+rol);
		
		this.sessionMap.put("rol", rol);
		return "/faces/Roles/editarRol.xhtml?faces-redirect=true";		
	}
	
	public String editarRol (Role rol) {
		RolImp rolImp = new RolImp();
		rolImp.ActualizarRole(rol);
		return "/faces/Roles/listaRol.xhtml?faces-redirect=true";		

	}
	
	public String eliminarRol(int idRol) {
		RolImp rolImp = new RolImp();
		rolImp.EliminarRole(idRol);
		System.out.print("ELIMINO");
		return "/faces/Roles/listaRol.xhtml?faces-redirect=true";		
	}
	
	public void exportar () throws IOException{
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		response.setContentType("application/octet-stream");
		DateFormat dateFormtter = new SimpleDateFormat("yyyy-MM-dd");
		String curretDateTime =dateFormtter.format(new Date ());
		String headerKey ="Content-Disposition";
		String headerValue ="attachemnt; filename=listaRoles" + curretDateTime + ".xlsx";
		response.setHeader (headerKey,headerValue);
		
		RolImp rolImp = new RolImp();
		this.listaRol = rolImp.findAll();
		
		ExportarRol exportRol = new ExportarRol(this.listaRol);
		exportRol.export(response);
	}
}

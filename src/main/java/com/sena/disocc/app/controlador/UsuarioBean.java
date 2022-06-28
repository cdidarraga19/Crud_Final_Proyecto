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

import com.sena.disocc.app.facadeImp.UsuarioImp;
import com.sena.disocc.app.modelo.Estado;
import com.sena.disocc.app.modelo.Role;
import com.sena.disocc.app.modelo.TiposDocumento;
import com.sena.disocc.app.modelo.Usuario;
import com.sena.disocc.app.utilities.ExportarUsuario;
import com.sena.disocc.app.facadeImp.EstadoImp;
import com.sena.disocc.app.facadeImp.RolImp;
import com.sena.disocc.app.facadeImp.TipoDocumentoImp;


@ManagedBean(name ="usuarioBean")
@RequestScoped
public class UsuarioBean {
	Usuario usuario = new Usuario();
	
	List<Usuario> listaUsuario = new ArrayList<Usuario>();
	List<Role> listaRol = new ArrayList<Role>();
	List <Estado> listaEstado = new ArrayList <Estado>();
	List<TiposDocumento> listaTipodoc = new ArrayList<TiposDocumento>();

	private Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
	
	private int idRolFK;
	private int idEstadoFK;
	private int idTipoDocFK;


	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public List<Usuario> getListaUsuario() {
		return listaUsuario;
	}
	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}	
	
	public List<Role> getListaRol() {
		return listaRol;
	}
	public void setListaRol(List<Role> listaRol) {
		this.listaRol = listaRol;
	}
	
	public int getidRolFK() {
		return idRolFK;
	}
	public void setidRolFK(int idRolFK) {
		this.idRolFK = idRolFK;
	}
	
	public int getIdEstadoFK() {
		return idEstadoFK;
	}
	public void setIdEstadoFK(int idEstadoFK) {
		this.idEstadoFK = idEstadoFK;
	}
	
	public List<Estado> getListaEstado() {
		return listaEstado;
	}
	public void setListaEstado(List<Estado> listaEstado) {
		this.listaEstado = listaEstado;
	}	
	
	public List<TiposDocumento> getListaTipodoc() {
		return listaTipodoc;
	}
	public void setListaTipodoc(List<TiposDocumento> listaTipodoc) {
		this.listaTipodoc = listaTipodoc;
	}
	public int getIdTipoDocFK() {
		return idTipoDocFK;
	}
	public void setIdTipoDocFK(int idTipoDocFK) {
		this.idTipoDocFK = idTipoDocFK;
	}
	
	
	public void llenarRol() {
		RolImp rolImp = new RolImp();
		this.listaRol = rolImp.findAll();
	}
	
	public void llenarEstado() {
		EstadoImp estadoImp = new EstadoImp();
		this.listaEstado = estadoImp.findAll();
	}
	public void llenarTipoDoc() {
		TipoDocumentoImp tipodocImp = new TipoDocumentoImp();
		this.listaTipodoc = tipodocImp.findAll();
	}
	
	public UsuarioBean() {
		this.llenarRol();
		this.llenarEstado();
		this.llenarTipoDoc();
	}
	
	public List<Usuario> findAll(){
		
		UsuarioImp usuarioImp = new UsuarioImp();
		this.listaUsuario=usuarioImp.findAll();
		return this.listaUsuario;
	}
	
	public String crearUsuario(Usuario usuario) {
		
		UsuarioImp usuarioImp = new UsuarioImp();
		
		RolImp rolImp = new RolImp();
		Role role = new Role();
		role = rolImp.findById(idRolFK);
		usuario.setRole(role);
		
		EstadoImp estadoImp = new EstadoImp();
		Estado estado = new Estado();
		estado = estadoImp.findById(idEstadoFK);
		usuario.setEstado(estado);
		
		TipoDocumentoImp tipodocImp = new TipoDocumentoImp();
		TiposDocumento tipoDoc = new TiposDocumento();
		tipoDoc = tipodocImp.findById(idTipoDocFK);
		usuario.setTiposDocumento(tipoDoc);
		
		usuarioImp.CrearUsu(usuario);
		return "/faces/html/login.xhtml?faces-redirect=true";
	}
	
	public String crear () {
		System.out.print("Entro a CREAR");
		this.sessionMap.put("usuario", usuario);
		return "/faces/html/registro.xhtml?faces-redirect=true";
 
	}
	
	public String actualizar (int idUsuario) {
		UsuarioImp usuarioImp = new UsuarioImp();
		Usuario usuario = new Usuario();
		
		usuario=usuarioImp.findById(idUsuario);
		System.out.print("Entro a EDITAR "+usuario);
		
		this.sessionMap.put("usuario", usuario);
		return "/faces/Usuarios/editarUsuario.xhtml?faces-redirect=true";
	}
	
	public String actualizarUsuario(Usuario usuario) {
		
		UsuarioImp usuarioImp = new UsuarioImp();
		usuarioImp.ActualizarUsu(usuario);
		return "/faces/Usuarios/listaUsuario.xhtml?faces-redirect=true";

	}
	
	public String eliminarUsuario(int idUsuario) {
		UsuarioImp usuarioImp = new UsuarioImp();
		usuarioImp.EliminarUsu(idUsuario);
		System.out.print("ELIMINO");
		return "/faces/Usuarios/listaUsuario.xhtml?faces-redirect=true";
		
	}
	
	public void exportar () throws IOException{
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		response.setContentType("application/octet-stream");
		DateFormat dateFormtter = new SimpleDateFormat("yyyy-MM-dd");
		String curretDateTime =dateFormtter.format(new Date ());
		String headerKey ="Content-Disposition";
		String headerValue ="attachemnt; filename=ListaUsuario" + curretDateTime + ".xlsx";
		response.setHeader (headerKey,headerValue);
		
		UsuarioImp usuarioImp = new UsuarioImp();
		if(idRolFK!=0 & idEstadoFK!=0) {
			this.listaUsuario = usuarioImp.exportRolEstado(idRolFK,idEstadoFK);
		}		
		else if (idRolFK!=0){
			this.listaUsuario = usuarioImp.exportPorRol(idRolFK);			
		}
		else if (idEstadoFK!=0){
			this.listaUsuario = usuarioImp.exportPorEstado(idEstadoFK);
		}
		else {
			this.listaUsuario = usuarioImp.findAll();
		}
		
		ExportarUsuario exportarUsuario = new ExportarUsuario(this.listaUsuario);
		exportarUsuario.export(response);
	}
		

}

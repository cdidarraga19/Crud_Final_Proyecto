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

import com.sena.disocc.app.facadeImp.CategoriaPImp;
import com.sena.disocc.app.modelo.CategoriasProducto;
import com.sena.disocc.app.utilities.ExportarCategoriaPro;

@ManagedBean (name="categoriaPBean")
@RequestScoped
public class CategoriaProBean {
	
	CategoriasProducto categoriaP = new CategoriasProducto();
	List<CategoriasProducto> listaCategoriasProducto = new ArrayList<CategoriasProducto>();
	private Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
	
	public CategoriasProducto getCategoriaP() {
		return categoriaP;
	}
	public void setCategoriaP(CategoriasProducto categoriaP) {
		this.categoriaP = categoriaP;
	}
	public List<CategoriasProducto> getListaCategoriasProducto() {
		return listaCategoriasProducto;
	}
	public void setListaCategoriasProducto(List<CategoriasProducto> listaCategoriasProducto) {
		this.listaCategoriasProducto = listaCategoriasProducto;
	}
	
	public List<CategoriasProducto> findAll(){
		CategoriaPImp categoriaPImp = new CategoriaPImp();
		this.listaCategoriasProducto=categoriaPImp.findAll();
		return this.listaCategoriasProducto;
	}
	
	public String crear () {
		
		System.out.print("Entro a CREAR");
		this.sessionMap.put("categoriaP", categoriaP);
		return "/faces/CategoriaProducto/crearCategoria.xhtml?faces-redirect=true";
	}
	
	public String crearCategoria(CategoriasProducto categoriaP) {
		
		CategoriaPImp categoriaPImp = new CategoriaPImp();
		categoriaPImp.crearCategoriaP(categoriaP);
		return "/faces/CategoriaProducto/listaCategoria.xhtml?faces-redirect=true";
	}
	
	public String actualizar (int idCategoria) {
		CategoriaPImp categoriaPImp = new CategoriaPImp();
		CategoriasProducto categoriaP = new CategoriasProducto();
		
		categoriaP = categoriaPImp.findById(idCategoria);
		System.out.print("Entro a EDITAR "+categoriaP);
		this.sessionMap.put("categoriaP", categoriaP);
		return "/faces/CategoriaProducto/editarCategoria.xhtml?faces-redirect=true";
		
	}
	
	public String actualizarCategoriaP(CategoriasProducto categoriaP) {
		
		CategoriaPImp categoriaPImp = new CategoriaPImp();
		categoriaPImp.actualizarCategoriaP(categoriaP);
		return "/faces/CategoriaProducto/listaCategoria.xhtml?faces-redirect=true";

	}
	
	public String eliminarCategoriaP (int idCategoria) {	
		CategoriaPImp categoriaPImp = new CategoriaPImp();
		categoriaPImp.eliminarCategoriaP(idCategoria);
		System.out.print("ELIMINO");
		return "/faces/CategoriaProducto/listaCategoria.xhtml?faces-redirect=true";

	}
	
	public void exportar () throws IOException {
		
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		response.setContentType("application/octet-stream");
		DateFormat dateFormtter = new SimpleDateFormat("yyyy-MM-dd");
		String curretDateTime =dateFormtter.format(new Date ());
		String headerKey ="Content-Disposition";
		String headerValue ="attachemnt; filename=ListaUsuario" + curretDateTime + ".xlsx";
		response.setHeader (headerKey,headerValue);
		
		CategoriaPImp categoriaPImp = new CategoriaPImp();
		this.listaCategoriasProducto =categoriaPImp.findAll();
		
		ExportarCategoriaPro exportar = new ExportarCategoriaPro(this.listaCategoriasProducto);
		exportar.export(response);
		
	}
	
	
	
}

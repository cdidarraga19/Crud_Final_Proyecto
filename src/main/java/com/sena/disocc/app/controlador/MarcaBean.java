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

import com.sena.disocc.app.facadeImp.MarcaImp;
import com.sena.disocc.app.modelo.Marca;
import com.sena.disocc.app.utilities.ExportarMarca;

@ManagedBean (name="marcaBean")
@RequestScoped
public class MarcaBean {
	
	Marca marca = new Marca();
	List<Marca> listaMarca = new ArrayList<Marca>();
	private Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
	
	public Marca getMarca() {
		return marca;
	}
	public void setMarca(Marca marca) {
		this.marca = marca;
	}
	public List<Marca> getListaMarca() {
		return listaMarca;
	}
	public void setListaMarca(List<Marca> listaMarca) {
		this.listaMarca = listaMarca;
	}
	
	public List<Marca> findAll(){
		
		MarcaImp marcaImp = new MarcaImp();
		this.listaMarca = marcaImp.findAll();
		return this.listaMarca;
	}
	
	public String crear () {
		
		System.out.print("Entro a CREAR");
		this.sessionMap.put("marca", marca);
		return "/faces/Marca/crearMarca.xhtml?faces-redirect=true";
	}
	
	public String crearMarca (Marca marca) {
		
		MarcaImp marcaImp = new MarcaImp();
		marcaImp.crearMarca(marca);
		return "/faces/Marca/listaMarca.xhtml?faces-redirect=true";
 
	}
	
	public String actualizar (int idMarca) {
		
		MarcaImp marcaImp = new MarcaImp();
		Marca marca = new Marca();
		marca=marcaImp.finById(idMarca);
		System.out.print("Entro a EDITAR "+marca);
		this.sessionMap.put("marca", marca);
		return "/faces/Marca/editarMarca.xhtml?faces-redirect=true";

	}
	
	public String actualizarMarca(Marca marca) {
		
		MarcaImp marcaImp = new MarcaImp();
		marcaImp.actualizarMarca(marca);
		return "/faces/Marca/listaMarca.xhtml?faces-redirect=true";

	}
	
	public String eliminarMarca(int idMarca) {
		
		MarcaImp marcaImp = new MarcaImp();
		marcaImp.eliminarMarca(idMarca);
		System.out.print("ELIMINO");
		return "/faces/Marca/listaMarca.xhtml?faces-redirect=true";

	}
	
	public void exportar () throws IOException{
		
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		response.setContentType("application/octet-stream");
		DateFormat dateFormtter = new SimpleDateFormat("yyyy-MM-dd");
		String curretDateTime =dateFormtter.format(new Date ());
		String headerKey ="Content-Disposition";
		String headerValue ="attachemnt; filename=ListaMarcas" + curretDateTime + ".xlsx";
		response.setHeader (headerKey,headerValue);
		
		MarcaImp marcaImp = new MarcaImp();
		this.listaMarca = marcaImp.findAll();
		
		ExportarMarca ex = new ExportarMarca(this.listaMarca);
		ex.export(response);
		
	}
	
}

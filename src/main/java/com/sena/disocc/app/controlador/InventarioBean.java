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

import com.sena.disocc.app.facadeImp.InventarioImp;
import com.sena.disocc.app.modelo.Inventario;
import com.sena.disocc.app.utilities.ExportarInventario;

@ManagedBean(name = "inventarioBean")
@RequestScoped
public class InventarioBean {
	Inventario inventario = new Inventario();
	List<Inventario> listaInventario = new ArrayList<Inventario>();
	private Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

	public Inventario getInventario() {
		return inventario;
	}

	public void setInventario(Inventario inventario) {
		this.inventario = inventario;
	}

	public List<Inventario> getListaInventario() {
		return listaInventario;
	}

	public void setListaInventario(List<Inventario> listaInventario) {
		this.listaInventario = listaInventario;
	}

	public List<Inventario> findAll() {
		InventarioImp inventarioImp = new InventarioImp();
		this.listaInventario = inventarioImp.findAll();
		return this.listaInventario;
	}

	public String crear() {
		System.out.print("Entro a CREAR");
		this.sessionMap.put("inventario", inventario);
		return "/faces/Inventarios/crearInventario.xhtml?faces-redirect=true";
	}

	public String crearInventario(Inventario inventario) {
		InventarioImp inventarioImp = new InventarioImp();
		inventarioImp.CrearInventario(inventario);
		return "/faces/Inventarios/listaInventario.xhtml?faces-redirect=true";
	}

	public String editar(int idInventario) {
		InventarioImp inventarioImp = new InventarioImp();
		Inventario inventario = new Inventario();

		inventario = inventarioImp.findById(idInventario);
		System.out.print("Entro a EDITAR" + inventario);

		this.sessionMap.put("inventario", inventario);
		return "/faces/Inventarios/editarInventario.xhtml?faces-redirect=true";
	}

	public String editarInventario(Inventario inventario) {
		InventarioImp inventarioImp = new InventarioImp();
		inventarioImp.ActualizarInventario(inventario);
		return "/faces/Inventarios/listaInventario.xhtml?faces-redirect=true";
	}

	public String eliminarInventario(int idInventario) {
		InventarioImp inventarioImp = new InventarioImp();
		inventarioImp.EliminarInventario(idInventario);
		return "/faces/Inventarios/listaInventario.xhtml?faces-redirect=true";
	}
	public void exportar () throws IOException {
		
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		response.setContentType("application/octet-stream");
		DateFormat dateFormtter = new SimpleDateFormat("yyyy-MM-dd");
		String curretDateTime =dateFormtter.format(new Date ());
		String headerKey ="Content-Disposition";
		String headerValue ="attachemnt; filename=ListaInventario" + curretDateTime + ".xlsx";
		response.setHeader (headerKey,headerValue);
		
		InventarioImp inventarioImp = new InventarioImp();
		this.listaInventario = inventarioImp.findAll();
		
		ExportarInventario ex = new ExportarInventario(this.listaInventario);
		ex.export(response);
	}
}

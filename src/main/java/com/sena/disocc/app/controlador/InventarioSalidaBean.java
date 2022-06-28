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

import com.sena.disocc.app.facadeImp.DetalleVentaImp;
import com.sena.disocc.app.facadeImp.InventarioImp;
import com.sena.disocc.app.facadeImp.InventarioSalidaImp;
import com.sena.disocc.app.modelo.DetallesVenta;
import com.sena.disocc.app.modelo.Inventario;
import com.sena.disocc.app.modelo.InventarioSalida;
import com.sena.disocc.app.utilities.ExportarInvSalida;
@ManagedBean(name = "inventariosalidaBean")
@RequestScoped
public class InventarioSalidaBean {
	InventarioSalida inventariosalida = new InventarioSalida();
	
	List<InventarioSalida> listaInventarioSalida = new ArrayList<InventarioSalida>();
	List<Inventario> listaInventario = new ArrayList<Inventario>();
	List<DetallesVenta> listaDetalleVenta = new ArrayList<DetallesVenta>();
	
	private Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
	
	private int idInventarioFK;
	private int idDetalleVentaFK;
	
	public InventarioSalida getInventariosalida() {
		return inventariosalida;
	}
	public void setInventariosalida(InventarioSalida inventariosalida) {
		this.inventariosalida = inventariosalida;
	}
	public List<InventarioSalida> getListaInventarioSalida() {
		return listaInventarioSalida;
	}
	public void setListaInventarioSalida(List<InventarioSalida> listaInventarioSalida) {
		this.listaInventarioSalida = listaInventarioSalida;
	}
	public List<Inventario> getListaInventario() {
		return listaInventario;
	}
	public void setListaInventario(List<Inventario> listaInventario) {
		this.listaInventario = listaInventario;
	}
	public List<DetallesVenta> getListaDetalleVenta() {
		return listaDetalleVenta;
	}
	public void setListaDetalleVenta(List<DetallesVenta> listaDetalleVenta) {
		this.listaDetalleVenta = listaDetalleVenta;
	}
	public int getIdInventarioFK() {
		return idInventarioFK;
	}
	public void setIdInventarioFK(int idInventarioFK) {
		this.idInventarioFK = idInventarioFK;
	}
	public int getIdDetalleVentaFK() {
		return idDetalleVentaFK;
	}
	public void setIdDetalleVentaFK(int idDetalleVentaFK) {
		this.idDetalleVentaFK = idDetalleVentaFK;
	}
	
	public List<InventarioSalida> findAll() {
		InventarioSalidaImp inventarioSalidaImp = new InventarioSalidaImp();
		this.listaInventarioSalida = inventarioSalidaImp.findAll();
		return this.listaInventarioSalida;
	}
	
	public void llenarInventario() {
		InventarioImp inventarioImp = new InventarioImp();
		this.listaInventario = inventarioImp.findAll();
	}
	
	public void llenarDetalleVenta() {
		DetalleVentaImp detalleVentaImp = new DetalleVentaImp();
		this.listaDetalleVenta = detalleVentaImp.findAll();
	}
	
	public InventarioSalidaBean() {
		this.llenarInventario();
		this.llenarDetalleVenta();
	}
	
	public String crear() {
		System.out.print("Entro a CREAR");
		this.sessionMap.put("inventariosalida", inventariosalida);
		return "/faces/InventarioSalidas/crearInventarioSalida.xhtml?faces-redirect=true";
	}
	
	public String crearInventarioSalida(InventarioSalida inventariosalida) {
		InventarioSalidaImp inventariosalidaImp = new InventarioSalidaImp();
		
		InventarioImp iventarioImp = new InventarioImp();
		Inventario inventario = new Inventario();
		inventario = iventarioImp.findById(idInventarioFK);
		inventariosalida.setInventario(inventario);
		
		DetalleVentaImp detalleVentaImp = new DetalleVentaImp();
		DetallesVenta detalleVenta = new DetallesVenta();
		detalleVenta = detalleVentaImp.findById(idDetalleVentaFK);
		inventariosalida.setDetallesVenta(detalleVenta);
		
		inventariosalidaImp.CrearInventarioSalida(inventariosalida);
		return "/faces/InventarioSalidas/listaInventarioSalida.xhtml?faces-redirect=true";
	}
	
	public String editar(int id) {
		InventarioSalidaImp inventariosalidaImp = new InventarioSalidaImp();
		InventarioSalida inventariosalida = new InventarioSalida();

		inventariosalida = inventariosalidaImp.findById(id);
		System.out.print("Entro a EDITAR" + inventariosalida);

		this.sessionMap.put("inventariosalida", inventariosalida);
		return "/faces/InventarioSalidas/editarInventarioSalida.xhtml?faces-redirect=true";
	}
	
	public String editarInventarioSalida(InventarioSalida inventariosalida) {
		InventarioSalidaImp inventariosalidaImp = new InventarioSalidaImp();
		inventariosalidaImp.ActualizarInventarioSalida(inventariosalida);
		return "/faces/InventarioSalidas/listaInventarioSalida.xhtml?faces-redirect=true";
	}
	
	public String eliminarInventarioSalida(int id) {
		InventarioSalidaImp inventariosalidaImp = new InventarioSalidaImp();
		inventariosalidaImp.EliminarInventarioSalida(id);
		return "/faces/InventarioSalidas/listaInventarioSalida.xhtml?faces-redirect=true";
	}
	public void exportar () throws IOException {
		
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		response.setContentType("application/octet-stream");
		DateFormat dateFormtter = new SimpleDateFormat("yyyy-MM-dd");
		String curretDateTime =dateFormtter.format(new Date ());
		String headerKey ="Content-Disposition";
		String headerValue ="attachemnt; filename=ListaInventarioSalida" + curretDateTime + ".xlsx";
		response.setHeader (headerKey,headerValue);
		
		InventarioSalidaImp inventarioSalidaImp = new InventarioSalidaImp();
		this.listaInventarioSalida = inventarioSalidaImp.findAll();
		
		ExportarInvSalida ex = new ExportarInvSalida(this.listaInventarioSalida);
		ex.export(response);
		
	}
}

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

import com.sena.disocc.app.facadeImp.InventarioEntradaImp;
import com.sena.disocc.app.facadeImp.InventarioImp;
import com.sena.disocc.app.facadeImp.ProductoImp;
import com.sena.disocc.app.modelo.Inventario;
import com.sena.disocc.app.modelo.InventarioEntrada;
import com.sena.disocc.app.modelo.Producto;
import com.sena.disocc.app.utilities.ExportarInvEntrada;

@ManagedBean(name = "inventarioentradaBean")
@RequestScoped
public class InventarioEntradaBean {
	InventarioEntrada inventarioentrada = new InventarioEntrada();
	
	List<InventarioEntrada> listaInventarioEntrada = new ArrayList<InventarioEntrada>();
	List<Producto> listaProducto = new ArrayList<Producto>();
	List<Inventario> listaInventario = new ArrayList<Inventario>();
	
	private Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
	
	private int idProductoFK;
	private int idInventarioFK;
	
	public InventarioEntrada getInventarioentrada() {
		return inventarioentrada;
	}
	public void setInventarioentrada(InventarioEntrada inventarioentrada) {
		this.inventarioentrada = inventarioentrada;
	}
	public List<InventarioEntrada> getListaInventarioEntrada() {
		return listaInventarioEntrada;
	}
	public void setListaInventarioEntrada(List<InventarioEntrada> listaInventarioEntrada) {
		this.listaInventarioEntrada = listaInventarioEntrada;
	}
	public List<Producto> getListaProducto() {
		return listaProducto;
	}
	public void setListaProducto(List<Producto> listaProducto) {
		this.listaProducto = listaProducto;
	}
	public List<Inventario> getListaInventario() {
		return listaInventario;
	}
	public void setListaInventario(List<Inventario> listaInventario) {
		this.listaInventario = listaInventario;
	}
	public int getIdProductoFK() {
		return idProductoFK;
	}
	public void setIdProductoFK(int idProductoFK) {
		this.idProductoFK = idProductoFK;
	}
	public int getIdInventarioFK() {
		return idInventarioFK;
	}
	public void setIdInventarioFK(int idInventarioFK) {
		this.idInventarioFK = idInventarioFK;
	}
	
	public List<InventarioEntrada> findAll() {
		InventarioEntradaImp inventarioEntradaImp = new InventarioEntradaImp();
		this.listaInventarioEntrada = inventarioEntradaImp.findAll();
		return this.listaInventarioEntrada;
	}
	
	public void llenarProducto() {
		ProductoImp productoImp = new ProductoImp();
		this.listaProducto = productoImp.finAll();
	}
	
	public void llenarInventario() {
		InventarioImp inventarioImp = new InventarioImp();
		this.listaInventario = inventarioImp.findAll();
	}
	public InventarioEntradaBean() {
		this.llenarProducto();
		this.llenarInventario();
	}
	
	public String crear() {
		System.out.print("Entro a CREAR");
		this.sessionMap.put("inventarioentrada", inventarioentrada);
		return "/faces/InventarioEntradas/crearInventarioEntrada.xhtml?faces-redirect=true";
	}
	
	public String crearInventarioEntrada(InventarioEntrada inventarioentrada) {
		InventarioEntradaImp inventarioentradaImp = new InventarioEntradaImp();
		
		ProductoImp productoImp = new ProductoImp();
		Producto producto = new Producto();
		producto = productoImp.findById(idProductoFK);
		inventarioentrada.setProducto(producto);
		
		InventarioImp iventarioImp = new InventarioImp();
		Inventario inventario = new Inventario();
		inventario = iventarioImp.findById(idInventarioFK);
		inventarioentrada.setInventario(inventario);
		
		inventarioentradaImp.CrearInventarioEntrada(inventarioentrada);
		return "/faces/InventarioEntradas/listaInventarioEntrada.xhtml?faces-redirect=true";
	}

	public String editar(int id) {
		InventarioEntradaImp inventarioentradaImp = new InventarioEntradaImp();
		InventarioEntrada inventarioentrada = new InventarioEntrada();

		inventarioentrada = inventarioentradaImp.findById(id);
		System.out.print("Entro a EDITAR" + inventarioentrada);

		this.sessionMap.put("inventarioentrada", inventarioentrada);
		return "/faces/InventarioEntradas/editarInventarioEntrada.xhtml?faces-redirect=true";
	}

	public String editarInventarioEntrada(InventarioEntrada inventarioentrada) {
		InventarioEntradaImp inventarioentradaImp = new InventarioEntradaImp();
		inventarioentradaImp.ActualizarInventarioEntrada(inventarioentrada);
		return "/faces/InventarioEntradas/listaInventarioEntrada.xhtml?faces-redirect=true";
	}

	public String eliminarInventarioEntrada(int id) {
		InventarioEntradaImp inventarioentradaImp = new InventarioEntradaImp();
		inventarioentradaImp.EliminarInventarioEntrada(id);
		return "/faces/InventarioEntradas/listaInventarioEntrada.xhtml?faces-redirect=true";
	}
	
	public void exportar () throws IOException {
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		response.setContentType("application/octet-stream");
		DateFormat dateFormtter = new SimpleDateFormat("yyyy-MM-dd");
		String curretDateTime =dateFormtter.format(new Date ());
		String headerKey ="Content-Disposition";
		String headerValue ="attachemnt; filename=listaInventarioEntrada" + curretDateTime + ".xlsx";
		response.setHeader (headerKey,headerValue);
		
		InventarioEntradaImp inventarioEntradaImp = new InventarioEntradaImp();
		this.listaInventarioEntrada = inventarioEntradaImp.findAll();
		
		ExportarInvEntrada ex = new ExportarInvEntrada(this.listaInventarioEntrada);
		
		ex.export(response);
	}
}

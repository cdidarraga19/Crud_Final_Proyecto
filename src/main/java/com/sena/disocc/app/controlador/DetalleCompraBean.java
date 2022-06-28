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

import com.sena.disocc.app.facadeImp.CompraImp;
import com.sena.disocc.app.facadeImp.DetalleCompraImp;
import com.sena.disocc.app.facadeImp.ProductoImp;
import com.sena.disocc.app.modelo.Compra;
import com.sena.disocc.app.modelo.DetallesCompra;
import com.sena.disocc.app.modelo.Producto;
import com.sena.disocc.app.utilities.ExportarDetalleCompra;

@ManagedBean(name = "detallecompraBean")
@RequestScoped
public class DetalleCompraBean {
	DetallesCompra detallescompra = new DetallesCompra();

	List<DetallesCompra> listaDetalleCompra = new ArrayList<DetallesCompra>();
	List<Compra> listaCompra = new ArrayList<Compra>();
	List<Producto> listaProducto = new ArrayList<Producto>();

	private Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

	private int idCompraFK;
	private int idProductoFK;

	public List<Compra> getListaCompra() {
		return listaCompra;
	}

	public void setListaCompra(List<Compra> listaCompra) {
		this.listaCompra = listaCompra;
	}

	public List<Producto> getListaProducto() {
		return listaProducto;
	}

	public void setListaProducto(List<Producto> listaProducto) {
		this.listaProducto = listaProducto;
	}

	public int getIdCompraFK() {
		return idCompraFK;
	}

	public void setIdCompraFK(int idCompraFK) {
		this.idCompraFK = idCompraFK;
	}

	public int getIdProductoFK() {
		return idProductoFK;
	}

	public void setIdProductoFK(int idProductoFK) {
		this.idProductoFK = idProductoFK;
	}

	public DetallesCompra getDetallescompra() {
		return detallescompra;
	}

	public void setDetallescompra(DetallesCompra detallescompra) {
		this.detallescompra = detallescompra;
	}

	public List<DetallesCompra> getListaDetalleCompra() {
		return listaDetalleCompra;
	}

	public void setListaDetalleCompra(List<DetallesCompra> listaDetalleCompra) {
		this.listaDetalleCompra = listaDetalleCompra;
	}

	public List<DetallesCompra> findAll() {
		DetalleCompraImp detallecompraImp = new DetalleCompraImp();
		this.listaDetalleCompra = detallecompraImp.findAll();
		return this.listaDetalleCompra;
	}

	public void llenarCompra() {
		CompraImp compraImp = new CompraImp();
		this.listaCompra = compraImp.findAll();
	}

	public void llenarProducto() {
		ProductoImp productoImp = new ProductoImp();
		this.listaProducto = productoImp.finAll();
	}

	public DetalleCompraBean() {
		this.llenarCompra();
		this.llenarProducto();
	}

	public String crear() {
		System.out.print("Entro a CREAR");
		this.sessionMap.put("detallescompra", detallescompra);
		return "/faces/DetallesCompra/crearDetalleCompra.xhtml?faces-redirect=true";
	}

	public String crearDetalleCompra(DetallesCompra detallescompra) {
		DetalleCompraImp detallecompraImp = new DetalleCompraImp();

		CompraImp compraImp = new CompraImp();
		Compra compra = new Compra();
		compra = compraImp.findById(idCompraFK);
		detallescompra.setCompra(compra);

		ProductoImp productoImp = new ProductoImp();
		Producto producto = new Producto();
		producto = productoImp.findById(idProductoFK);
		detallescompra.setProducto(producto);

		detallecompraImp.CrearDetalleCompra(detallescompra);
		return "/faces/DetallesCompra/listaDetalleCompra.xhtml?faces-redirect=true";
	}

	public String editar(int id) {
		DetalleCompraImp detallecompraImp = new DetalleCompraImp();
		DetallesCompra detallecompra = new DetallesCompra();

		detallecompra = detallecompraImp.findById(id);
		System.out.print("Entro a EDITAR" + detallecompra);

		this.sessionMap.put("detallescompra", detallecompra);
		return "/faces/DetallesCompra/editarDetalleCompra.xhtml?faces-redirect=true";
	}

	public String editarDetalleCompra(DetallesCompra detallescompra) {
		DetalleCompraImp detallecompraImp = new DetalleCompraImp();
		detallecompraImp.ActualizarDetalleCompra(detallescompra);
		return "/faces/DetallesCompra/listaDetalleCompra.xhtml?faces-redirect=true";
	}

	public String eliminarDetalleCompra(int id) {
		DetalleCompraImp detallecompraImp = new DetalleCompraImp();
		detallecompraImp.EliminarDetalleCompra(id);
		return "/faces/DetallesCompra/listaDetalleCompra.xhtml?faces-redirect=true";
	}
	
	public void exportar () throws IOException {
		
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		response.setContentType("application/octet-stream");
		DateFormat dateFormtter = new SimpleDateFormat("yyyy-MM-dd");
		String curretDateTime =dateFormtter.format(new Date ());
		String headerKey ="Content-Disposition";
		String headerValue ="attachemnt; filename=ListaDetallesCompras" + curretDateTime + ".xlsx";
		response.setHeader (headerKey,headerValue);
		
		DetalleCompraImp detalleCompraImp = new DetalleCompraImp();
		this.listaDetalleCompra = detalleCompraImp.findAll();
		
		ExportarDetalleCompra ex = new ExportarDetalleCompra(this.listaDetalleCompra);
		ex.export(response);
	}
}

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

import com.sena.disocc.app.facadeImp.ProductoImp;
import com.sena.disocc.app.facadeImp.UsuarioImp;
import com.sena.disocc.app.facadeImp.VentaImp;
import com.sena.disocc.app.modelo.Producto;
import com.sena.disocc.app.modelo.Usuario;
import com.sena.disocc.app.modelo.Venta;
import com.sena.disocc.app.utilities.ExportarVentas;

@ManagedBean(name = "ventaBean")
@RequestScoped
public class VentaBean {
	Venta venta = new Venta();
	
	List<Venta> listaVenta = new ArrayList<Venta>();
	List<Producto> listaProducto = new ArrayList<Producto>();
	List <Usuario> listaUsuario = new ArrayList <Usuario>();
	
	private Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
	
	private int idProductoFK;
	private int idUsuarioFK;
	
	public Venta getVenta() {
		return venta;
	}
	public void setVenta(Venta venta) {
		this.venta = venta;
	}
	public List<Venta> getListaVenta() {
		return listaVenta;
	}
	public void setListaVenta(List<Venta> listaVenta) {
		this.listaVenta = listaVenta;
	}
	public List<Producto> getListaProducto() {
		return listaProducto;
	}
	public void setListaProducto(List<Producto> listaProducto) {
		this.listaProducto = listaProducto;
	}
	public List<Usuario> getListaUsuario() {
		return listaUsuario;
	}
	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}
	public int getIdProductoFK() {
		return idProductoFK;
	}
	public void setIdProductoFK(int idProductoFK) {
		this.idProductoFK = idProductoFK;
	}
	public int getIdUsuarioFK() {
		return idUsuarioFK;
	}
	public void setIdUsuarioFK(int idUsuarioFK) {
		this.idUsuarioFK = idUsuarioFK;
	}
	
	public List<Venta> findAll() {
		VentaImp ventaImp = new VentaImp();
		this.listaVenta = ventaImp.findAll();
		return this.listaVenta;
	}
	
	public void llenarProducto() {
		ProductoImp productoImp = new ProductoImp();
		this.listaProducto = productoImp.finAll();
	}
	
	public void llenarUsuario() {
		UsuarioImp usuarioImp = new UsuarioImp();
		this.listaUsuario = usuarioImp.findAll();
	}
	
	public VentaBean() {
		this.llenarProducto();
		this.llenarUsuario();
	}
	
	public String crear() {
		System.out.print("Entro a CREAR");
		this.sessionMap.put("venta", venta);
		return "/faces/Ventas/crearVenta.xhtml?faces-redirect=true";
	}
	
	public String crearVenta(Venta venta) {
		VentaImp ventaImp = new VentaImp();
		
		ProductoImp productoImp = new ProductoImp();
		Producto producto = new Producto();
		producto = productoImp.findById(idProductoFK);
		venta.setProducto(producto);
		
		UsuarioImp usuarioImp = new UsuarioImp();
		Usuario usuario = new Usuario();
		usuario = usuarioImp.findById(idUsuarioFK);
		venta.setUsuario(usuario);
		
		ventaImp.CrearVenta(venta);
		return "/faces/Ventas/listaVenta.xhtml?faces-redirect=true";
	}
	
	public String editar(int idVenta) {
		VentaImp ventaImp = new VentaImp();
		Venta venta = new Venta();

		venta = ventaImp.findById(idVenta);
		System.out.print("Entro a EDITAR" + venta);

		this.sessionMap.put("venta", venta);
		return "/faces/Ventas/editarVenta.xhtml?faces-redirect=true";
	}
	
	public String editarVenta(Venta venta) {
		VentaImp ventaImp = new VentaImp();
		ventaImp.ActualizarVenta(venta);
		return "/faces/Ventas/listaVenta.xhtml?faces-redirect=true";
	}
	
	public String eliminarVenta(int idVenta) {
		VentaImp ventaImp = new VentaImp();;
		ventaImp.EliminarVenta(idVenta);
		return "/faces/Ventas/listaVenta.xhtml?faces-redirect=true";
	}
	
	public void exportar () throws IOException {
		
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		response.setContentType("application/octet-stream");
		DateFormat dateFormtter = new SimpleDateFormat("yyyy-MM-dd");
		String curretDateTime =dateFormtter.format(new Date ());
		String headerKey ="Content-Disposition";
		String headerValue ="attachemnt; filename=ListaVentas" + curretDateTime + ".xlsx";
		response.setHeader (headerKey,headerValue);
		
		VentaImp ventaImp = new VentaImp();
		this.listaVenta = ventaImp.findAll();
		
		ExportarVentas ex = new ExportarVentas(this.listaVenta);
		ex.export(response);
	}
}

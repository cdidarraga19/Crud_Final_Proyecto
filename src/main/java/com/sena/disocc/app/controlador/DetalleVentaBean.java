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
import com.sena.disocc.app.facadeImp.VentaImp;
import com.sena.disocc.app.modelo.DetallesVenta;
import com.sena.disocc.app.modelo.Venta;
import com.sena.disocc.app.utilities.ExportarDetalleVenta;

@ManagedBean(name = "detalleventaBean")
@RequestScoped
public class DetalleVentaBean {
	DetallesVenta detalleventa = new DetallesVenta();

	List<DetallesVenta> listaDetalleVenta = new ArrayList<DetallesVenta>();
	List<Venta> listaVenta = new ArrayList<Venta>();

	private Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

	private int idVentaFK;

	public DetallesVenta getDetalleventa() {
		return detalleventa;
	}

	public void setDetalleventa(DetallesVenta detalleventa) {
		this.detalleventa = detalleventa;
	}

	public List<DetallesVenta> getListaDetalleVenta() {
		return listaDetalleVenta;
	}

	public void setListaDetalleVenta(List<DetallesVenta> listaDetalleVenta) {
		this.listaDetalleVenta = listaDetalleVenta;
	}

	public List<Venta> getListaVenta() {
		return listaVenta;
	}

	public void setListaVenta(List<Venta> listaVenta) {
		this.listaVenta = listaVenta;
	}

	public int getIdVentaFK() {
		return idVentaFK;
	}

	public void setIdVentaFK(int idVentaFK) {
		this.idVentaFK = idVentaFK;
	}

	public List<DetallesVenta> findAll() {
		DetalleVentaImp detalleventaImp = new DetalleVentaImp();
		this.listaDetalleVenta = detalleventaImp.findAll();
		return this.listaDetalleVenta;
	}
	
	public void llenarVenta() {
		VentaImp ventaImp = new VentaImp();
		this.listaVenta = ventaImp.findAll();
	}

	public DetalleVentaBean() {
		this.llenarVenta();
	}

	public String crear() {
		System.out.print("Entro a CREAR");
		this.sessionMap.put("detalleventa", detalleventa);
		return "/faces/DetallesVenta/crearDetalleVenta.xhtml?faces-redirect=true";
	}
	
	public String crearDetalleVenta(DetallesVenta detalleventa) {
		DetalleVentaImp detalleventaImp = new DetalleVentaImp();

		VentaImp ventaImp = new VentaImp();
		Venta venta = new Venta();
		venta = ventaImp.findById(idVentaFK);
		detalleventa.setVenta(venta);

		detalleventaImp.CrearDetalleVenta(detalleventa);
		return "/faces/DetallesVenta/listaDetalleVenta.xhtml?faces-redirect=true";
	}
	
	public String editar(int idDetalleVenta) {
		DetalleVentaImp detalleventaImp = new DetalleVentaImp();
		DetallesVenta detalleventa = new DetallesVenta();

		detalleventa = detalleventaImp.findById(idDetalleVenta);
		System.out.print("Entro a EDITAR" + detalleventa);

		this.sessionMap.put("detalleventa", detalleventa);
		return "/faces/DetallesVenta/editarDetalleVenta.xhtml?faces-redirect=true";
	}
	
	public String editarDetalleVenta(DetallesVenta detalleventa) {
		DetalleVentaImp detalleventaImp = new DetalleVentaImp();
		detalleventaImp.ActualizarDetalleVenta(detalleventa);
		return "/faces/DetallesVenta/listaDetalleVenta.xhtml?faces-redirect=true";
	}
	
	public String eliminarDetalleVenta(int idDetalleVenta) {
		DetalleVentaImp detalleventaImp = new DetalleVentaImp();
		detalleventaImp.EliminarDetalleVenta(idDetalleVenta);
		return "/faces/DetallesVenta/listaDetalleVenta.xhtml?faces-redirect=true";
	}
	
	public void exportar () throws IOException {
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		response.setContentType("application/octet-stream");
		DateFormat dateFormtter = new SimpleDateFormat("yyyy-MM-dd");
		String curretDateTime =dateFormtter.format(new Date ());
		String headerKey ="Content-Disposition";
		String headerValue ="attachemnt; filename=ListaDetalleVenta" + curretDateTime + ".xlsx";
		response.setHeader (headerKey,headerValue);
		
		DetalleVentaImp detalleVentaImp = new DetalleVentaImp();
		this.listaDetalleVenta = detalleVentaImp.findAll();
		
		ExportarDetalleVenta eex = new ExportarDetalleVenta(this.listaDetalleVenta);
		eex.export(response);
	}
}

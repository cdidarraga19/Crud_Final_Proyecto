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
import com.sena.disocc.app.facadeImp.ProveedorImp;
import com.sena.disocc.app.modelo.Compra;
import com.sena.disocc.app.modelo.Proveedore;
import com.sena.disocc.app.utilities.ExportarCompras;

@ManagedBean(name = "compraBean")
@RequestScoped
public class CompraBean {
	Compra compra = new Compra();

	List<Compra> listaCompra = new ArrayList<Compra>();
	List<Proveedore> listaProveedor = new ArrayList<Proveedore>();

	private Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

	private int idProveedorFK;

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	public List<Compra> getListaCompra() {
		return listaCompra;
	}

	public void setListaCompra(List<Compra> listaCompra) {
		this.listaCompra = listaCompra;
	}

	public List<Proveedore> getListaProveedor() {
		return listaProveedor;
	}

	public void setListaProveedor(List<Proveedore> listaProveedor) {
		this.listaProveedor = listaProveedor;
	}

	public int getIdProveedorFK() {
		return idProveedorFK;
	}

	public void setIdProveedorFK(int idProveedorFK) {
		this.idProveedorFK = idProveedorFK;
	}

	public List<Compra> findAll() {
		CompraImp compraImp = new CompraImp();
		this.listaCompra = compraImp.findAll();
		return this.listaCompra;
	}
	
	public void llenarProveedor() {
		ProveedorImp proveedorImp = new ProveedorImp();
		this.listaProveedor = proveedorImp.findAll();
	}
	
	public CompraBean() {
		this.llenarProveedor();
	}

	public String crear() {
		System.out.print("Entro a CREAR");
		this.sessionMap.put("compra", compra);
		return "/faces/Compras/crearCompra.xhtml?faces-redirect=true";
	}

	public String crearCompra(Compra compra) {
		CompraImp compraImp = new CompraImp();
		
		ProveedorImp proveedorImp = new ProveedorImp();
		Proveedore proveedore = new Proveedore();
		proveedore = proveedorImp.findById(idProveedorFK);
		compra.setProveedore(proveedore);
		
		compraImp.CrearCompra(compra);
		return "/faces/Compras/listaCompra.xhtml?faces-redirect=true";
	}

	public String editar(int idCompra) {
		CompraImp compraImp = new CompraImp();
		Compra compra = new Compra();

		compra = compraImp.findById(idCompra);
		System.out.print("Entro a EDITAR" + compra);

		this.sessionMap.put("compra", compra);
		return "/faces/Compras/editarCompra.xhtml?faces-redirect=true";
	}

	public String editarCompra(Compra compra) {
		CompraImp compraImp = new CompraImp();
		compraImp.ActualizarCompra(compra);
		return "/faces/Compras/listaCompra.xhtml?faces-redirect=true";
	}

	public String eliminarCompra(int idCompra) {
		CompraImp compraImp = new CompraImp();
		compraImp.EliminarCompra(idCompra);
		return "/faces/Compras/listaCompra.xhtml?faces-redirect=true";
	}
	
	public void exportar () throws IOException {
		
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		response.setContentType("application/octet-stream");
		DateFormat dateFormtter = new SimpleDateFormat("yyyy-MM-dd");
		String curretDateTime =dateFormtter.format(new Date ());
		String headerKey ="Content-Disposition";
		String headerValue ="attachemnt; filename=ListaCompras" + curretDateTime + ".xlsx";
		response.setHeader (headerKey,headerValue);
		
		CompraImp compraImp = new CompraImp();
		this.listaCompra  = compraImp.findAll();
		
		ExportarCompras ex = new ExportarCompras(this.listaCompra);
		ex.export(response);
		
	}
}

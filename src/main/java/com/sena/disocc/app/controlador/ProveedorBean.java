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

import com.sena.disocc.app.facadeImp.ProveedorImp;
import com.sena.disocc.app.modelo.Proveedore;
import com.sena.disocc.app.utilities.ExportarProveedor;

@ManagedBean(name = "proveedorBean")
@RequestScoped
public class ProveedorBean {
	Proveedore proveedor = new Proveedore();
	List<Proveedore> listaProveedor = new ArrayList<Proveedore>();
	private Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

	public Proveedore getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedore proveedor) {
		this.proveedor = proveedor;
	}

	public List<Proveedore> getListaProveedor() {
		return listaProveedor;
	}

	public void setListaProveedor(List<Proveedore> listaProveedor) {
		this.listaProveedor = listaProveedor;
	}

	public List<Proveedore> findAll() {
		ProveedorImp provedorImp = new ProveedorImp();
		this.listaProveedor = provedorImp.findAll();
		return this.listaProveedor;
	}

	public String crear() {
		System.out.print("Entro a CREAR");
		this.sessionMap.put("proveedor", proveedor);
		return "/faces/Proveedores/crearProveedor.xhtml?faces-redirect=true";
	}

	public String crearProveedor(Proveedore proveedor) {
		ProveedorImp proveedorImp = new ProveedorImp();
		proveedorImp.CrearProveedore(proveedor);
		return "/faces/Proveedores/listaProveedor.xhtml?faces-redirect=true";
	}

	public String editar(int idProveedor) {
		ProveedorImp proveedorImp = new ProveedorImp();
		Proveedore proveedor = new Proveedore();

		proveedor = proveedorImp.findById(idProveedor);
		System.out.print("Entro a EDITAR" + proveedor);

		this.sessionMap.put("proveedor", proveedor);
		return "/faces/Proveedores/editarProveedor.xhtml?faces-redirect=true";
	}

	public String editarProveedor(Proveedore proveedor) {
		ProveedorImp proveedorImp = new ProveedorImp();
		proveedorImp.ActualizarProveedore(proveedor);
		return "/faces/Proveedores/listaProveedor.xhtml?faces-redirect=true";
	}

	public String eliminarProveedor(int idProveedor) {
		ProveedorImp proveedorImp = new ProveedorImp();
		proveedorImp.EliminarProveedore(idProveedor);
		return "/faces/Proveedores/listaProveedor.xhtml?faces-redirect=true";
	}
	public void exportar () throws IOException {
		
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		response.setContentType("application/octet-stream");
		DateFormat dateFormtter = new SimpleDateFormat("yyyy-MM-dd");
		String curretDateTime =dateFormtter.format(new Date ());
		String headerKey ="Content-Disposition";
		String headerValue ="attachemnt; filename=ListaProveedores" + curretDateTime + ".xlsx";
		response.setHeader (headerKey,headerValue);
		
		ProveedorImp proveedorImp = new ProveedorImp();
		this.listaProveedor = proveedorImp.findAll();
		
		ExportarProveedor ex = new ExportarProveedor(this.listaProveedor);
		ex.export(response);
		
	}
}

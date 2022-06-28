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
import com.sena.disocc.app.facadeImp.MarcaImp;
import com.sena.disocc.app.facadeImp.ProductoImp;
import com.sena.disocc.app.modelo.CategoriasProducto;
import com.sena.disocc.app.modelo.Marca;
import com.sena.disocc.app.modelo.Producto;
import com.sena.disocc.app.utilities.ExportarProducto;

@ManagedBean (name="productoBean")
@RequestScoped
public class ProductoBean {
	
	Producto producto = new Producto();
	List<Producto> listaProducto = new ArrayList<Producto>();
	List <Marca> listaMarca = new ArrayList<Marca>();
	List <CategoriasProducto> listaCategoriaP = new ArrayList<CategoriasProducto>();
	
	private Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
	
	private int idMarcaFK;
	private int idCategoriaFK;
	
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public List<Producto> getListaProducto() {
		return listaProducto;
	}
	public void setListaProducto(List<Producto> listaProducto) {
		this.listaProducto = listaProducto;
	}
	public List<Marca> getListaMarca() {
		return listaMarca;
	}
	public void setListaMarca(List<Marca> listaMarca) {
		this.listaMarca = listaMarca;
	}
	public List<CategoriasProducto> getListaCategoriaP() {
		return listaCategoriaP;
	}
	public void setListaCategoriaP(List<CategoriasProducto> listaCategoriaP) {
		this.listaCategoriaP = listaCategoriaP;
	}
	public int getIdMarcaFK() {
		return idMarcaFK;
	}
	public void setIdMarcaFK(int idMarcaFK) {
		this.idMarcaFK = idMarcaFK;
	}
	public int getIdCategoriaFK() {
		return idCategoriaFK;
	}
	public void setIdCategoriaFK(int idCategoriaFK) {
		this.idCategoriaFK = idCategoriaFK;
	}
	
	
	public void llenarMarca() {
		MarcaImp marcaImp = new MarcaImp();
		this.listaMarca = marcaImp.findAll();
	}
	
	public void llenarCategoria() {
		CategoriaPImp categoriaPImp = new CategoriaPImp();
		this.listaCategoriaP = categoriaPImp.findAll();
	}
	
	public ProductoBean() {
		this.llenarCategoria();
		this.llenarMarca();
	}
	
	public List<Producto> findAll(){
		
		ProductoImp productoImp = new ProductoImp();
		this.listaProducto = productoImp.finAll();
		return listaProducto;
	}
	
	public String crearProducto (Producto producto) {
		ProductoImp productoImp = new ProductoImp();
		
		MarcaImp marcaImp = new MarcaImp();
		Marca marca = new Marca();
		marca = marcaImp.finById(idMarcaFK);
		producto.setMarca(marca);
		
		CategoriaPImp categoriaPImp = new CategoriaPImp();
		CategoriasProducto categoriaP = new CategoriasProducto();
		categoriaP = categoriaPImp.findById(idCategoriaFK);
		producto.setCategoriasProducto(categoriaP);
		
		productoImp.crearProducto(producto);
		return "/faces/Producto/listaProducto.xhtml?faces-redirect=true";
	}
	
	public String crear() {
		System.out.print("Entro a CREAR");
		this.sessionMap.put("producto", producto);
		return "/faces/Producto/crearProducto.xhtml?faces-redirect=true";

	}
	
	public String actualizar (int idProducto) {
		ProductoImp productoImp = new ProductoImp();
		Producto producto = new Producto();
		
		producto = productoImp.findById(idProducto);
		System.out.print("Entro a EDITAR "+producto);
		
		this.sessionMap.put("producto", producto);
		return "/faces/Producto/editarProducto.xhtml?faces-redirect=true";	

	}
	
	public String actualizarProducto (Producto producto) {
		
		ProductoImp productoImp = new ProductoImp();
		productoImp.actualizarProducto(producto);
		return "/faces/Producto/listaProducto.xhtml?faces-redirect=true";

	}
	
	public String eliminarProducto (int idProducto) {
		
		ProductoImp productoImp = new ProductoImp();
		productoImp.eliminarProducto(idProducto);
		System.out.print("ELIMINO");
		return "/faces/Producto/listaProducto.xhtml?faces-redirect=true";
		
	}
	
	public void exportar () throws IOException {
		
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		response.setContentType("application/octet-stream");
		DateFormat dateFormtter = new SimpleDateFormat("yyyy-MM-dd");
		String curretDateTime =dateFormtter.format(new Date ());
		String headerKey ="Content-Disposition";
		String headerValue ="attachemnt; filename=ListaProductos" + curretDateTime + ".xlsx";
		response.setHeader (headerKey,headerValue);
		
		ProductoImp productoImp = new ProductoImp();
		this.listaProducto = productoImp.finAll();
		
		if(idMarcaFK!=0 & idCategoriaFK!=0) {
			this.listaProducto = productoImp.exportPorMarcaCatego(idMarcaFK,idCategoriaFK);
		}		
		else if (idMarcaFK!=0){
			this.listaProducto = productoImp.exportPorMarca(idMarcaFK);			
		}
		else if (idCategoriaFK!=0){
			this.listaProducto = productoImp.exportPorCatego(idCategoriaFK);
		}
		else {
			this.listaProducto = productoImp.finAll();
		}
		ExportarProducto ex = new ExportarProducto(this.listaProducto);
		ex.export(response);
	}
	
	
}

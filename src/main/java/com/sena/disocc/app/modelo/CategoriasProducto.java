package com.sena.disocc.app.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the categorias_productos database table.
 * 
 */
@Entity
@Table(name="categorias_productos")
@NamedQuery(name="CategoriasProducto.findAll", query="SELECT c FROM CategoriasProducto c")
public class CategoriasProducto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_CATEGORIA")
	private int idCategoria;

	@Column(name="NOMBRE_CATEGORIA")
	private String nombreCategoria;

	//bi-directional many-to-one association to Producto
	@OneToMany(mappedBy="categoriasProducto")
	private List<Producto> productos;

	public CategoriasProducto() {
	}

	public int getIdCategoria() {
		return this.idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getNombreCategoria() {
		return this.nombreCategoria;
	}

	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}

	public List<Producto> getProductos() {
		return this.productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public Producto addProducto(Producto producto) {
		getProductos().add(producto);
		producto.setCategoriasProducto(this);

		return producto;
	}

	public Producto removeProducto(Producto producto) {
		getProductos().remove(producto);
		producto.setCategoriasProducto(null);

		return producto;
	}

}
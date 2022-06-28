package com.sena.disocc.app.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the detalles_compras database table.
 * 
 */
@Entity
@Table(name="detalles_compras")
@NamedQuery(name="DetallesCompra.findAll", query="SELECT d FROM DetallesCompra d")
public class DetallesCompra implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private int cantidad;

	private String fecha;

	private int iva;

	@Column(name="PRECIO_PRODUCTO")
	private BigInteger precioProducto;

	private BigInteger subtotal;

	private BigInteger total;

	//bi-directional many-to-one association to Compra
	@ManyToOne
	@JoinColumn(name="FK_ID_COMPRA")
	private Compra compra;

	//bi-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name="FK_ID_PRODUCTO")
	private Producto producto;

	public DetallesCompra() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getFecha() {
		return this.fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public int getIva() {
		return this.iva;
	}

	public void setIva(int iva) {
		this.iva = iva;
	}

	public BigInteger getPrecioProducto() {
		return this.precioProducto;
	}

	public void setPrecioProducto(BigInteger precioProducto) {
		this.precioProducto = precioProducto;
	}

	public BigInteger getSubtotal() {
		return this.subtotal;
	}

	public void setSubtotal(BigInteger subtotal) {
		this.subtotal = subtotal;
	}

	public BigInteger getTotal() {
		return this.total;
	}

	public void setTotal(BigInteger total) {
		this.total = total;
	}

	public Compra getCompra() {
		return this.compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

}
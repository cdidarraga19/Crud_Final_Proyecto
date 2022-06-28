package com.sena.disocc.app.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the inventario_entradas database table.
 * 
 */
@Entity
@Table(name="inventario_entradas")
@NamedQuery(name="InventarioEntrada.findAll", query="SELECT i FROM InventarioEntrada i")
public class InventarioEntrada implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private int cantidad;

	private BigInteger total;

	//bi-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name="FK_ID_PRODUCTO")
	private Producto producto;

	//bi-directional many-to-one association to Inventario
	@ManyToOne
	@JoinColumn(name="FK_ID_INVENTARIO")
	private Inventario inventario;

	public InventarioEntrada() {
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

	public BigInteger getTotal() {
		return this.total;
	}

	public void setTotal(BigInteger total) {
		this.total = total;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Inventario getInventario() {
		return this.inventario;
	}

	public void setInventario(Inventario inventario) {
		this.inventario = inventario;
	}

}
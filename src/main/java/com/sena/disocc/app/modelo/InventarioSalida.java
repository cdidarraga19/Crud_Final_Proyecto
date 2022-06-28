package com.sena.disocc.app.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the inventario_salidas database table.
 * 
 */
@Entity
@Table(name="inventario_salidas")
@NamedQuery(name="InventarioSalida.findAll", query="SELECT i FROM InventarioSalida i")
public class InventarioSalida implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private int cantidad;

	private BigInteger total;

	//bi-directional many-to-one association to DetallesVenta
	@ManyToOne
	@JoinColumn(name="FK_ID_DETALLE_VENTA")
	private DetallesVenta detallesVenta;

	//bi-directional many-to-one association to Inventario
	@ManyToOne
	@JoinColumn(name="FK_ID_INVENTARIO")
	private Inventario inventario;

	public InventarioSalida() {
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

	public DetallesVenta getDetallesVenta() {
		return this.detallesVenta;
	}

	public void setDetallesVenta(DetallesVenta detallesVenta) {
		this.detallesVenta = detallesVenta;
	}

	public Inventario getInventario() {
		return this.inventario;
	}

	public void setInventario(Inventario inventario) {
		this.inventario = inventario;
	}

}
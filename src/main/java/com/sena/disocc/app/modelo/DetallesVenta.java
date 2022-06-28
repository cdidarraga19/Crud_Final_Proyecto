package com.sena.disocc.app.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.math.BigInteger;
import java.util.List;


/**
 * The persistent class for the detalles_ventas database table.
 * 
 */
@Entity
@Table(name="detalles_ventas")
@NamedQuery(name="DetallesVenta.findAll", query="SELECT d FROM DetallesVenta d")
public class DetallesVenta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_DETALLE_VENTA")
	private int idDetalleVenta;

	private int cantidad;

	private String fecha;

	private int iva;

	@Column(name="PRECIO_PRODUCTO")
	private BigInteger precioProducto;

	private BigInteger subtotal;

	private BigInteger total;

	//bi-directional many-to-one association to Venta
	@ManyToOne
	@JoinColumn(name="FK_ID_VENTA")
	private Venta venta;

	//bi-directional many-to-one association to InventarioSalida
	@OneToMany(mappedBy="detallesVenta")
	private List<InventarioSalida> inventarioSalidas;

	public DetallesVenta() {
	}

	public int getIdDetalleVenta() {
		return this.idDetalleVenta;
	}

	public void setIdDetalleVenta(int idDetalleVenta) {
		this.idDetalleVenta = idDetalleVenta;
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

	public Venta getVenta() {
		return this.venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}

	public List<InventarioSalida> getInventarioSalidas() {
		return this.inventarioSalidas;
	}

	public void setInventarioSalidas(List<InventarioSalida> inventarioSalidas) {
		this.inventarioSalidas = inventarioSalidas;
	}

	public InventarioSalida addInventarioSalida(InventarioSalida inventarioSalida) {
		getInventarioSalidas().add(inventarioSalida);
		inventarioSalida.setDetallesVenta(this);

		return inventarioSalida;
	}

	public InventarioSalida removeInventarioSalida(InventarioSalida inventarioSalida) {
		getInventarioSalidas().remove(inventarioSalida);
		inventarioSalida.setDetallesVenta(null);

		return inventarioSalida;
	}

}
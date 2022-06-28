package com.sena.disocc.app.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;


/**
 * The persistent class for the ventas database table.
 * 
 */
@Entity
@Table(name="ventas")
@NamedQuery(name="Venta.findAll", query="SELECT v FROM Venta v")
public class Venta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_VENTA")
	private int idVenta;

	private String fecha;

	@Column(name="PAGO_REALIZADO")
	private String pagoRealizado;

	private BigInteger total;

	//bi-directional many-to-one association to DetallesVenta
	@OneToMany(mappedBy="venta")
	private List<DetallesVenta> detallesVentas;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="FK_ID_USUARIO")
	private Usuario usuario;

	//bi-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name="FK_ID_PRODUCTO")
	private Producto producto;

	public Venta() {
	}

	public int getIdVenta() {
		return this.idVenta;
	}

	public void setIdVenta(int idVenta) {
		this.idVenta = idVenta;
	}

	public String getFecha() {
		return this.fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getPagoRealizado() {
		return this.pagoRealizado;
	}

	public void setPagoRealizado(String pagoRealizado) {
		this.pagoRealizado = pagoRealizado;
	}

	public BigInteger getTotal() {
		return this.total;
	}

	public void setTotal(BigInteger total) {
		this.total = total;
	}

	public List<DetallesVenta> getDetallesVentas() {
		return this.detallesVentas;
	}

	public void setDetallesVentas(List<DetallesVenta> detallesVentas) {
		this.detallesVentas = detallesVentas;
	}

	public DetallesVenta addDetallesVenta(DetallesVenta detallesVenta) {
		getDetallesVentas().add(detallesVenta);
		detallesVenta.setVenta(this);

		return detallesVenta;
	}

	public DetallesVenta removeDetallesVenta(DetallesVenta detallesVenta) {
		getDetallesVentas().remove(detallesVenta);
		detallesVenta.setVenta(null);

		return detallesVenta;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

}
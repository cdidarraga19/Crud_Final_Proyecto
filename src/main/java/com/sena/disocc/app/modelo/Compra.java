package com.sena.disocc.app.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;


/**
 * The persistent class for the compras database table.
 * 
 */
@Entity
@Table(name="compras")
@NamedQuery(name="Compra.findAll", query="SELECT c FROM Compra c")
public class Compra implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_COMPRA")
	private int idCompra;

	private String fecha;

	@Column(name="PAGO_REALIZADO")
	private String pagoRealizado;

	private BigInteger total;

	//bi-directional many-to-one association to Proveedore
	@ManyToOne
	@JoinColumn(name="FK_ID_PROVEEDOR")
	private Proveedore proveedore;

	//bi-directional many-to-one association to DetallesCompra
	@OneToMany(mappedBy="compra")
	private List<DetallesCompra> detallesCompras;

	public Compra() {
	}

	public int getIdCompra() {
		return this.idCompra;
	}

	public void setIdCompra(int idCompra) {
		this.idCompra = idCompra;
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

	public Proveedore getProveedore() {
		return this.proveedore;
	}

	public void setProveedore(Proveedore proveedore) {
		this.proveedore = proveedore;
	}

	public List<DetallesCompra> getDetallesCompras() {
		return this.detallesCompras;
	}

	public void setDetallesCompras(List<DetallesCompra> detallesCompras) {
		this.detallesCompras = detallesCompras;
	}

	public DetallesCompra addDetallesCompra(DetallesCompra detallesCompra) {
		getDetallesCompras().add(detallesCompra);
		detallesCompra.setCompra(this);

		return detallesCompra;
	}

	public DetallesCompra removeDetallesCompra(DetallesCompra detallesCompra) {
		getDetallesCompras().remove(detallesCompra);
		detallesCompra.setCompra(null);

		return detallesCompra;
	}

}
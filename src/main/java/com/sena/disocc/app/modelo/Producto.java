package com.sena.disocc.app.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;


/**
 * The persistent class for the productos database table.
 * 
 */
@Entity
@Table(name="productos")
@NamedQuery(name="Producto.findAll", query="SELECT p FROM Producto p")
public class Producto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_PRODUCTO")
	private int idProducto;

	private String descripcion;

	@Column(name="FECHA_VENCIMIENTO")
	private String fechaVencimiento;

	@Lob
	private byte[] foto;

	private String nombre;

	@Column(name="PRECIO_UNIDAD")
	private BigInteger precioUnidad;

	@Column(name="STOCK_DISPONIBLE")
	private int stockDisponible;

	//bi-directional many-to-one association to DetallesCompra
	@OneToMany(mappedBy="producto")
	private List<DetallesCompra> detallesCompras;

	//bi-directional many-to-one association to InventarioEntrada
	@OneToMany(mappedBy="producto")
	private List<InventarioEntrada> inventarioEntradas;

	//bi-directional many-to-one association to Marca
	@ManyToOne
	@JoinColumn(name="FK_ID_MARCA")
	private Marca marca;

	//bi-directional many-to-one association to CategoriasProducto
	@ManyToOne
	@JoinColumn(name="FK_ID_CATEGORIA_PRODUCTO")
	private CategoriasProducto categoriasProducto;

	//bi-directional many-to-many association to Proveedore
	@ManyToMany
	@JoinTable(
		name="proveedor_producto"
		, joinColumns={
			@JoinColumn(name="FK_ID_PRODUCTO")
			}
		, inverseJoinColumns={
			@JoinColumn(name="FK_ID_PROVEEDOR")
			}
		)
	private List<Proveedore> proveedores;

	//bi-directional many-to-one association to Venta
	@OneToMany(mappedBy="producto")
	private List<Venta> ventas;

	public Producto() {
	}

	public int getIdProducto() {
		return this.idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getFechaVencimiento() {
		return this.fechaVencimiento;
	}

	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public byte[] getFoto() {
		return this.foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public BigInteger getPrecioUnidad() {
		return this.precioUnidad;
	}

	public void setPrecioUnidad(BigInteger precioUnidad) {
		this.precioUnidad = precioUnidad;
	}

	public int getStockDisponible() {
		return this.stockDisponible;
	}

	public void setStockDisponible(int stockDisponible) {
		this.stockDisponible = stockDisponible;
	}

	public List<DetallesCompra> getDetallesCompras() {
		return this.detallesCompras;
	}

	public void setDetallesCompras(List<DetallesCompra> detallesCompras) {
		this.detallesCompras = detallesCompras;
	}

	public DetallesCompra addDetallesCompra(DetallesCompra detallesCompra) {
		getDetallesCompras().add(detallesCompra);
		detallesCompra.setProducto(this);

		return detallesCompra;
	}

	public DetallesCompra removeDetallesCompra(DetallesCompra detallesCompra) {
		getDetallesCompras().remove(detallesCompra);
		detallesCompra.setProducto(null);

		return detallesCompra;
	}

	public List<InventarioEntrada> getInventarioEntradas() {
		return this.inventarioEntradas;
	}

	public void setInventarioEntradas(List<InventarioEntrada> inventarioEntradas) {
		this.inventarioEntradas = inventarioEntradas;
	}

	public InventarioEntrada addInventarioEntrada(InventarioEntrada inventarioEntrada) {
		getInventarioEntradas().add(inventarioEntrada);
		inventarioEntrada.setProducto(this);

		return inventarioEntrada;
	}

	public InventarioEntrada removeInventarioEntrada(InventarioEntrada inventarioEntrada) {
		getInventarioEntradas().remove(inventarioEntrada);
		inventarioEntrada.setProducto(null);

		return inventarioEntrada;
	}

	public Marca getMarca() {
		return this.marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public CategoriasProducto getCategoriasProducto() {
		return this.categoriasProducto;
	}

	public void setCategoriasProducto(CategoriasProducto categoriasProducto) {
		this.categoriasProducto = categoriasProducto;
	}

	public List<Proveedore> getProveedores() {
		return this.proveedores;
	}

	public void setProveedores(List<Proveedore> proveedores) {
		this.proveedores = proveedores;
	}

	public List<Venta> getVentas() {
		return this.ventas;
	}

	public void setVentas(List<Venta> ventas) {
		this.ventas = ventas;
	}

	public Venta addVenta(Venta venta) {
		getVentas().add(venta);
		venta.setProducto(this);

		return venta;
	}

	public Venta removeVenta(Venta venta) {
		getVentas().remove(venta);
		venta.setProducto(null);

		return venta;
	}

}
package com.sena.disocc.app.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the inventarios database table.
 * 
 */
@Entity
@Table(name="inventarios")
@NamedQuery(name="Inventario.findAll", query="SELECT i FROM Inventario i")
public class Inventario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_INVENTARIO")
	private int idInventario;

	private String nombre;

	//bi-directional many-to-one association to InventarioEntrada
	@OneToMany(mappedBy="inventario")
	private List<InventarioEntrada> inventarioEntradas;

	//bi-directional many-to-one association to InventarioSalida
	@OneToMany(mappedBy="inventario")
	private List<InventarioSalida> inventarioSalidas;

	public Inventario() {
	}

	public int getIdInventario() {
		return this.idInventario;
	}

	public void setIdInventario(int idInventario) {
		this.idInventario = idInventario;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<InventarioEntrada> getInventarioEntradas() {
		return this.inventarioEntradas;
	}

	public void setInventarioEntradas(List<InventarioEntrada> inventarioEntradas) {
		this.inventarioEntradas = inventarioEntradas;
	}

	public InventarioEntrada addInventarioEntrada(InventarioEntrada inventarioEntrada) {
		getInventarioEntradas().add(inventarioEntrada);
		inventarioEntrada.setInventario(this);

		return inventarioEntrada;
	}

	public InventarioEntrada removeInventarioEntrada(InventarioEntrada inventarioEntrada) {
		getInventarioEntradas().remove(inventarioEntrada);
		inventarioEntrada.setInventario(null);

		return inventarioEntrada;
	}

	public List<InventarioSalida> getInventarioSalidas() {
		return this.inventarioSalidas;
	}

	public void setInventarioSalidas(List<InventarioSalida> inventarioSalidas) {
		this.inventarioSalidas = inventarioSalidas;
	}

	public InventarioSalida addInventarioSalida(InventarioSalida inventarioSalida) {
		getInventarioSalidas().add(inventarioSalida);
		inventarioSalida.setInventario(this);

		return inventarioSalida;
	}

	public InventarioSalida removeInventarioSalida(InventarioSalida inventarioSalida) {
		getInventarioSalidas().remove(inventarioSalida);
		inventarioSalida.setInventario(null);

		return inventarioSalida;
	}

}
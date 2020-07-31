package com.laura.spring.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;


@Entity
public class Producto {

	@Id
	@GeneratedValue
	private long id;
	
	private String nombre;
	private double precio;
	private String imagen;
	
	@ManyToOne
	private Usuario vendedor;
	
	public Producto() {
		
	}
	
	public Producto(@NotNull String nombre, @NotNull double precio, String imagen, Usuario vendedor) {
		super();
		this.nombre = nombre;
		this.precio = precio;
		this.imagen = imagen;
		this.vendedor = vendedor;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public Usuario getVendedor() {
		return vendedor;
	}
	public void setVendedor(Usuario vendedor) {
		this.vendedor = vendedor;
	}

}

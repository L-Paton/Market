package com.laura.spring.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;

@Entity
public class Compra {

	@Id
	@GeneratedValue
	private long id;
	
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaCompra;
	
	@ManyToOne
	private Usuario comprador;
	
	@ManyToOne
	private Producto producto;
	
	private boolean finalizada;
	
	public Compra() {
		
	}

	public Compra(Usuario comprador, Producto producto, boolean finalizada) {
		super();
		this.comprador = comprador;
		this.producto = producto;
		this.finalizada = finalizada;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	

	public Date getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	public Usuario getComprador() {
		return comprador;
	}

	public void setComprador(Usuario comprador) {
		this.comprador = comprador;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public boolean isFinalizada() {
		return finalizada;
	}

	public void setFinalizada(boolean finalizada) {
		this.finalizada = finalizada;
	}
	
}

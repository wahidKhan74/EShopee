package com.simplilearn.model;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Cart")
public class Cart {
	
	@Id
	@Column(name = "cart_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "cart_total")
	private double total;

	@ManyToMany(targetEntity = Product.class, cascade = { CascadeType.ALL })
	@JoinTable(name = "Cart_Product", 
				joinColumns = { @JoinColumn(name = "cart_id") }, 
				inverseJoinColumns = { @JoinColumn(name = "product_id") })
	private Set<Product> products;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public Cart(long id, double total, Set<Product> products) {
		super();
		this.id = id;
		this.total = total;
		this.products = products;
	}
	
	public Cart() { }
	
}

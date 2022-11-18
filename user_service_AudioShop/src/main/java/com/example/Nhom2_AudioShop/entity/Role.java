package com.example.Nhom2_AudioShop.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Role {
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	
	public Role(long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Role() {
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}

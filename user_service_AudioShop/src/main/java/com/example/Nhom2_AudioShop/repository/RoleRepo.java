package com.example.Nhom2_AudioShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Nhom2_AudioShop.entity.Role;


public interface RoleRepo extends JpaRepository<Role, Long> {
	public Role findByName(String name);
}

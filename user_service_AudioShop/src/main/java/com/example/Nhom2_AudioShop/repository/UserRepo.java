package com.example.Nhom2_AudioShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Nhom2_AudioShop.entity.User;


public interface UserRepo extends JpaRepository<User, Long> {
	public User findByUsername(String username);
}

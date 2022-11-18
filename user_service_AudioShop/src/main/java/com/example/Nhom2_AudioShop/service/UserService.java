package com.example.Nhom2_AudioShop.service;

import java.util.List;

import com.example.Nhom2_AudioShop.entity.Role;
import com.example.Nhom2_AudioShop.entity.User;


public interface UserService {
	public User addUser(User user);
	public Role addRole(Role role);
	public void addRoleToUser(String username, String roleName);
	public User getUser(String username);
	public User getUserById(long id);
	public List<User> getUsers();
}

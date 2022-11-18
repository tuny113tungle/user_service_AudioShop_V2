package com.example.Nhom2_AudioShop;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.Nhom2_AudioShop.entity.Role;
import com.example.Nhom2_AudioShop.entity.User;
import com.example.Nhom2_AudioShop.service.UserService;

@SpringBootApplication
public class Nhom2AudioShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(Nhom2AudioShopApplication.class, args);
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
//	@Bean
//	CommandLineRunner run(UserService userService) {
//		return args -> {
//			userService.addRole(new Role(0, "ROLE_CUSTOMER"));
//			userService.addRole(new Role(0, "ROLE_STAFF"));
//			userService.addRole(new Role(0, "ROLE_MANAGER"));
//
//			userService.addUser(new User(0, "Trần Thanh Hùng", "hung", "1234", "112 Lê Lợi", "hung@gmail.com", "0415151514", null));
//			userService.addUser(new User(0, "Lê Hoàng Minh", "minh", "1234", "41 Nguyễn Thái Bình", "minh@gmail.com", "0915145416", null));
//			userService.addUser(new User(0, "Nguyễn Thanh Lam", "lam", "1234", "51 Phạm Văn Đồng", "lam@gmail.com", "0515151514", null));
//			userService.addUser(new User(0, "Cao Thanh Hà", "ha", "1234", "61 Lê Văn Sỹ", "ha@gmail.com", "0115151514", null));
//			
//			userService.addRoleToUser("hung", "ROLE_CUSTOMER");
//			userService.addRoleToUser("minh", "ROLE_STAFF");
//			userService.addRoleToUser("lam", "ROLE_MANAGER");
//			userService.addRoleToUser("ha", "ROLE_MANAGER");
//		};
//	}


}

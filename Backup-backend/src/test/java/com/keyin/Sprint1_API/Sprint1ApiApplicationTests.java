package com.keyin.Sprint1_API;

import com.keyin.Sprint1_API.User.User;
import com.keyin.Sprint1_API.User.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class Sprint1ApiApplicationTests {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@BeforeEach
	void setup() {
		userRepository.deleteAll();
		User testUser = new User();
		testUser.setUsername("testuser");
		testUser.setPassword(passwordEncoder.encode("password"));
		testUser.setEmail("testuser@example.com");
		userRepository.save(testUser);
	}

	@Test
	void contextLoads() {
	}
}

package tawjih;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import tawjih.enums.Role;
import tawjih.model.Admin;
import tawjih.repository.AdminRepository;

@SpringBootApplication
public class TawjihApplication {

	public static void main(String[] args) {
		SpringApplication.run(TawjihApplication.class, args);
	}

	@Autowired
	AdminRepository adminRepository;

	@Bean
	public CommandLineRunner startup() {

		return args -> {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			if (adminRepository.count() == 0) {
				Admin admin = new Admin();
				admin.setFullname("admin");
				admin.setEmail("zouhayr@gmail.com");
				admin.setPassword(passwordEncoder.encode("admin"));
				admin.setRole(Role.ADMIN);

				adminRepository.save(admin);
				System.out.println("Admin  created avec success");
			} else {
				System.out.println("Admin  already exists.");
			}

		};

	}

}

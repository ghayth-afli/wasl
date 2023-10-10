package com.mzo.wasl;

import com.mzo.wasl.request.RegisterRequest;
import com.mzo.wasl.service.AuthenticationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import static com.mzo.wasl.model.Role.ADMIN;
import static com.mzo.wasl.model.Role.REGULAR;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class WaslApplication {

	public static void main(String[] args) {
		SpringApplication.run(WaslApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(
			AuthenticationService service
	) {
		return args -> {
			var admin = RegisterRequest.builder()
					.username("Admin")
					.email("admin@mail.com")
					.password("password")
					.role(ADMIN)
					.build();
			System.out.println("Admin token: " + service.register(admin).getAccessToken());

			var regular = RegisterRequest.builder()
					.username("Regular")
					.email("regular@mail.com")
					.password("password")
					.role(REGULAR)
					.build();
			System.out.println("Manager token: " + service.register(regular).getAccessToken());

		};
	}
}

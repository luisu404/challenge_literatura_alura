package com.luisu404.challengeliteratura;

import com.luisu404.challengeliteratura.principal.Principal;
import com.luisu404.challengeliteratura.service.ConsumoApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChallengeliteraluraApplication implements CommandLineRunner {

    @Autowired
    private ConsumoApiService apiService;

	public static void main(String[] args) {
		SpringApplication.run(ChallengeliteraluraApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        Principal principal = new Principal(apiService);
        principal.mostrarMenu();

    }
}

package com.luisu404.challengeliteratura;

import com.luisu404.challengeliteratura.principal.Principal;
import com.luisu404.challengeliteratura.service.ConsumoApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChallengeliteraluraApplication implements CommandLineRunner {

    private final Principal principal;

    public ChallengeliteraluraApplication(Principal principal) {
        this.principal = principal;
    }

	public static void main(String[] args) {
		SpringApplication.run(ChallengeliteraluraApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        principal.mostrarMenu();

    }
}

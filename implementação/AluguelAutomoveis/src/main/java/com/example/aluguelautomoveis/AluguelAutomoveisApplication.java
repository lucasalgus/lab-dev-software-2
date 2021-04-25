package com.example.aluguelautomoveis;

import com.example.aluguelautomoveis.DAO.UsuarioDAO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(scanBasePackages = {"com.example"})
@RestController
public class AluguelAutomoveisApplication {

    public static void main(String[] args) {
        SpringApplication.run(AluguelAutomoveisApplication.class, args);
    }

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello World";
    }
}

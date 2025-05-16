package com.daw.cardmarket;

import com.daw.cardmarket.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CardMarketApplication implements CommandLineRunner {

    @Autowired
    private AdminService adminService;

    public static void main(String[] args) {
        SpringApplication.run(CardMarketApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        adminService.createDefaultAdmin();
    }
}

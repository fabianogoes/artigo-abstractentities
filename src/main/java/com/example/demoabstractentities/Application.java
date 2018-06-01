package com.example.demoabstractentities;

import com.example.demoabstractentities.model.Customer;
import com.example.demoabstractentities.model.Product;
import com.example.demoabstractentities.repository.CustomerRepository;
import com.example.demoabstractentities.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

import java.util.UUID;

@EnableMongoAuditing
@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        Customer customer = Customer.builder()
                .name(UUID.randomUUID().toString())
                .documentNumber(123456)
                .build();
        this.customerRepository.save(customer);

        Product product = Product.builder().description(UUID.randomUUID().toString()).ean(654321).build();
        this.productRepository.save(product);

        System.out.println("***********************************************************************");
        System.out.println("* Customer");
        System.out.println("***********************************************************************");
        this.customerRepository.findAll().forEach(System.out::println);

        System.out.println("***********************************************************************");
        System.out.println("* Product");
        System.out.println("***********************************************************************");
        this.productRepository.findAll().forEach(System.out::println);

    }

}

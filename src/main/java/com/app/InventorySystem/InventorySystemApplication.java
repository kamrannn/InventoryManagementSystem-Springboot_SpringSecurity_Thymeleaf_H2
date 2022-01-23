package com.app.InventorySystem;

import com.app.InventorySystem.model.User;
import com.app.InventorySystem.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class InventorySystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventorySystemApplication.class, args);
    }

    /*
    Creating 2 users whenever the application will run, these are just to test the website
    functionalities.
     */
    @Bean
    CommandLineRunner run(UserRepository userRepository, PasswordEncoder bCryptPasswordEncoder) {
        return args -> {
            //This is an admin
            User admin = new User();
            admin.setFullName("Admin Name");
            admin.setUsername("admin"); //This admin will be created everytime you'll run the application
            admin.setPassword(bCryptPasswordEncoder.encode("admin"));
            admin.setEmail("admin@gmail.com");
            admin.setRole("ROLE_ADMIN");
            userRepository.save(admin);

            // This is a general user
            User user = new User();
            user.setFullName("General User");
            user.setUsername("user"); //This admin will be created everytime you'll run the application
            user.setPassword(bCryptPasswordEncoder.encode("user"));
            user.setEmail("user@gmail.com");
            user.setRole("ROLE_USER");
            userRepository.save(user);
        };
    }
}

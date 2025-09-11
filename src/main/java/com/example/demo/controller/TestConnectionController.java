package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

@RestController
public class TestConnectionController {
    
    @Autowired
    private DataSource dataSource;
    
    @Autowired
    private UserRepository userRepository;
    
    @GetMapping("/test-connection")
    public String testConnection() {
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT 'Conexion exitosa' FROM DUAL");
            if (resultSet.next()) {
                return "Oracle Cloud conectado: " + resultSet.getString(1);
            }
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
        return "No se pudo establecer conexion";
    }
    
    @GetMapping("/create-test-user")
    public String createTestUser() {
        try {
            User user = new User("testuser", "test@example.com");
            userRepository.save(user);
            return "Usuario creado con ID: " + user.getId();
        } catch (Exception e) {
            return "Error creando usuario: " + e.getMessage();
        }
    }
    
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    @GetMapping("/user-count")
    public String getUserCount() {
        try {
            long count = userRepository.count();
            return "Total de usuarios en la tabla: " + count;
        } catch (Exception e) {
            return "Error contando usuarios: " + e.getMessage();
        }
    }
}
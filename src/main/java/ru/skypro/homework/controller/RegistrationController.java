package ru.skypro.homework.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.Register;
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/register")
public class RegistrationController {

    @PostMapping
    public ResponseEntity<Void> register(@RequestBody Register register) {
        // Реализация регистрации пользователя
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}

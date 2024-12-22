package ru.skypro.homework.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.service.UserService;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/users")
public class UserController {

    @PostMapping("/set_password")
    public ResponseEntity<Void> setPassword(@RequestBody NewPassword newPassword) {
        // Реализация обновления пароля
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    public ResponseEntity<User> getUser() {
        // Реализация получения информации об авторизованном пользователе
        return ResponseEntity.ok(UserService.getCurrentUser());
    }

    @PatchMapping("/me")
    public ResponseEntity<UpdateUser> updateUser(@RequestBody UpdateUser updateUser) {
        // Реализация обновления информации об пользователе
        return ResponseEntity.ok(UserService.updateUser(updateUser));
    }

    @PatchMapping("/me/image")
    public ResponseEntity<Void> updateUserImage(@RequestParam("image") MultipartFile image) {
        // Реализация обновления аватара
        return ResponseEntity.ok().build();
    }
}


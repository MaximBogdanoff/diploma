package ru.skypro.homework.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.service.UserService;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/users")
public class UserController {

    @PostMapping("/set_password")
    public ResponseEntity<Void> setPassword(@RequestBody NewPasswordDTO newPassword) {
        // Реализация обновления пароля
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    public ResponseEntity<UserDTO> getUser() {
        // Реализация получения информации об авторизованном пользователе
        return ResponseEntity.ok(UserService.getCurrentUser());
    }

    @PatchMapping("/me")
    public ResponseEntity<UpdateUserDTO> updateUser(@RequestBody UpdateUserDTO updateUserDTO) {
        // Реализация обновления информации об пользователе
        return ResponseEntity.ok(UserService.updateUser(updateUserDTO));
    }

    @PatchMapping("/me/image")
    public ResponseEntity<Void> updateUserImage(@RequestParam("image") MultipartFile image) {
        // Реализация обновления аватара
        return ResponseEntity.ok().build();
    }
}


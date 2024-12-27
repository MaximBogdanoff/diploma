package ru.skypro.homework.service;

import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.dto.UserDTO;

public interface UserService {
    static UserDTO getCurrentUser() {
        return getCurrentUser();
    }

    static UpdateUserDTO updateUser(UpdateUserDTO updateUserDTO) {
     return  updateUserDTO;
    }
}

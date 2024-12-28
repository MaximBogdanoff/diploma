package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.exception.PasswordIsNotMatchException;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.mapper.UserMapperInterface;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.LoggingMethod;
import ru.skypro.homework.service.UserService;

import java.io.IOException;

/**
 * Сервис хранящий логику для управления данными пользователей.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ImageRepository imageRepository;
    private final UserMapper userMapper;
    private final ImageServiceImpl imageService;
    private final LoggingMethod loggingMethod;

    private final UserMapperInterface userMapperInterface;
    // private final PasswordEncoder encoder ?
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Value("${path.to.photos.folder}")
    private String photoDir;

    @Override
    public void setPassword(NewPasswordDTO newPass, Authentication authentication) {

        log.info("Запущен метод сервиса {}", loggingMethod.getMethodName());
        String oldPassword = newPass.getCurrentPassword();
        String encodeNewPassword = passwordEncoder.encode(newPass.getNewPassword());
        User entity = userRepository.findUserByUsername(authentication.getName());
        if (!passwordEncoder.matches(oldPassword, entity.getPassword())) {
            throw new PasswordIsNotMatchException("Пароли не совпадают");
        } else {
            entity.setPassword(encodeNewPassword);
        }
        userRepository.save(entity);
    }
    @Transactional
    @Override
    public User getUser(String username) {

        log.info("Запущен метод сервиса {}", loggingMethod.getMethodName());
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            throw new UserNotFoundException("Пользователя с таким логином в базе данных нет");
        }
        return user;
    }

    @Transactional
    @Override
    public User updateUser(UpdateUserDTO updateUser, Authentication authentication) {

        log.info("Запущен метод сервиса {}", loggingMethod.getMethodName());
        String userName = authentication.getName();
        User user = userRepository.findUserByUsername(userName);
        user.setFirstName(updateUser.getFirstName());
        user.setLastName(updateUser.getLastName());
        user.setPhone(updateUser.getPhone());
        userRepository.save(user);
        return user;
    }

    @Transactional
    @Override
    public void updateUserImage(MultipartFile image, Authentication authentication) throws IOException {
        log.info("Запущен метод сервиса {}", loggingMethod.getMethodName());
        User entity = userRepository.findUserByUsername(authentication.getName());
        entity = (User) imageService.updateEntitiesPhoto(image, entity);
        log.info("userEntity создано - {}", entity);
        assert entity != null;
        userRepository.save(entity);
    }
    public UserDTO findUserByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User not found with username: " + username));
        return userMapperInterface.toUserDTO(user);
    }

    public void changePassword(NewPasswordDTO newPasswordDTO, String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User not found with username: " + username));

        System.out.println(user.getPassword());
        if (passwordEncoder.matches(newPasswordDTO.getCurrentPassword(), user.getPassword())) {
            user.setPassword(passwordEncoder.encode(newPasswordDTO.getNewPassword()));
            userRepository.save(user);
        }
    }

}

package ru.skypro.homework.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.CreateOrUpdateAdDTO;
import ru.skypro.homework.dto.ExtendedAdDTO;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.repository.UserRepository;

import java.io.IOException;

@Service
@AllArgsConstructor
public class AdMapper {

    private final UserRepository userRepository;

    /**
     * Entity -> DTO mapping
     *
     * @param entity Ad entity class
     * @return Ad DTO class
     */
    public AdDTO mapToAdDto(Ad entity) {
        AdDTO dto = new AdDTO();
        dto.setAuthor(entity.getAuthor().getId());
        dto.setImage(URLPhotoEnum.URL_PHOTO_CONSTANT.getString() + entity.getImage().getId());
        dto.setPk(entity.getId());
        dto.setPrice(entity.getPrice());
        dto.setTitle(entity.getTitle());
        return dto;
    }

    public Ad mapToAdEntity(CreateOrUpdateAdDTO dto, String username) {
        User author = userRepository.findUserByUsername(username);
        if (author == null) {
            throw new UserNotFoundException("User not found");
        }
        Ad entity = new Ad();
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setAuthor(author);
        return entity;
    }

    public ExtendedAdDTO mapToExtendedAdDto(Ad entity) {
        ExtendedAdDTO dto = new ExtendedAdDTO();
        dto.setPk(entity.getId());
        dto.setAuthorFirstName(entity.getAuthor().getFirstName());
        dto.setAuthorLastName(entity.getAuthor().getLastName());
        dto.setDescription(entity.getDescription());
        dto.setEmail(entity.getAuthor().getUsername());
        dto.setImage(URLPhotoEnum.URL_PHOTO_CONSTANT.getString() + entity.getImage().getId());
        dto.setPhone(entity.getAuthor().getPhone());
        dto.setPrice(entity.getPrice());
        dto.setTitle(entity.getTitle());
        return dto;
    }

    public Image mapMultipartFileToPhoto(MultipartFile image) throws IOException {
        Image photo = new Image();
        photo.setData(image.getBytes());
        photo.setMediaType(image.getContentType());
        photo.setFileSize(image.getSize());
        return photo;
    }

}
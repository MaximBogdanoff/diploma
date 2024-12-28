package ru.skypro.homework.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.CreateOrUpdateAdDTO;

import java.util.List;

import ru.skypro.homework.dto.ExtendedAdDTO;
import ru.skypro.homework.service.AdService;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
public class AdController {

    // Получение всех объявлений
    @GetMapping
    public ResponseEntity<List<AdDTO>> getAllAds() {
        return ResponseEntity.ok(AdService.getAllAds());
    }

    // Добавление объявления
    @PostMapping
    public ResponseEntity<AdDTO> addAd(@RequestPart("properties") CreateOrUpdateAdDTO createOrUpdateAdDTO,
                                       @RequestPart("image") MultipartFile image) {
        return ResponseEntity.status(HttpStatus.CREATED).body(AdService.addAd(createOrUpdateAdDTO, image));
    }

    // Получение информации об объявлении
    @GetMapping("/{id}")
    public ResponseEntity<ExtendedAdDTO> getAd(@PathVariable Integer id) {
        return ResponseEntity.ok(AdService.getAdById(id));
    }

    // Обновление информации об объявлении
    @PatchMapping("/{id}")
    public ResponseEntity<AdDTO> updateAds(@PathVariable Integer id,
                                           @RequestBody CreateOrUpdateAdDTO createOrUpdateAdDTO) {
        return ResponseEntity.ok(AdService.updateAd(id, createOrUpdateAdDTO));
    }

    // Удаление объявления
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeAd(@PathVariable Integer id) {
        AdService.removeAd(id);
        return ResponseEntity.noContent().build();
    }

    // Получение объявлений авторизованного пользователя
    @GetMapping("/me")
    public ResponseEntity<List<AdDTO>> getAdsMe() {
        return ResponseEntity.ok(AdService.getAdsByCurrentUser());
    }

    // Обновление картинки объявления
    @PatchMapping("/{id}/image")
    public ResponseEntity<byte[]> updateImage(@PathVariable Integer id,
                                              @RequestPart("image") MultipartFile image) {
        byte[] updatedImage = AdService.updateAdImage(id, image);
        return ResponseEntity.ok(updatedImage);
    }
}
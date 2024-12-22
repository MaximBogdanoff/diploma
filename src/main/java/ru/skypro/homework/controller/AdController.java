package ru.skypro.homework.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.CreateOrUpdateAd;

import java.util.List;

import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.service.AdService;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
public class AdController {

    // Получение всех объявлений
    @GetMapping
    public ResponseEntity<List<Ad>> getAllAds() {
        return ResponseEntity.ok(AdService.getAllAds());
    }

    // Добавление объявления
    @PostMapping
    public ResponseEntity<Ad> addAd(@RequestPart("properties") CreateOrUpdateAd createOrUpdateAd,
                                    @RequestPart("image") MultipartFile image) {
        return ResponseEntity.status(HttpStatus.CREATED).body(AdService.addAd(createOrUpdateAd, image));
    }

    // Получение информации об объявлении
    @GetMapping("/{id}")
    public ResponseEntity<ExtendedAd> getAd(@PathVariable Integer id) {
        return ResponseEntity.ok(AdService.getAdById(id));
    }

    // Обновление информации об объявлении
    @PatchMapping("/{id}")
    public ResponseEntity<Ad> updateAds(@PathVariable Integer id,
                                        @RequestBody CreateOrUpdateAd createOrUpdateAd) {
        return ResponseEntity.ok(AdService.updateAd(id, createOrUpdateAd));
    }

    // Удаление объявления
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeAd(@PathVariable Integer id) {
        AdService.removeAd(id);
        return ResponseEntity.noContent().build();
    }

    // Получение объявлений авторизованного пользователя
    @GetMapping("/me")
    public ResponseEntity<List<Ad>> getAdsMe() {
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
package ru.skypro.homework.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.service.AdService;

import java.util.List;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
public class CommentController {

    @GetMapping("/{id}/comments")
    public ResponseEntity<List<CommentDTO>> getComments(@PathVariable Integer id) {
        return ResponseEntity.ok(AdService.getCommentsByAdId(id));
    }

    // Добавление комментария к объявлению
    @PostMapping("/{id}/comments")
    public ResponseEntity<CommentDTO> addComment(@PathVariable Integer id,
                                                 @RequestBody CreateOrUpdateComment createOrUpdateComment) {
        return ResponseEntity.ok(AdService.addComment(id, createOrUpdateComment));
    }

    // Удаление комментария
    @DeleteMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Integer adId,
                                              @PathVariable Integer commentId) {
        AdService.deleteComment(adId, commentId);
        return ResponseEntity.ok().build();
    }

    // Обновление комментария
    @PatchMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable Integer adId,
                                                    @PathVariable Integer commentId,
                                                    @RequestBody CreateOrUpdateComment updateComment) {
        return ResponseEntity.ok(AdService.updateComment(adId, commentId, updateComment));
    }
}
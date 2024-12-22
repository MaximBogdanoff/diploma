package ru.skypro.homework.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.service.AdService;

import java.util.List;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
public class CommentsController {

    @GetMapping("/{id}/comments")
    public ResponseEntity<List<Comment>> getComments(@PathVariable Integer id) {
        return ResponseEntity.ok(AdService.getCommentsByAdId(id));
    }

    // Добавление комментария к объявлению
    @PostMapping("/{id}/comments")
    public ResponseEntity<Comment> addComment(@PathVariable Integer id,
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
    public ResponseEntity<Comment> updateComment(@PathVariable Integer adId,
                                                 @PathVariable Integer commentId,
                                                 @RequestBody CreateOrUpdateComment updateComment) {
        return ResponseEntity.ok(AdService.updateComment(adId, commentId, updateComment));
    }
}
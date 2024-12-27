package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.*;
import lombok.Data;

import java.util.List;

@Data
public class CommentsDTO {

    @Schema(description = "общее количество комментариев")
    private int count;

    @Schema (description = "список всех комментариев объявления")
    private List<CommentDTO> result;
}

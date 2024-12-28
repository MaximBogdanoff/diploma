package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.*;
import lombok.Data;

import java.util.List;

@Data
public class AdsDTO {
    @Schema(description = "общее количество объявлений")
    private int count;

    @Schema (description = "список всех объявлений")
    private List<AdDTO> result;
}

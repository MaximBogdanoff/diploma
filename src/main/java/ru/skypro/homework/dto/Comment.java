package ru.skypro.homework.dto;

import java.time.LocalDateTime;

public class Comment {
    private Integer author; // ID автора комментария
    private String authorImage; // Ссылка на аватар автора комментария
    private String authorFirstName; // Имя создателя комментария
    private Integer createdAt; // Дата создания комментария
    private Integer pk;// id комментария
    private String text;//Текст комментария
}


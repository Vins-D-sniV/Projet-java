package com.projetSpring.api.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public class BookRequestDto {

    @NotBlank(message = "Le titre est obligatoire")
    @Size(max = 255, message = "Le titre doit faire moins de 255 caractères")
    private String title;

    @NotBlank(message = "L'auteur est obligatoire")
    @Size(max = 255, message = "Le nom de l'auteur doit faire moins de 255 caractères")
    private String author;

    private LocalDate publishedDate;

    // Getters et setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public LocalDate getPublishedDate() { return publishedDate; }
    public void setPublishedDate(LocalDate publishedDate) { this.publishedDate = publishedDate; }
}
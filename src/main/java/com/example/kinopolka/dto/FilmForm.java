package com.example.kinopolka.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public class FilmForm {
    private Long id;

    @NotBlank
    @Size(max = 250)
    private String title;

    @Min(1888)
    private Integer releaseYear;

    @Min(1)
    private Integer durationMin;

    @DecimalMin("0.0")
    @DecimalMax("10.0")
    private BigDecimal rating;

    @Size(max = 2000)
    private String description;

    @NotNull
    private Long directorId;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public Integer getReleaseYear() { return releaseYear; }
    public void setReleaseYear(Integer releaseYear) { this.releaseYear = releaseYear; }

    public Integer getDurationMin() { return durationMin; }
    public void setDurationMin(Integer durationMin) { this.durationMin = durationMin; }

    public BigDecimal getRating() { return rating; }
    public void setRating(BigDecimal rating) { this.rating = rating; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Long getDirectorId() { return directorId; }
    public void setDirectorId(Long directorId) { this.directorId = directorId; }
}

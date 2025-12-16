package com.example.kinopolka.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Entity
@Table(name = "films")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 250)
    @Column(name = "title", nullable = false, length = 250)
    private String title;

    @Min(1888)
    @Column(name = "release_year")
    private Integer releaseYear;

    @Min(1)
    @Column(name = "duration_min")
    private Integer durationMin;

    @DecimalMin("0.0")
    @DecimalMax("10.0")
    @Column(name = "rating", precision = 3, scale = 1) // NUMERIC(3,1)
    private BigDecimal rating;

    @Size(max = 2000)
    @Column(name = "description", length = 2000)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "director_id", nullable = false)
    private Director director;

    public Film() {}

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

    public Director getDirector() { return director; }
    public void setDirector(Director director) { this.director = director; }
}


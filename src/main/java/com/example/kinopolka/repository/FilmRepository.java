package com.example.kinopolka.repository;

import com.example.kinopolka.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilmRepository extends JpaRepository<Film, Long> {

    // Фильмы конкретного режиссёра, отсортированы по рейтингу убыванию
    List<Film> findByDirectorIdOrderByRatingDesc(Long directorId);
}

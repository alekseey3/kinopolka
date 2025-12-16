package com.example.kinopolka.service;

import com.example.kinopolka.dto.FilmForm;
import com.example.kinopolka.model.Director;
import com.example.kinopolka.model.Film;
import com.example.kinopolka.repository.DirectorRepository;
import com.example.kinopolka.repository.FilmRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmService {
    private final FilmRepository filmRepository;
    private final DirectorRepository directorRepository;

    public FilmService(FilmRepository filmRepository, DirectorRepository directorRepository) {
        this.filmRepository = filmRepository;
        this.directorRepository = directorRepository;
    }

    public List<Film> findAll() {
        return filmRepository.findAll();
    }

    public Film findById(Long id) {
        return filmRepository.findById(id).orElseThrow();
    }

    public void deleteById(Long id) {
        filmRepository.deleteById(id);
    }

    // Для страницы профиля режиссёра
    public List<Film> findByDirector(Long directorId) {
        return filmRepository.findByDirectorIdOrderByRatingDesc(directorId);
    }

    public Film createFromForm(FilmForm form) {
        Director director = directorRepository.findById(form.getDirectorId()).orElseThrow();

        Film film = new Film();
        film.setTitle(form.getTitle());
        film.setReleaseYear(form.getReleaseYear());
        film.setDurationMin(form.getDurationMin());
        film.setRating(form.getRating());
        film.setDescription(form.getDescription());
        film.setDirector(director);

        return filmRepository.save(film);
    }

    public Film updateFromForm(Long id, FilmForm form) {
        Film film = findById(id);
        Director director = directorRepository.findById(form.getDirectorId()).orElseThrow();

        film.setTitle(form.getTitle());
        film.setReleaseYear(form.getReleaseYear());
        film.setDurationMin(form.getDurationMin());
        film.setRating(form.getRating());
        film.setDescription(form.getDescription());
        film.setDirector(director);

        return filmRepository.save(film);
    }

    public FilmForm toForm(Film film) {
        FilmForm form = new FilmForm();
        form.setId(film.getId());
        form.setTitle(film.getTitle());
        form.setReleaseYear(film.getReleaseYear());
        form.setDurationMin(film.getDurationMin());
        form.setRating(film.getRating());
        form.setDescription(film.getDescription());
        form.setDirectorId(film.getDirector().getId());
        return form;
    }
}

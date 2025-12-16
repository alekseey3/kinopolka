package com.example.kinopolka.service;

import com.example.kinopolka.model.Director;
import com.example.kinopolka.repository.DirectorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DirectorService {
    private final DirectorRepository directorRepository;

    public DirectorService(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
    }

    public List<Director> findAll() {
        return directorRepository.findAll();
    }

    public Director findById(Long id) {
        return directorRepository.findById(id).orElseThrow();
    }

    public Director save(Director director) {
        return directorRepository.save(director);
    }

    public void deleteById(Long id) {
        directorRepository.deleteById(id);
    }
}

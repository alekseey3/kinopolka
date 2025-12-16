package com.example.kinopolka.controller;

import com.example.kinopolka.model.Director;
import com.example.kinopolka.service.DirectorService;
import com.example.kinopolka.service.FilmService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/directors")
public class DirectorController {

    private final DirectorService directorService;
    private final FilmService filmService;

    public DirectorController(DirectorService directorService, FilmService filmService) {
        this.directorService = directorService;
        this.filmService = filmService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("directors", directorService.findAll());
        return "directors/list";
    }

    // Профиль режиссёра
    @GetMapping("/{id}")
    public String show(@PathVariable Long id, Model model) {
        model.addAttribute("director", directorService.findById(id));
        model.addAttribute("films", filmService.findByDirector(id));
        return "directors/show";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("director", new Director());
        return "directors/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("director") Director director,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "directors/form";
        directorService.save(director);
        return "redirect:/directors";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("director", directorService.findById(id));
        return "directors/form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("director") Director director,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "directors/form";
        director.setId(id);
        directorService.save(director);
        return "redirect:/directors";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        directorService.deleteById(id);
        return "redirect:/directors";
    }
}

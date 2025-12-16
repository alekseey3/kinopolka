package com.example.kinopolka.controller;

import com.example.kinopolka.dto.FilmForm;
import com.example.kinopolka.service.DirectorService;
import com.example.kinopolka.service.FilmService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/films")
public class FilmController {

    private final FilmService filmService;
    private final DirectorService directorService;

    public FilmController(FilmService filmService, DirectorService directorService) {
        this.filmService = filmService;
        this.directorService = directorService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("films", filmService.findAll());
        return "films/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("filmForm", new FilmForm());
        model.addAttribute("directors", directorService.findAll());
        return "films/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("filmForm") FilmForm filmForm,
                         BindingResult bindingResult,
                         Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("directors", directorService.findAll());
            return "films/form";
        }
        filmService.createFromForm(filmForm);
        return "redirect:/films";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("filmForm", filmService.toForm(filmService.findById(id)));
        model.addAttribute("directors", directorService.findAll());
        return "films/form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("filmForm") FilmForm filmForm,
                         BindingResult bindingResult,
                         Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("directors", directorService.findAll());
            return "films/form";
        }
        filmService.updateFromForm(id, filmForm);
        return "redirect:/films";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        filmService.deleteById(id);
        return "redirect:/films";
    }
}

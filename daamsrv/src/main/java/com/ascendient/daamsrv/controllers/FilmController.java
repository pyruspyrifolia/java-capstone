package com.ascendient.daamsrv.controllers;

import com.ascendient.daamsrv.entities.Film;
import com.ascendient.daamsrv.repositories.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
//This encapsulates the full HTTP Response as a response entity
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/films")
@CrossOrigin(origins = "*")
public class FilmController {

    @Autowired
    private FilmRepository filmRepository;

    @GetMapping
    public ResponseEntity<List<Film>> getAllFilms() {
        try {
            List<Film> films = filmRepository.findAll();
            return ResponseEntity.ok(films);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Film> getFilmById(@PathVariable Long id) {
        try {
            Optional<Film> film = filmRepository.findById(id);
            if (film.isPresent()) {
                return ResponseEntity.ok(film.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<Long> createFilm(@RequestBody Film film) {
        try {
            Film savedFilm = filmRepository.save(film);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedFilm.getId());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Film> updateFilm(@PathVariable Long id, @RequestBody Film film) {
        try {
            Optional<Film> existingFilm = filmRepository.findById(id);
            if (existingFilm.isPresent()) {
                film.setId(id);
                Film savedFilm = filmRepository.save(film);
                return ResponseEntity.ok(savedFilm);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFilm(@PathVariable Long id) {
        try {
            Optional<Film> film = filmRepository.findById(id);
            if (film.isPresent()) {
                filmRepository.deleteById(id);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
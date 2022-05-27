package ru.job4j.passportmanagementservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.passportmanagementservice.domain.Passport;
import ru.job4j.passportmanagementservice.service.PassportService;

import java.util.Collection;

@RestController
@RequestMapping("/passport")
public class PassportController {
    private final PassportService passportService;

    public PassportController(PassportService passportService) {
        this.passportService = passportService;
    }

    @GetMapping("/all")
    public ResponseEntity<Collection<Passport>> findAllPassports() {
    return new ResponseEntity<>(passportService.findAll(), HttpStatus.FOUND);
    }

    @GetMapping("/series/{series}")
    public ResponseEntity<Collection<Passport>> searchBySeries(@PathVariable Long series) {
        return new ResponseEntity<>(passportService.findBySeries(series), HttpStatus.FOUND);
    }

    @GetMapping("/unavailable")
    public ResponseEntity<Collection<Passport>> unavailable() {
        return new ResponseEntity<>(passportService.unavailable(), HttpStatus.FOUND);
    }

    @GetMapping("/replaceAbl")
    public ResponseEntity<Collection<Passport>> findReplaceAble() {
        return new ResponseEntity<>(passportService.findReplaceAble(), HttpStatus.FOUND);
    }

    @PostMapping("/save")
    public ResponseEntity<Passport> save(@RequestBody Passport passport) {
      return new ResponseEntity<>(passportService.save(passport), HttpStatus.CREATED);
    }

    @PutMapping("/replace")
    public ResponseEntity<Void> replace(@RequestBody Passport passport) {
        passportService.save(passport);
    return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        passportService.deleteById(id);
    return ResponseEntity.ok().build();
    }
}

package ru.job4j.passportmanagementservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
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
    public ResponseEntity<Iterable<Passport>> findAllPassports() {
        return new ResponseEntity<>(passportService.findAll(), HttpStatus.FOUND);
    }

    @GetMapping("/series/{series}")
    public ResponseEntity<Collection<Passport>> searchBySeries(@PathVariable Long series) {
        return new ResponseEntity<>(passportService.findBySeries(series), HttpStatus.FOUND);
    }

    @GetMapping("/unavailable")
    public ResponseEntity<Collection<Passport>> unavailable() {
        return new ResponseEntity<>(passportService.findUnavailablePassports(), HttpStatus.FOUND);
    }

    @GetMapping("/replaceAbl")
    public ResponseEntity<Collection<Passport>> findReplaceAble() {
        return new ResponseEntity<>(passportService.findReplaceAble(), HttpStatus.FOUND);
    }

    @PostMapping("/save")
    public ResponseEntity<Passport> save(@RequestBody Passport passport) {
        Passport savedPassport = passportService.save(passport).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "Passport already exists"));
        return new ResponseEntity<>(savedPassport, HttpStatus.CREATED);
    }

    @PutMapping("/replace")
    public ResponseEntity<Void> replace(@RequestBody Passport passport) {
        passportService.findById(passport.getId()).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Passport is not found"));
        passportService.save(passport).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "Passport already exists"));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        passportService.deleteById(id).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Passport is not found"));
        return ResponseEntity.ok().build();
    }
}

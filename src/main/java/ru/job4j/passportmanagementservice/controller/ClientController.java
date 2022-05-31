package ru.job4j.passportmanagementservice.controller;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.job4j.passportmanagementservice.domain.Passport;
import ru.job4j.passportmanagementservice.exeption.Operation;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/client")
@Validated
public class ClientController {
    private final RestTemplate rest;

    public ClientController(RestTemplate rest) {
        this.rest = rest;
    }

    @GetMapping("/")
    public Collection<Passport> findAllPassports() {
        return rest.exchange(
                "http://localhost:8080/passport/all",
                HttpMethod.GET, null,
                new ParameterizedTypeReference<Collection<Passport>>() { }).getBody();
    }

    @GetMapping("/{series}")
    public Collection<Passport> searchBySeries(@PathVariable Long series) {
        return rest.exchange(
                String.format("http://localhost:8080/passport/series/%s", series),
                HttpMethod.GET, null,
                new ParameterizedTypeReference<Collection<Passport>>() { }).getBody();
    }

    @GetMapping("/unavailable")
    public Collection<Passport> unavailable() {
        return rest.exchange(
                "http://localhost:8080/passport/unavailable",
                HttpMethod.GET, null,
                new ParameterizedTypeReference<Collection<Passport>>() { }).getBody();
    }

    @GetMapping("/replaceAbl")
    public Collection<Passport> findReplaceAble() {
        return rest.exchange(
                "http://localhost:8080/passport/replaceAbl",
                HttpMethod.GET, null,
                new ParameterizedTypeReference<Collection<Passport>>() { }).getBody();
    }

    @PostMapping("/")
    @Validated(Operation.OnCreate.class)
    public ResponseEntity<Passport> save(@Valid @RequestBody Passport passport) {
        Passport rsl = rest.postForObject("http://localhost:8080/passport/save",
                passport, Passport.class);
        return new ResponseEntity<>(rsl, HttpStatus.CREATED);
    }

    @PutMapping("/")
    @Validated(Operation.OnUpdate.class)
    public ResponseEntity<Void> replace(@Valid @RequestBody Passport passport) {
        rest.put("http://localhost:8080/passport/replace", passport);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        rest.delete("http://localhost:8080/passport/delete/{id}", id);
        return ResponseEntity.ok().build();
    }
}

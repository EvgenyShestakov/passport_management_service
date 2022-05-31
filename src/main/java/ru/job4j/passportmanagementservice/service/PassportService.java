package ru.job4j.passportmanagementservice.service;

import ru.job4j.passportmanagementservice.domain.Passport;
import java.util.Collection;
import java.util.Optional;

public interface PassportService {
Optional<Passport> save(Passport passport);

Optional<Passport> findById(Long id);

Optional<Passport> deleteById(Long id);

Iterable<Passport> findAll();

Collection<Passport> findBySeries(Long series);

Collection<Passport> unavailable();

Collection<Passport> findReplaceAble();
}

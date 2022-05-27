package ru.job4j.passportmanagementservice.service;

import ru.job4j.passportmanagementservice.domain.Passport;
import java.util.Collection;

public interface PassportService {
Passport save(Passport passport);

void deleteById(Long id);

Collection<Passport> findAll();

Collection<Passport> findBySeries(Long series);

Collection<Passport> unavailable();

Collection<Passport> findReplaceAble();
}

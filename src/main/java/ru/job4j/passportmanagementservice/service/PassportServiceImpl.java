package ru.job4j.passportmanagementservice.service;

import org.springframework.stereotype.Service;
import ru.job4j.passportmanagementservice.domain.Passport;
import ru.job4j.passportmanagementservice.repository.PassportRepository;
import java.util.Collection;
import java.util.Optional;

@Service
public class PassportServiceImpl implements PassportService {
    private PassportRepository passportRepository;

    public PassportServiceImpl(PassportRepository passportRepository) {
        this.passportRepository = passportRepository;
    }

    @Override
    public Optional<Passport> save(Passport passport) {
        Optional<Passport> findPassport = passportRepository.
                findPassportBySeriesAndNumber(passport.getSeries(), passport.getNumber());
        Optional<Passport> savePassport = Optional.empty();
        if (findPassport.isEmpty()) {
            savePassport = Optional.of(passportRepository.save(passport));
        }
        return savePassport;
    }

    public Optional<Passport> deleteById(Long id) {
        Optional<Passport> byId = passportRepository.findById(id);
        byId.ifPresent(passport -> passportRepository.delete(passport));
        return byId;
    }

    public Optional<Passport> findById(Long id) {
        return passportRepository.findById(id);
    }

    @Override
    public Iterable<Passport> findAll() {
        return passportRepository.findAll();
    }

    @Override
    public Collection<Passport> findBySeries(Long series) {
        return passportRepository.findPassportBySeries(series);
    }

    @Override
    public Collection<Passport> unavailable() {
        return passportRepository.unavailable();
    }

    @Override
    public Collection<Passport> findReplaceAble() {
        return passportRepository.findReplaceable();
    }
}

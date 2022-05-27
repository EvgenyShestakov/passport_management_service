package ru.job4j.passportmanagementservice.service;

import org.springframework.stereotype.Service;
import ru.job4j.passportmanagementservice.domain.Passport;
import ru.job4j.passportmanagementservice.repository.PassportRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class PassportServiceImpl implements PassportService {
    private PassportRepository passportRepository;

    public PassportServiceImpl(PassportRepository passportRepository) {
        this.passportRepository = passportRepository;
    }

    @Override
    public Passport save(Passport passport) {
        return passportRepository.save(passport);
    }

    public void deleteById(Long id) {
        passportRepository.deleteById(id);
    }

    @Override
    public Collection<Passport> findAll() {
        List<Passport> passports = new ArrayList<>();
        passportRepository.findAll().forEach(passports::add);
        return passports;
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
        return passportRepository.findReplaceable(LocalDate.now().plusMonths(3));
    }
}

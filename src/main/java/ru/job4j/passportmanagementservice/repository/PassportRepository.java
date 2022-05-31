package ru.job4j.passportmanagementservice.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.passportmanagementservice.domain.Passport;
import java.util.Collection;
import java.util.Optional;

public interface PassportRepository extends CrudRepository<Passport, Long> {
    Collection<Passport> findPassportBySeries(Long series);

    @Query("select p from Passport p where p.expirationDate < current_date")
    Collection<Passport> unavailable();

    @Query(value = "select * from passport p where expiration_date between"
            + " current_date and current_date + interval '3 month'", nativeQuery = true)
    Collection<Passport> findReplaceable();

    Optional<Passport> findPassportBySeriesAndNumber(Long series, Long number);
}
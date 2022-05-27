package ru.job4j.passportmanagementservice.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.job4j.passportmanagementservice.domain.Passport;
import java.time.LocalDate;
import java.util.Collection;

public interface PassportRepository extends CrudRepository<Passport, Long> {
    Collection<Passport> findPassportBySeries(Long series);

    @Query("select p from Passport p where p.expirationDate < current_date")
    Collection<Passport> unavailable();

    @Query(value = "select * from passport p where expiration_date between"
            + " current_date and current_date + interval '3 month'", nativeQuery = true)
    Collection<Passport> findReplaceable(@Param("bottomLine") LocalDate bottomLine);

}
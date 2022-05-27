package ru.job4j.passportmanagementservice.domain;

import lombok.Getter;
import lombok.Setter;
import ru.job4j.passportmanagementservice.exeption.Operation;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "passport")
public class Passport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "Id must be non empty", groups = Operation.OnUpdate.class)
    @Column(name = "id")
    private Long id;

    @Column(name = "series")
    @NotNull(message = "Series must be non empty")
    private Long series;

    @Column(name = "number")
    @NotNull(message = "Number must be non empty")
    private Long number;

    @Column(name = "expiration_date")
    @NotNull(message = "ExpirationDate must be non empty")
    private Timestamp expirationDate;

    public Passport() {
    }

    public Passport(Long series, Long number, Timestamp expirationDate) {
        this.series = series;
        this.number = number;
        this.expirationDate = expirationDate;
    }
}

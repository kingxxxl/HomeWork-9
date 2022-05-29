package com.example.movieratingsoftware.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor @Data @NoArgsConstructor
@Entity @Validated
public class Movie {
    @Id
    @Positive
    @Min(value = 4, message = "id length has to be more than 3")
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;
    @NotNull
    @Size(min = 3, message = "name length has to be more than 2")
    @Column(nullable = false, unique = true)
    private String name;
    @NotNull
    @Column(nullable = false)
    @Pattern(regexp = "drama|action|comedy")
    private String genre;
    @Pattern(regexp = "[1-5]")
    private String rate;
    @NotNull
    @PositiveOrZero
    @Min(60)
    private Integer duration;
    //loc
   // private LocalDate date;
    private String date;


}

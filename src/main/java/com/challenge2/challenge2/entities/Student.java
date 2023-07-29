package com.challenge2.challenge2.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Student extends User{

    @Column(name = "college")
    private String college;

//    @Column(name= "semester")
//    private Integer semester;

    @Min(value = 0, message = "A nota deve ser no minimo 0")
    @Max(value = 10, message = "A nota deve ser no maximo 10")
    @Column(name = "grade")
    private Float grade;

    @Min(value = 0, message = "O valor deve ser maior que zero")
    @Column(name = "attendance")
    private Float attendance;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "startDate")
    private LocalDateTime startDate;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime endDate;

    @ManyToOne
    @JoinColumn(name = "squadId")
    private Squad squad;

}

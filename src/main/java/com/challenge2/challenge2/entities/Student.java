package com.challenge2.challenge2.entities;

import jakarta.persistence.*;
import lombok.*;

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

    @Column(name= "semester")
    private Integer semester;

    @Column(name = "grade")
    private Float grade;

    @Column(name = "attendance")
    private Float attendance;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

}

package com.challenge2.challenge2.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Table(name = "student", schema = "scholarshipprogramclasses")
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

    @ManyToOne
    @JoinColumn(name = "squad_id")
    private Squad squad;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}

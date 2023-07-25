package com.challenge2.challenge2.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Table(name = "class", schema = "scholarshipprogramclasses")
@Getter
@Setter
public class Class {

    @Id
    @Column(name = "class_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "learning_path")
    private String learningPath;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name= "sprint")
    private Integer sprint;

    @ManyToOne
    @JoinColumn(name = "squad")
    private Squad squad;


}

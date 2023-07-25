package com.challenge2.challenge2.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Table(name = "squad", schema = "scholarshipprogramclasses")
@Getter
@Setter
public class Squad{

    @Id
    @Column(name = "squad_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long squadId;

    @Column(name = "squad_name")
    private Long squadName;

}

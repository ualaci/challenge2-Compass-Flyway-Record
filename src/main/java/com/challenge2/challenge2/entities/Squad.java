package com.challenge2.challenge2.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Squad{

    @Id
    @Column(name = "squad_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long squadId;

    @Column(name = "squad_name")
    private String squadName;

}

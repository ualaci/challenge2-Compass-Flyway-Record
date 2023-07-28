package com.challenge2.challenge2.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Squad{

    @Id
    @Column(name = "squadId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long squadId;

    @Column(name = "squadName")
    private Long squadName;

    @OneToMany(mappedBy = "squad")
    private List<Student> students;

}

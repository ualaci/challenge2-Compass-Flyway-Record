package com.challenge2.challenge2.entities;

import com.challenge2.challenge2.enums.OrganizerEnums;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Organizer extends User{


    @Column(name = "role")
    @Enumerated(value= EnumType.STRING)
    private OrganizerEnums role = OrganizerEnums.getDefault();
}

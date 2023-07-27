package com.challenge2.challenge2.entities;

import com.challenge2.challenge2.enums.OrganizerEnums;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Getter
@Setter
public class Organizer extends User{

    @Column(name = "role")
    @Enumerated(value= EnumType.STRING)
    private OrganizerEnums organizer;
}

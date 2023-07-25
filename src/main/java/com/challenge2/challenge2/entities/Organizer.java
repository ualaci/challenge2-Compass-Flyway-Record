package com.challenge2.challenge2.entities;

import com.challenge2.challenge2.enums.OrganizerEnums;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Table(name = "organizer", schema = "scholarshipprogramclasses")
@Getter
@Setter
public class Organizer extends User{

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "role")
    @Enumerated(value= EnumType.STRING)
    private OrganizerEnums organizer;
}

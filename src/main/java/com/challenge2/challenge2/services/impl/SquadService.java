package com.challenge2.challenge2.services.impl;

import com.challenge2.challenge2.entities.Squad;

import java.util.List;
import java.util.Optional;

public interface SquadService {
    List<Squad> getAllSquads();

    Optional<Squad> getSquadById(Long id);

    Squad saveSquad(Squad squad);

    void deleteSquad(Long id);

}

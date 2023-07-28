package com.challenge2.challenge2.services.impl;

import com.challenge2.challenge2.entities.Organizer;

import java.util.List;
import java.util.Optional;

public interface OrganizerService {

    List<Organizer> getAllOrganizers();

    Optional<Organizer> getOrganizerById(Long id);

    Organizer saveOrganizer(Organizer organizer);

    void deleteOrganizer(Long id);
}

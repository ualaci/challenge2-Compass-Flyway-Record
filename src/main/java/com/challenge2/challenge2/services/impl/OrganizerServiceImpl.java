package com.challenge2.challenge2.services.impl;

import com.challenge2.challenge2.entities.Organizer;
import com.challenge2.challenge2.repositories.OrganizerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizerServiceImpl implements OrganizerService{

    private OrganizerRepository organizerRepository;


    public OrganizerServiceImpl(OrganizerRepository organizerRepository) {

        this.organizerRepository = organizerRepository;
    }

    public List<Organizer> getAllOrganizers() {

        return organizerRepository.findAll();
    }

    public Organizer getOrganizerById(Long id) {
        return organizerRepository.findById(id).orElse(null);
    }

    public Organizer saveOrganizer(Organizer organizer) {

        return organizerRepository.save(organizer);
    }

    public void deleteOrganizer(Long id) {

        organizerRepository.deleteById(id);
    }
}

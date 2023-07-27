package com.challenge2.challenge2.services.impl;

import com.challenge2.challenge2.entities.Squad;
import com.challenge2.challenge2.repositories.SquadRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SquadServiceImpl implements SquadService{


    private SquadRepository squadRepository;


    public SquadServiceImpl(SquadRepository squadRepository) {

        this.squadRepository = squadRepository;
    }
    public List<Squad> getAllSquads() {
        return squadRepository.findAll();
    }

    public Squad getSquadById(Long id) {
        return squadRepository.findById(id).orElse(null);
    }

    public Squad saveSquad(Squad squad) {
        return squadRepository.save(squad);
    }

    public void deleteSquad(Long id) {
        squadRepository.deleteById(id);
    }

}

package com.challenge2.challenge2.services.impl;

import com.challenge2.challenge2.entities.Squad;
import com.challenge2.challenge2.repositories.SquadRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SquadServiceImpl implements SquadService{


    private static SquadRepository squadRepository;

    public SquadServiceImpl(SquadRepository squadRepository) {

        this.squadRepository = squadRepository;
    }

    @Override
    public List<Squad> getAllSquads() {

        try {
            return squadRepository.findAll();
        }
        catch (Exception e){
            System.out.println("Erro ao encontrar objeto!");
            return null;
        }
    }

    @Override
    public Optional<Squad> getSquadById(Long id) {

        return squadRepository.findById(id);
    }

    @Override
    public Squad saveSquad(Squad squad) {

        return squadRepository.save(squad);
    }

    @Override
    public void deleteSquad(Long id) {

        squadRepository.deleteById(id);
    }

}

package com.challenge2.challenge2.services.impl;

import com.challenge2.challenge2.dto.SquadDTO;
import com.challenge2.challenge2.entities.Squad;
import com.challenge2.challenge2.entities.Student;
import com.challenge2.challenge2.exceptions.BadRequestException;
import com.challenge2.challenge2.repositories.SquadRepository;
import com.challenge2.challenge2.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SquadServiceImpl implements SquadService{


    private static SquadRepository squadRepository;
    private static StudentRepository studentRepository;

    @Autowired
    public SquadServiceImpl(SquadRepository squadRepository, StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
        this.squadRepository = squadRepository;
    }

    @Override
    public List<Squad> getAllSquads() throws BadRequestException {
        return squadRepository.findAll();
    }

    @Override
    public Optional<Squad> getSquadById(Long id) {
        Optional<Squad> squadOptional = squadRepository.findById(id);
        return squadOptional;
    }

    @Override
    public Squad saveSquad(Squad squad) {

        return squadRepository.save(squad);
    }

    public Squad createSquadWithStudents(SquadDTO squadDTO) {

        List<Long> studentIds = squadDTO.getStudents();
        Squad squad = new Squad();
        squad.setSquadName(squadDTO.getSquadName());

        for (Long studentId : studentIds) {
            if (!studentRepository.existsById(studentId)) {
                throw new IllegalArgumentException("O aluno com ID " + studentId + " não foi encontrado.");
            }
            Optional<Student> student = studentRepository.findById(studentId);
            squad.getStudents().add(student.get());
        }

        return squadRepository.save(squad);
    }

    @Override
    public void deleteSquad(Long id) {

        squadRepository.deleteById(id);
    }

}

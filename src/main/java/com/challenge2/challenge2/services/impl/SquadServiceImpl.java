package com.challenge2.challenge2.services.impl;

import com.challenge2.challenge2.entities.Squad;
import com.challenge2.challenge2.entities.Student;
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

    /*
    public Squad createSquad(Squad squad) {
        List<Student> students = squad.getStudents();
        for (Student student : students) {
            Optional<Student> optionalStudent = studentRepository.findById(student.getId());
            if (optionalStudent.isEmpty()) {
                throw new IllegalArgumentException("O aluno com ID " + student.getId() + " não foi encontrado.");
            }
        }

        Squad newSquad = new Squad();
        newSquad.setSquadName(squad.getSquadName());

        // Associar os estudantes ao novo Squad por meio de seus IDs
        List<Student> squadStudents = studentRepository.findAllById(students.stream().map(Student::getId).toList());
        newSquad.setStudents(students);

        // Salvar o novo Squad no banco de dados
        return squadRepository.save(newSquad);
    }

     */

    public Squad createSquad(Squad squad) {
        List<Student> students = squad.getStudents();
        for (Student student : students) {
            Long studentId = student.getId();
            if (!studentRepository.existsById(studentId)) {
                throw new IllegalArgumentException("O aluno com ID " + studentId + " não foi encontrado.");
            }
        }

        return squadRepository.save(squad);
    }

    @Override
    public void deleteSquad(Long id) {

        squadRepository.deleteById(id);
    }

}

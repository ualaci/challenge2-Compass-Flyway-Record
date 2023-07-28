package com.challenge2.challenge2.restControllers;

import com.challenge2.challenge2.entities.Squad;
import com.challenge2.challenge2.entities.Student;
import com.challenge2.challenge2.services.impl.SquadServiceImpl;
import com.challenge2.challenge2.services.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/squad")
public class SquadController {

    private final SquadServiceImpl squadService;
    private final StudentServiceImpl studentService;

    @Autowired
    public SquadController (SquadServiceImpl squadService, StudentServiceImpl studentService){
        this.squadService = squadService;
        this.studentService = studentService;
    }

    @GetMapping
    public List<Squad> getAllSquads() {
        return squadService.getAllSquads();

    }

    @GetMapping("/{id}")
    public Optional<Squad> getSquadById(@PathVariable Long id) {

        return squadService.getSquadById(id);
    }

    @PostMapping
    public ResponseEntity addSquad(@RequestBody Squad squad) {
        Squad savedSquad = squadService.saveSquad(squad);
        return new ResponseEntity(savedSquad, HttpStatus.CREATED);
    }


    @PostMapping("/perID")
    public ResponseEntity<String> createSquadWithStudents(@RequestBody Squad squad) {
        for (Student student : squad.getStudents()) {
            if (!studentService.existsById(student.getId())) {
                Long studentID = student.getId();
                String message = "O aluno com ID " + studentID + " não foi encontrado.";
                return ResponseEntity.badRequest().body(message);
            }
        }
        Squad createdSquad = squadService.createSquad(squad);

        return new ResponseEntity(createdSquad, HttpStatus.CREATED);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity deleteSquad(@PathVariable Long id){
        return squadService.getSquadById(id).map(entidade ->{
            squadService.deleteSquad(entidade.getSquadId());
            return new ResponseEntity( HttpStatus.NO_CONTENT);
        }).orElseGet(() ->
                new ResponseEntity("Squad não encontrada na base de dados", HttpStatus.BAD_REQUEST));

    }
    @PutMapping
    public ResponseEntity<String> updateSquad(@RequestBody Squad squad){
        return squadService.getSquadById(squad.getSquadId()).map(entidade -> {
            squadService.saveSquad(squad);
            return new ResponseEntity<String>("Squad atualizada com sucesso!", HttpStatus.OK);
        }).orElseGet(() ->
                new ResponseEntity<String>("Essa squad não existe!", HttpStatus.BAD_REQUEST));
    }

}

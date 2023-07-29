package com.challenge2.challenge2.restControllers;

import com.challenge2.challenge2.dto.SquadDTO;
import com.challenge2.challenge2.entities.Squad;
import com.challenge2.challenge2.exceptions.BadRequestException;
import com.challenge2.challenge2.exceptions.NotFoundException;
import com.challenge2.challenge2.services.impl.SquadServiceImpl;
import com.challenge2.challenge2.services.impl.StudentServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/squad")
public class SquadController {

    private final SquadServiceImpl squadService;
    private final StudentServiceImpl studentService;
    public SquadController (SquadServiceImpl squadService, StudentServiceImpl studentService){
        this.squadService = squadService;
        this.studentService = studentService;
    }

    @GetMapping
    public Optional <List<Squad>> getAllSquads() {
        Optional<List<Squad>> squads = Optional.ofNullable(squadService.getAllSquads());
        if(squads.isEmpty()){
            return squads;
        } else {
            throw new NotFoundException("Nenhum squad encontrado");
        }
    }

    @GetMapping("/{id}")
    public Optional<Squad> getSquadById(@PathVariable Long id) {
        Optional<Squad> squad = squadService.getSquadById(id);
        if(squad.isPresent()){
            return squad;
        } else {
            throw new NotFoundException("Squad não encontrada");
        }
    }


    @PostMapping
    public ResponseEntity<Squad> createSquad(@RequestBody SquadDTO squadDTO) {
        Squad createdSquad = squadService.createSquadWithStudents(squadDTO);
        if(createdSquad == null){
            //throw new BadRequestException("Não foi possível criar a squad");
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não foi possível criar a squad");
            return null;
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(createdSquad);
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

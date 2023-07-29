package com.challenge2.challenge2.restControllers;

import com.challenge2.challenge2.entities.ErrorResponse;
import org.springframework.web.bind.annotation.RestController;
import com.challenge2.challenge2.entities.Organizer;
import com.challenge2.challenge2.entities.Squad;
import com.challenge2.challenge2.services.impl.OrganizerService;
import com.challenge2.challenge2.services.impl.OrganizerServiceImpl;
import com.challenge2.challenge2.services.impl.SquadServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/organizer")
public class OrganizerController {

    private final OrganizerServiceImpl organizerService;

    public OrganizerController (OrganizerServiceImpl organizerService){
        this.organizerService = organizerService;
    }

    @GetMapping
    public ResponseEntity<?> getAllOrganizers() {
        ErrorResponse errorResponse = new ErrorResponse("Nenhum organizador encontrado",
        new Timestamp(System.currentTimeMillis()), HttpStatus.NOT_FOUND.name());
        if(organizerService.getAllOrganizers().isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(organizerService.getAllOrganizers());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrganizerById(@PathVariable Long id) {
        ErrorResponse errorResponse = new ErrorResponse("Organizador não encontrado",
                new Timestamp(System.currentTimeMillis()), HttpStatus.NOT_FOUND.name());
        Optional <Organizer> organizer = organizerService.getOrganizerById(id);
        if (organizer.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(organizer);
        }
    }

    @PostMapping
    public ResponseEntity <?> addOrganizer(@RequestBody Organizer organizer) {
        Organizer savedOrganizer = organizerService.saveOrganizer(organizer);
        ErrorResponse errorResponse = new ErrorResponse("Não foi possível criar o organizador",
                new Timestamp(System.currentTimeMillis()), HttpStatus.BAD_REQUEST.name());
        if (savedOrganizer == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(savedOrganizer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <?> deleteOrganizer(@PathVariable Long id){
        ErrorResponse errorResponse = new ErrorResponse("Não foi possível deletar o organizador pois ele não existe",
                new Timestamp(System.currentTimeMillis()), HttpStatus.BAD_REQUEST.name());
        return organizerService.getOrganizerById(id).map(entidade ->{
            organizerService.deleteOrganizer(entidade.getId());
            return new ResponseEntity<>( HttpStatus.NO_CONTENT);
        }).orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ErrorResponse> updateSquad(@RequestBody Organizer organizer, @PathVariable Long id){
        ErrorResponse errorResponseSuccess = new ErrorResponse("Organizador atualizado com sucesso!",
                new Timestamp(System.currentTimeMillis()), HttpStatus.OK.name());
        ErrorResponse errorResponseFail = new ErrorResponse("Não foi possível atualizar o organizador",
                new Timestamp(System.currentTimeMillis()), HttpStatus.BAD_REQUEST.name());

        return organizerService.getOrganizerById(organizer.getId()).map(entidade -> {
            organizerService.saveOrganizer(organizer);
            return new ResponseEntity<>(errorResponseSuccess, HttpStatus.OK);
        }).orElseGet(() ->
                new ResponseEntity<>(errorResponseFail, HttpStatus.BAD_REQUEST));
    }
}

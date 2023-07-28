package com.challenge2.challenge2.restControllers;

import com.challenge2.challenge2.entities.Organizer;
import com.challenge2.challenge2.entities.Squad;
import com.challenge2.challenge2.services.impl.OrganizerService;
import com.challenge2.challenge2.services.impl.OrganizerServiceImpl;
import com.challenge2.challenge2.services.impl.SquadServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/organizer")
public class OrganizerController {

    private OrganizerServiceImpl organizerService;

    public OrganizerController (OrganizerServiceImpl organizerService){
        this.organizerService = organizerService;
    }

    @GetMapping
    public List<Organizer> getAllOrganizers() {
        return organizerService.getAllOrganizers();

    }

    @GetMapping("/{id}")
    public Optional<Organizer> getOrganizerById(@PathVariable Long id) {

        return organizerService.getOrganizerById(id);
    }

    @PostMapping
    public ResponseEntity addOrganizer(@RequestBody Organizer organizer) {
        Organizer savedOrganizer = organizerService.saveOrganizer(organizer);
        return new ResponseEntity(savedOrganizer, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteOrganizer(@PathVariable Long id){
        return organizerService.getOrganizerById(id).map(entidade ->{
            organizerService.deleteOrganizer(entidade.getId());
            return new ResponseEntity( HttpStatus.NO_CONTENT);
        }).orElseGet(() ->
                new ResponseEntity("Organizador não encontrado na base de dados", HttpStatus.BAD_REQUEST));

    }
    @PutMapping
    public ResponseEntity<String> updateSquad(@RequestBody Organizer organizer){
        return organizerService.getOrganizerById(organizer.getId()).map(entidade -> {
            organizerService.saveOrganizer(organizer);
            return new ResponseEntity<String>("Organizador atualizada com sucesso!", HttpStatus.OK);
        }).orElseGet(() ->
                new ResponseEntity<String>("Esse organizador não existe!", HttpStatus.BAD_REQUEST));
    }
}

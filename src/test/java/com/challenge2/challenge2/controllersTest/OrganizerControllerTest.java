package com.challenge2.challenge2.controllersTest;

import com.challenge2.challenge2.entities.ErrorResponse;
import com.challenge2.challenge2.entities.Organizer;
import com.challenge2.challenge2.enums.OrganizerEnums;
import com.challenge2.challenge2.repositories.OrganizerRepository;
import com.challenge2.challenge2.restControllers.OrganizerController;
import com.challenge2.challenge2.services.impl.OrganizerServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = OrganizerController.class)
public class OrganizerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrganizerServiceImpl organizerService;

    @Mock
    private OrganizerRepository organizerRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @InjectMocks
    private OrganizerController organizerController;
    private final Organizer organizer = Organizer.builder()
            .Id(1L)
            .name("TestOrganizer")
            .email("testorganizer@gmail.com")
            .password("TestPassword1234@#")
            .city("TestCity")
            .role(OrganizerEnums.Coordinator)
            .build();

    @BeforeEach
    public void setUp() {
        organizerRepository = org.mockito.Mockito.mock(OrganizerRepository.class);
        organizerService = new OrganizerServiceImpl(organizerRepository);
        organizerController = new OrganizerController(organizerService);
    }

    /*
    @Test
    public void OrganizerController_UpdateOrganizer_ReturnSucces() {
        Organizer updatedOrganizer = new Organizer();
        updatedOrganizer.setId(1L);
        updatedOrganizer.setRole(OrganizerEnums.Coordinator);

        when(organizerService.getOrganizerById(updatedOrganizer.getId())).thenReturn(Optional.of(updatedOrganizer));
        when(organizerService.saveOrganizer(organizer)).thenReturn(organizer);

        ResponseEntity<ErrorResponse> responseEntity = organizerController.updateOrganizer(updatedOrganizer);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        ErrorResponse errorResponse = responseEntity.getBody();
        Assertions.assertNotNull(errorResponse);
        assertEquals("Organizador atualizado com sucesso!", errorResponse.getMessage());
    }*/

}
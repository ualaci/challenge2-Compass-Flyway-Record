package com.challenge2.challenge2.controllersTest;

import com.challenge2.challenge2.dto.SquadDTO;
import com.challenge2.challenge2.entities.ErrorResponse;
import com.challenge2.challenge2.entities.Squad;
import com.challenge2.challenge2.entities.Student;
import com.challenge2.challenge2.repositories.SquadRepository;
import com.challenge2.challenge2.repositories.StudentRepository;
import com.challenge2.challenge2.restControllers.SquadController;
import com.challenge2.challenge2.services.impl.SquadServiceImpl;
import com.challenge2.challenge2.services.impl.StudentServiceImpl;
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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

//@SpringBootTest
@WebMvcTest(SquadController.class)
public class SquadControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SquadServiceImpl squadService;

    @Mock
    private SquadRepository squadRepository;

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    //private SquadServiceImpl squadService;
    private StudentServiceImpl studentService;

    private SquadController squadController;

    private String asJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    Squad updatedSquad = Squad.builder()
            .id(1L)
            .squadName("TestSquad")
            //.students(new ArrayList<Student>(Collections.singletonList(student)))
            .students(null)
            .build();

    Student student = Student.builder()
            .Id(1L)
            .name("TestStudent")
            .email("testStudent@gmail.com")
            .city("TestCity")
            .password("TestPassword1234@#")
            .squad(updatedSquad)
            .college("UFES")
            .attendance(9f)
            .grade(9f)
            .startDate(null)
            .endDate(null)
            .build();

    @BeforeEach
    public void setUp(){
        studentService = new StudentServiceImpl(studentRepository);
        squadService = new SquadServiceImpl(squadRepository, studentRepository);
        squadController = new SquadController(squadService);
        updatedSquad.setStudents(new ArrayList<Student>(Collections.singletonList(student)));
    }


    @Test
    public void SquadController_UpdateClass_ReturnSucces() {

        Long id = 1L;

        Squad existingSquad = new Squad();
        existingSquad.setId(id);

        when(squadService.getSquadById(id)).thenReturn(Optional.of(existingSquad));



        when(squadService.saveSquad(any(Squad.class))).thenReturn(updatedSquad);

        ResponseEntity<?> responseEntity = squadController.updateSquad(updatedSquad);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        ErrorResponse errorResponse = (ErrorResponse) responseEntity.getBody();
        Assertions.assertNotNull(errorResponse);
        assertEquals("Squad atualizada com sucesso!", errorResponse.getMessage());
    }


/*
    @Test
    public void testAddSquad() {

        when(squadService.saveSquad(any(Squad.class))).thenReturn(updatedSquad);
        when(studentRepository.save(any(Student.class))).thenReturn(student);
        when(studentService.saveStudent(any(Student.class))).thenReturn(student);

        when(studentRepository.save(any(Student.class))).thenAnswer(invocation -> {
            Student student = invocation.getArgument(0);
            student.setId(1L);
            return student;
        });

        studentService.saveStudent(student);

        List<Long> studentIds = new ArrayList<>();
        studentIds.add(1L);

        SquadDTO squadDTO = SquadDTO.builder()
                .squadName("TestSquad")
                .students(studentIds).build();


        ResponseEntity<?> responseEntity = squadController.createSquad(squadDTO);


        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        Squad savedSquad = (Squad) responseEntity.getBody();
        assertThat(savedSquad).isNotNull();
        assertEquals("TestSquad", savedSquad.getSquadName());
        Assertions.assertNotNull(savedSquad.getStudents());
        assertEquals(new ArrayList<Student>(Collections.singletonList(student)), savedSquad.getStudents());

    }*/
}

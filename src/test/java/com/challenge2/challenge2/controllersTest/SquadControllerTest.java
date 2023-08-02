package com.challenge2.challenge2.controllersTest;

import com.challenge2.challenge2.dto.SquadDTO;
import com.challenge2.challenge2.entities.Classes;
import com.challenge2.challenge2.entities.ErrorResponse;
import com.challenge2.challenge2.entities.Squad;
import com.challenge2.challenge2.entities.Student;
import com.challenge2.challenge2.repositories.SquadRepository;
import com.challenge2.challenge2.repositories.StudentRepository;
import com.challenge2.challenge2.restControllers.ClassController;
import com.challenge2.challenge2.restControllers.SquadController;
import com.challenge2.challenge2.services.impl.SquadService;
import com.challenge2.challenge2.services.impl.SquadServiceImpl;
import com.challenge2.challenge2.services.impl.StudentServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.cloud.contract.verifier.http.Request.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
    public void setUp() {
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
*/

    @Test
    public void SquadController_GetSquad_ReturnSquadNotFound() {
        Long id = 1L;

        when(squadService.getSquadById(id)).thenReturn(Optional.empty());

        ResponseEntity<?> responseEntity = squadController.getSquadById(id);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());

        ErrorResponse errorResponse = (ErrorResponse) responseEntity.getBody();
        assertNotNull(errorResponse);
        assertEquals("Squad não encontrado", errorResponse.getMessage());
    }

/*
    @Test
    public void SquadController_GetAllSquads_ReturnAllSquads() throws Exception {
        List<Squad> squadList = new ArrayList<>();
        squadList.add(updatedSquad);

        when(squadService.getAllSquads()).thenReturn(squadList);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/squad"))
                .andExpect(status().isOk());
                /*.andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.squadName").value("TestSquad"))
                .andExpect(jsonPath("$.students.length()").value(1))
                .andExpect(jsonPath("$.students[0].name").value("TestStudent"))
                .andExpect(jsonPath("$.students[0].email").value("testStudent@gmail.com"))
                .andExpect(jsonPath("$.students[0].city").value("TestCity"))
                .andExpect(jsonPath("$.students[0].password").value("TestPassword1234@#"))
                .andExpect(jsonPath("$.students[0].college").value("UFES"))
                .andExpect(jsonPath("$.students[0].grade").value(9f))
                .andExpect(jsonPath("$.students[0].attendance").value(9f))
                .andExpect(jsonPath("$.students[0].startDate").value(null))
                .andExpect(jsonPath("$.students[0].endDate").value(null));
    }*/
    @Test
    public void testGetAllSquads_EmptyList() throws Exception {
        List<Squad> squadList = Collections.emptyList();

        when(squadService.getAllSquads()).thenReturn(squadList);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/squad"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Nenhum Squad encontrado"))
                .andExpect(jsonPath("$.httpStatus").value("NOT_FOUND"));
    }

    @Test
    public void SquadController_DeleteSquad_ReturnBadRequest() {
        Long id = 1L;

        when(squadService.getSquadById(id)).thenReturn(Optional.empty());

        ResponseEntity<?> responseEntity = squadController.deleteSquad(id);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

        //squadService not a mock?
        //verify(squadService, never()).deleteSquad(id);
    }

    @Test
    public void SquadController_DeleteSquad_ReturnNoContent() {
        Long id = 1L;


        Squad SquadFound = Squad.builder().build();
        when(squadService.getSquadById(id)).thenReturn(Optional.ofNullable(SquadFound));

        ResponseEntity<?> responseEntity = squadController.deleteSquad(id);


        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());

        //not a mock?
        //verify(squadService, times(1)).deleteSquad(id);
    }

}
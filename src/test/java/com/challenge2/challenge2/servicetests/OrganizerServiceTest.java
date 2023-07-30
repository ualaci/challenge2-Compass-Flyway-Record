package com.challenge2.challenge2.servicetests;

import com.challenge2.challenge2.entities.*;
import com.challenge2.challenge2.entities.Organizer;
import com.challenge2.challenge2.entities.Organizer;
import com.challenge2.challenge2.repositories.ClassRepository;
import com.challenge2.challenge2.repositories.OrganizerRepository;
import com.challenge2.challenge2.services.impl.ClassServiceImpl;
import com.challenge2.challenge2.services.impl.OrganizerServiceImpl;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static com.challenge2.challenge2.enums.OrganizerEnums.Instructor;
import static com.challenge2.challenge2.enums.OrganizerEnums.ScrumMaster;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class OrganizerServiceTest {

    @Mock
    private OrganizerRepository organizerRepository;
    @InjectMocks
    private OrganizerServiceImpl organizerServiceImpl;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateOrganizer(){
        Organizer newOrganizer = new Organizer();
        newOrganizer.setId(1L);
        newOrganizer.setName("Zezinho Scrum Master");
        newOrganizer.setRole(ScrumMaster);


        when(organizerRepository.save(any(Organizer.class))).thenReturn(newOrganizer);


        Organizer savedOrganizer = organizerServiceImpl.saveOrganizer(newOrganizer);

        assertNotNull(savedOrganizer);
        assertNotNull(savedOrganizer.getId());
        assertEquals("Zezinho Scrum Master", savedOrganizer.getName());
        assertEquals(ScrumMaster, savedOrganizer.getRole());
        Mockito.verify(organizerRepository, times(1)).save(any(Organizer.class));
    }

    @Test
    public void testGetOrganizerById() {
        Long organizerId = 1L;
        Organizer mockOrganizer = new Organizer();
        mockOrganizer.setId(organizerId);
        when(organizerRepository.findById(organizerId)).thenReturn(Optional.of(mockOrganizer));

        Optional<Organizer> optionalOrganizer = organizerServiceImpl.getOrganizerById(organizerId);
        assertTrue(optionalOrganizer.isPresent());

        Organizer Organizer = optionalOrganizer.get();
        assertEquals(organizerId, Organizer.getId());
    }

    @Test
    public void testUpdateOrganizer() {
        Long organizerId = 1L;

        Organizer mockOrganizer = new Organizer();
        mockOrganizer.setId(organizerId);
        mockOrganizer.setName("John Doe");
        mockOrganizer.setRole(Instructor);


        when(organizerRepository.findById(organizerId)).thenReturn(Optional.of(mockOrganizer));


        Optional<Organizer> optionalOrganizer = organizerServiceImpl.getOrganizerById(organizerId);
        assertNotNull(optionalOrganizer);


        Organizer organizer = optionalOrganizer.orElseThrow(() -> new IllegalArgumentException("Organizer not found"));


        String updatedName = "Jane Doe";
        organizer.setName(updatedName);


        when(organizerRepository.save(organizer)).thenReturn(organizer);
        Organizer updatedOrganizer = organizerServiceImpl.saveOrganizer(organizer);
        assertNotNull(updatedOrganizer);
        assertEquals(updatedName, updatedOrganizer.getName());
    }

    @Test
    public void testDeleteOrganizer() {
        Long organizerId = 1L;

        Organizer mockOrganizer = new Organizer();
        mockOrganizer.setId(organizerId);
        mockOrganizer.setName("John Doe");

        when(organizerRepository.findById(organizerId)).thenReturn(Optional.of(mockOrganizer));

        Optional<Organizer> optionalOrganizer = organizerServiceImpl.getOrganizerById(organizerId);
        assertTrue(optionalOrganizer.isPresent());

        organizerServiceImpl.deleteOrganizer(organizerId);

        verify(organizerRepository, times(1)).deleteById(organizerId);

        Optional<Organizer> deletedOrganizerOptional = organizerServiceImpl.getOrganizerById(organizerId);
        assertTrue(deletedOrganizerOptional.isPresent());
    }



}

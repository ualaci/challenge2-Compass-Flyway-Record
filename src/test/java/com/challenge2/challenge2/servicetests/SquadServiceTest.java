package com.challenge2.challenge2.servicetests;

import com.challenge2.challenge2.entities.Squad;
import com.challenge2.challenge2.repositories.SquadRepository;
import com.challenge2.challenge2.services.impl.SquadServiceImpl;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest
public class SquadServiceTest {
    @Mock
    private SquadRepository squadRepository;
    @InjectMocks
    private SquadServiceImpl squadServiceImpl;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateSquad() {
        Squad mockSquad = new Squad();
        mockSquad.setSquadId(1L);
        mockSquad.setSquadName("Spring Force");



        when(squadRepository.save(Mockito.any(Squad.class))).thenReturn(mockSquad);


        Squad savedSquad = squadServiceImpl.saveSquad(mockSquad);


        assertNotNull(savedSquad);
        assertNotNull(savedSquad.getSquadId());


        assertEquals("Spring Force", savedSquad.getSquadName());
    }

    @Test
    public void testGetSquadById() {
        Long SquadId = 1L;
        Squad mockSquad = new Squad();
        mockSquad.setSquadId(SquadId);
        when(squadRepository.findById(SquadId)).thenReturn(Optional.of(mockSquad));

        Optional<Squad> optionalSquad = squadServiceImpl.getSquadById(SquadId);
        assertTrue(optionalSquad.isPresent());

        Squad Squad = optionalSquad.get();
        assertEquals(SquadId, Squad.getSquadId());
    }




    @Test
    public void testUpdateSquad() {
        Long SquadId = 1L;

        Squad mockSquad = new Squad();
        mockSquad.setSquadId(SquadId);
        mockSquad.setSquadName("ByteAnt");


        when(squadRepository.findById(SquadId)).thenReturn(Optional.of(mockSquad));


        Optional<Squad> optionalSquad = squadServiceImpl.getSquadById(SquadId);
        assertNotNull(optionalSquad);


        Squad Squad = optionalSquad.orElseThrow(() -> new IllegalArgumentException("Squad not found"));


        String updatedName = "Spring Force";
        Squad.setSquadName(updatedName);


        when(squadRepository.save(Squad)).thenReturn(Squad);
        Squad updatedSquad = squadServiceImpl.saveSquad(Squad);
        assertNotNull(updatedSquad);
        assertEquals(updatedName, updatedSquad.getSquadName());
    }

    @Test
    public void testDeleteSquad() {
        Long SquadId = 1L;

        Squad mockSquad = new Squad();
        mockSquad.setSquadId(SquadId);
        mockSquad.setSquadName("Spring Force");

        when(squadRepository.findById(SquadId)).thenReturn(Optional.of(mockSquad));

        Optional<Squad> optionalSquad = squadServiceImpl.getSquadById(SquadId);
        assertTrue(optionalSquad.isPresent());

        squadServiceImpl.deleteSquad(SquadId);

        verify(squadRepository, times(1)).deleteById(SquadId);

        Optional<Squad> deletedSquadOptional = squadServiceImpl.getSquadById(SquadId);
        assertTrue(deletedSquadOptional.isPresent());
    }

}


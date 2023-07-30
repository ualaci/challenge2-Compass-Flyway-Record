package com.challenge2.challenge2.entitiesTests;

import com.challenge2.challenge2.entities.Classes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ClassesTest {

    private Classes classes;

    @BeforeEach
    public void setUp() {
        classes = new Classes();
    }

    @Test
    public void testId() {
        Long expectedId = 1L;
        classes.setId(expectedId);

        Long actualId = classes.getId();

        assertEquals(expectedId, actualId, "O ID deve ser igual ao esperado.");
    }

    @Test
    public void testLearningPath() {
        String expectedLearningPath = "Java Programming";
        classes.setLearningPath(expectedLearningPath);

        String actualLearningPath = classes.getLearningPath();

        assertEquals(expectedLearningPath, actualLearningPath, "O Learning Path deve ser igual ao esperado.");
    }

    @Test
    public void testSprint() {
        Integer expectedSprint = 3;
        classes.setSprint(expectedSprint);

        Integer actualSprint = classes.getSprint();

        assertEquals(expectedSprint, actualSprint, "A Sprint deve ser igual à esperada.");
    }
}

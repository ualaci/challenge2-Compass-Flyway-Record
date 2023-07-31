package com.challenge2.challenge2.repositoriesTest;

import com.challenge2.challenge2.entities.Classes;
import com.challenge2.challenge2.repositories.ClassRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@ExtendWith(SpringExtension.class)
public class ClassRepositoryTest {

    //arange
    //act
    //assert

    @Autowired
    private ClassRepository classRepository;

    @Test
    public void ClassRepository_Save_ReturnSavedClass(){
        Classes c = Classes.builder()
                .learningPath("Java")
                .sprint(1).build();

        Classes savedClass = classRepository.save(c);

        assertThat(savedClass).isNotNull();
        assertEquals(c, savedClass);
    }



}

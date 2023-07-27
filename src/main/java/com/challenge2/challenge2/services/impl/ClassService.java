package com.challenge2.challenge2.services.impl;

import java.util.List;

import com.challenge2.challenge2.entities.Classes;

public interface ClassService {

    List<Classes> getAllClasses(); 

    Classes getClassById(Long id);
    
    Classes saveClass(Classes classes);

    void deleteClass(Long id);
}

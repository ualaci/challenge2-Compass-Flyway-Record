package com.challenge2.challenge2.services.impl;

import com.challenge2.challenge2.entities.Classes;
import com.challenge2.challenge2.repositories.ClassRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassServiceImpl implements ClassService{
    private ClassRepository classRepository;

    public ClassServiceImpl(ClassRepository classRepository) {

        this.classRepository = classRepository;
    }


    public List<Classes> getAllClasses() {
        return classRepository.findAll();
    }

    public Classes getClassById(Long id) {

        return classRepository.findById(id).orElse(null);
    }

    public Classes saveClass(Classes c) {
        return classRepository.save(c);
    }

    public void deleteClass(Long id) {

        classRepository.deleteById(id);
    }
}

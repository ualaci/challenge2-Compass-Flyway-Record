package com.challenge2.challenge2.restControllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenge2.challenge2.entities.Classes;
import com.challenge2.challenge2.services.impl.ClassServiceImpl;

@RestController
@RequestMapping("/api/classes")
public class ClassController {

    private ClassServiceImpl classService;

    public ClassController(ClassServiceImpl classService){
        this.classService = classService;
    }

    @GetMapping
    public List<Classes> getAllClasses(){
        return classService.getAllClasses();
    }

    @GetMapping("/{id}")
    public Classes getClassById(@PathVariable Long id){
        return classService.getClassById(id);
    }
    
    @PostMapping
    public Classes addClass(@RequestBody Classes classes){
        return classService.saveClass(classes);
    }

    @DeleteMapping("/{id}")
    public void deleteClass(@PathVariable Long id){
       Classes c = classService.getClassById(id);

       if(c != null){
        classService.deleteClass(c.getId());
       }
    }
}

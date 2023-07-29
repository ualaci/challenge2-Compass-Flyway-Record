package com.challenge2.challenge2.restControllers;

 // import com.challenge2.challenge2.dto.StudentDTO;
import com.challenge2.challenge2.entities.ErrorResponse;
import com.challenge2.challenge2.entities.Student;
import com.challenge2.challenge2.services.impl.StudentServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    private StudentServiceImpl studentService;

    public StudentController (StudentServiceImpl studentService){
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<?> getAllStudents() {
        ErrorResponse errorResponse = new ErrorResponse("Nenhum estudante encontrado",
                new Timestamp(System.currentTimeMillis()), HttpStatus.NOT_FOUND.name());
        List<Student> students = studentService.getAllStudents();
        if(students.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(students);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable Long id) {
        Optional <Student> student = studentService.getStudentById(id);
        ErrorResponse errorResponse = new ErrorResponse("Estudante não encontrado",
                new Timestamp(System.currentTimeMillis()), HttpStatus.NOT_FOUND.name());
        if(student.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(student);
        }
    }

   @PostMapping
     public ResponseEntity<?> addStudent(@RequestBody @Valid Student student) {
        Student createdStudent = studentService.saveStudent(student);
        ErrorResponse errorResponse = new ErrorResponse("Não foi possível criar o estudante",
                new Timestamp(System.currentTimeMillis()), HttpStatus.BAD_REQUEST.name());
        if(createdStudent == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
        }
    }

    @DeleteMapping("/{id}")
        public ResponseEntity<?> deleteStudent(@PathVariable Long id){
        ErrorResponse errorResponse = new ErrorResponse("Não foi possível deletar o estudante",
                new Timestamp(System.currentTimeMillis()), HttpStatus.BAD_REQUEST.name());
        return studentService.getStudentById(id)
                .map(student -> {
                    studentService.deleteStudent(id);
                    return new ResponseEntity<>( HttpStatus.NO_CONTENT);
                }).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse));

    }
}

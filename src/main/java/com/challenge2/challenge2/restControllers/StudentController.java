package com.challenge2.challenge2.restControllers;

 // import com.challenge2.challenge2.dto.StudentDTO;
import com.challenge2.challenge2.entities.Student;
import com.challenge2.challenge2.services.impl.StudentServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public Optional<Student> getStudentById(@PathVariable Long id) {

        return studentService.getStudentById(id);
    }

   @PostMapping
     public ResponseEntity addStudent(@RequestBody Student student) {
            Student savedStudent = studentService.saveStudent(student);
            return new ResponseEntity(savedStudent, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
        public ResponseEntity deleteStudent(@PathVariable Long id){
        return studentService.getStudentById(id).map(entidade ->{
            studentService.deleteStudent(entidade.getId());
            return new ResponseEntity( HttpStatus.NO_CONTENT);
        }).orElseGet(() ->
                new ResponseEntity("Lançamento não encontrado na base de dados", HttpStatus.BAD_REQUEST));

    }
}

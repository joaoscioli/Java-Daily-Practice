package org.example.restapiproject;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.restapiproject.service.JwtService;

import java.util.List;

@RestController
@RequestMapping("/student")
@Tag(name = "User Controller", description = "Operations related to users")
public class StudentController {

    private final StudentRepository studentRepository;
    private final JwtService jwtService;

    public StudentController(StudentRepository studentRepository, JwtService jwtService) {
        this.studentRepository = studentRepository;
        this.jwtService = jwtService;
    }

    @PostMapping("/authenticate")
    @Operation(summary = "Authenticate token", description = "Return new token to authenticate")
    public String authenticate(@RequestParam String username) {
        return jwtService.generateToken(username);
    }

    // GET /student
    @GetMapping()
    @Operation(summary = "Get all students", description = "get all students from database")
    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    // POST /student/{id}
    @PostMapping
    @Operation(summary = "Add new student", description = "Add new student to database")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student savedStudent = studentRepository.save(student);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }

    // DELETE /students/{id}
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete student to ID", description = "Delete from student with ID")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

package org.example.restapiproject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.restapiproject.service.JwtService;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentRepository studentRepository;
    private final JwtService jwtService;

    public StudentController(StudentRepository studentRepository, JwtService jwtService) {
        this.studentRepository = studentRepository;
        this.jwtService = jwtService;
    }

    @PostMapping("/authenticate")
    public String authenticate(@RequestParam String username) {
        return jwtService.generateToken(username);
    }

    // GET /student
    @GetMapping()
    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    // POST /student/{id}
    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student savedStudent = studentRepository.save(student);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }

    // DELETE /students/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

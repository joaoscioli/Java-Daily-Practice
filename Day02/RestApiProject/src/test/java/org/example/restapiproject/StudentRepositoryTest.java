package org.example.restapiproject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
public class StudentRepositoryTest {
    @Autowired
    private StudentRepository studentRepository;

    @Test
    void shouldFindAllStudents() {
        Student student = new Student(null, "John Silva", "johnSilva@gmail.com");
        student = studentRepository.save(student);

        Optional<Student> findStudent = studentRepository.findById(student.getId());

        Assertions.assertTrue(findStudent.isPresent());
        Assertions.assertEquals("John Silva", findStudent.get().getName());
    }
}

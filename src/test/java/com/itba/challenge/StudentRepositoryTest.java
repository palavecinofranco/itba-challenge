package com.itba.challenge;

import com.itba.challenge.model.entity.Student;
import com.itba.challenge.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class StudentRepositoryTest {
    @Autowired
    private StudentRepository studentRepository;

    @Test
    void shouldSaveAndFindStudent() {
        Student student = Student.builder()
                .dni("12345678")
                .name("Franco")
                .lastname("Palavecino")
                .email("fp@mail.com")
                .address("Calle 123")
                .build();

        Student savedStudent = studentRepository.save(student);
        Optional<Student> foundStudent = studentRepository.findById(savedStudent.getId());

        assertTrue(foundStudent.isPresent());
        assertEquals("Franco", foundStudent.get().getName());
        assertEquals("12345678", foundStudent.get().getDni());
    }
}

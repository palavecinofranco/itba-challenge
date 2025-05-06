package com.itba.challenge;

import com.itba.challenge.controller.StudentController;
import com.itba.challenge.controller.response.StudentResponse;
import com.itba.challenge.service.StudentService;
import com.itba.challenge.utils.MessageUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(StudentController.class)
public class StudentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;
    @MockBean
    private MessageUtil messageUtil;

    @Test
    void shouldReturnAllStudents() throws Exception {
        when(studentService.getAllStudents())
                .thenReturn(List.of(new StudentResponse("Franco Palavecino", "44451023", "fpalavecino@gmail.com", "San Justo")));

        mockMvc.perform(get("/api/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].fullName").value("Franco Palavecino"));
    }
}

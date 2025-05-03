package com.itba.challenge.dto.mapper;

import com.itba.challenge.controller.response.StudentResponse;
import com.itba.challenge.dto.StudentDTO;
import com.itba.challenge.entity.Student;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    StudentDTO toDto(Student student);
    Student toEntity(StudentDTO studentDTO);
    StudentResponse toResponse(Student student);

}

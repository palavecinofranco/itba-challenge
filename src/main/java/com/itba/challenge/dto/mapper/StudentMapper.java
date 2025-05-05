package com.itba.challenge.dto.mapper;

import com.itba.challenge.controller.response.StudentResponse;
import com.itba.challenge.dto.StudentDTO;
import com.itba.challenge.model.entity.Student;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    StudentDTO toDto(Student student);
    Student toEntity(StudentDTO studentDTO);
    @Mapping(target = "fullName", expression = "java(student.getName() + \" \" + student.getLastname())")
    StudentResponse toResponse(Student student);

}

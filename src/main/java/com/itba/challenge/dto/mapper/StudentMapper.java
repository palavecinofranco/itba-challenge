package com.itba.challenge.dto.mapper;

import com.itba.challenge.controller.request.StudentCreateRequest;
import com.itba.challenge.controller.request.StudentUpdateRequest;
import com.itba.challenge.controller.response.StudentResponse;
import com.itba.challenge.model.entity.Student;
import org.mapstruct.*;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface StudentMapper {

    Student toEntity(StudentCreateRequest studentDTO);
    @Mapping(target = "fullName", expression = "java(student.getName() + \" \" + student.getLastname())")
    StudentResponse toResponse(Student student);
    @Mapping(target = "id", ignore = true)
    void updateStudentFromRequest(StudentUpdateRequest request, @MappingTarget Student student);

}

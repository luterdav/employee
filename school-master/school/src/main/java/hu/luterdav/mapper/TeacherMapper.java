package hu.luterdav.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import hu.luterdav.dto.TeacherDto;
import hu.luterdav.model.Teacher;

@Mapper(componentModel = "spring")
public interface TeacherMapper {

	List<TeacherDto> teachersToDtos(List<Teacher> teachers);

	TeacherDto teacherToDto(Teacher teacher);

	Teacher dtoToTeacher(TeacherDto teacherDto);

	List<Teacher> dtosToTeachers(List<TeacherDto> teacherDtos);

}

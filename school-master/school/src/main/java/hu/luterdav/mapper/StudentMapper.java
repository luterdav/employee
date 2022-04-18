package hu.luterdav.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import hu.luterdav.dto.StudentDto;
import hu.luterdav.model.Student;

@Mapper(componentModel = "spring")
public interface StudentMapper {

	List<StudentDto> studentsToDtos(List<Student> students);

	StudentDto studentToDto(Student student);

	Student dtoToStudent(StudentDto studentDto);

	List<Student> dtosToStudentss(List<StudentDto> studentDtos);

}

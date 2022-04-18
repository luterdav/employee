package hu.luterdav.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import hu.luterdav.dto.CourseDto;
import hu.luterdav.model.Course;

@Mapper(componentModel = "spring")
public interface CourseMapper {

	List<CourseDto> coursesToDtos(List<Course> courses);

	@Mapping(source = "student.id", target = "studentId")
	@Mapping(source = "teacher.id", target = "teacherId")
	CourseDto courseToDto(Course course);

	@Mapping(target = "student", ignore = true)
	@Mapping(target = "teacher", ignore = true)
	Course dtoToCourse(CourseDto courseDto);

	List<Course> dtosToCourses(List<CourseDto> courseDtos);

}

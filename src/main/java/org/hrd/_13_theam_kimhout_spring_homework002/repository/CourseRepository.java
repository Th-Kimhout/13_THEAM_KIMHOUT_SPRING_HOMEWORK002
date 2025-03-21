package org.hrd._13_theam_kimhout_spring_homework002.repository;

import org.apache.ibatis.annotations.*;
import org.hrd._13_theam_kimhout_spring_homework002.model.Course;
import org.hrd._13_theam_kimhout_spring_homework002.model.request.CourseRequest;

import java.util.List;

@Mapper
public interface CourseRepository {
    @Select("select * from courses offset #{page} limit #{size} ")
    @Results(id = "CourseMapper", value = {
            @Result(property = "courseId", column = "course_id"),
            @Result(property = "courseName", column = "course_name"),
            @Result(property = "instructor", column = "instructor_id",
            one = @One(select = "org.hrd._13_theam_kimhout_spring_homework002.repository.InstructorRepository.findInstructorById"))}
    )
    List<Course> findAllCoursesPagination(Integer page, Integer size);

    @Select("select * from courses")
    @ResultMap("CourseMapper")
     List<Course> findAllCourses();


    @Select("select * from courses where course_id = #{courseId}")
    @ResultMap("CourseMapper")
     Course findCourseById(Integer courseId);

    @Select("insert into courses(course_name, description, instructor_id) values (#{course.courseName}, #{course.description}, #{course.instructorId}) returning *")
    @ResultMap("CourseMapper")
     Course insertCourse(@Param("course") CourseRequest courseRequest);

    @Select("update courses set course_name= #{course.courseName}, description= #{course.description}, instructor_id=  #{course.instructorId} where course_id = #{id}")
    @ResultMap("CourseMapper")
     Course updateCourseById( Integer id,@Param("course") CourseRequest courseRequest);

    @Delete("delete from courses where course_id = #{id}")
    void deleteCourseById(Integer id);

    @Select("select student_id from student_course where course_id = #{id} ")
    List<Integer> getStudentIdByCourseId(Integer id);
}

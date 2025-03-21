package org.hrd._13_theam_kimhout_spring_homework002.repository;

import org.apache.ibatis.annotations.*;
import org.hrd._13_theam_kimhout_spring_homework002.model.Course;
import org.hrd._13_theam_kimhout_spring_homework002.model.Student;
import org.hrd._13_theam_kimhout_spring_homework002.model.request.StudentRequest;

import java.util.List;
@Mapper
public interface StudentRepository {
    @Select("select * from students offset #{page} limit #{size}")
    @Results(id = "StudentMapper", value = {
            @Result(property = "studentId", column = "student_id"),
            @Result(property = "studentName", column = "student_name"),
            @Result(property = "phoneNumber", column = "phone_number"),
            @Result(property = "courseList", column = "student_id",
                    many = @Many(select ="findCoursesByStudentId"))
    })
    List<Student> findAllStudents(Integer page, Integer size);

    @Select("select * from students where student_id = #{studentId}")
    @ResultMap("StudentMapper")
    Student findStudentById(Integer studentId);

    @Select("insert into students(student_name, email, phone_number) values (#{student.studentName}, #{student.email}, #{student.phoneNumber}) returning *")
    @ResultMap("StudentMapper")
    Student insertStudent(@Param("student") StudentRequest studentRequest);

    @Select("""
            update students set
                                student_name= #{student.studentName},
                                email= #{student.email},
                                phone_number= #{student.phoneNumber}
                            where
                                student_id = #{id} returning *
            """)
    @ResultMap("StudentMapper")
    Student updateStudentById( Integer id,@Param("student") StudentRequest studentRequest);

    @Delete("delete from students where student_id = #{id}")
    void deleteStudentById(Integer id);

    //Delete all student course and insert new
    @Delete("delete from student_course where student_id = #{studentId}")
    void deleteAllStudentCourses(Integer studentId);

    //Insert Into student_course
    @Insert("insert into student_course(student_id, course_id) values (#{studentId},#{courseId})")
     void insertStudentCourse(Integer studentId,Integer courseId);

    //Get All Course By Student ID

    @Select("""
            select * from courses c
                inner join student_course
                    sc on c.course_id = sc.course_id
            where sc.student_id = #{studentId}
            """)
    @Results(id = "StudentCourseMapper", value = {
            @Result(property = "courseId", column = "course_id"),
            @Result(property = "courseName", column = "course_name"),
            @Result(property = "instructor", column = "instructor_id",
                    one = @One(select = "org.hrd._13_theam_kimhout_spring_homework002.repository.InstructorRepository.findInstructorById"))})
     List<Course> findCoursesByStudentId(Integer studentId);
}

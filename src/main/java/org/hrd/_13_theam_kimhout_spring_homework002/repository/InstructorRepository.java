package org.hrd._13_theam_kimhout_spring_homework002.repository;

import org.apache.ibatis.annotations.*;
import org.hrd._13_theam_kimhout_spring_homework002.model.Instructor;
import org.hrd._13_theam_kimhout_spring_homework002.model.request.InstructorRequest;

import java.util.List;

@Mapper
public interface InstructorRepository {

    @Select("select * from instructors offset #{page} limit #{size}")
    @Results(id = "InstructorMapper", value = {
            @Result(property = "instructorId", column = "instructor_id"),
            @Result(property = "instructorName", column = "instructor_name")}
    )
     List<Instructor> findAllInstructors(Integer page, Integer size);

    @Select("select * from instructors where instructor_id = #{instructorId}")
    @ResultMap("InstructorMapper")
     Instructor findInstructorById(Integer instructorId);

    @Select("insert into instructors(instructor_name, email) values (#{instructor.instructorName}, #{instructor.email}) returning *")
    @ResultMap("InstructorMapper")
     Instructor insertInstructor(@Param("instructor") InstructorRequest instructorRequest);

    @Select("update instructors set instructor_name= #{instructor.instructorName}, email= #{instructor.email} where instructor_id = #{id} returning *")
    @ResultMap("InstructorMapper")
     Instructor updateInstructorById(Integer id,@Param("instructor") InstructorRequest instructorRequest);

    @Delete("delete from instructors where instructor_id = #{id}")
    void deleteInstructorById(Integer id);
}



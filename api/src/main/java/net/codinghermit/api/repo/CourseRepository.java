package net.codinghermit.api.repo;

import org.apache.ibatis.annotations.*;

import net.codinghermit.api.model.Course;

import java.util.List;

@Mapper
public interface CourseRepository {

    @Select("SELECT * FROM courses")
    public List<Course> findAll();

    @Select("SELECT * FROM courses WHERE courseId = #{courseId}")
    public Course findById(long courseId);

    @Delete("DELETE FROM courses WHERE courseId = #{courseId}")
    public int deleteById(long courseId);

    @Insert("INSERT INTO courses(courseId, courseName, id) " +
	    " VALUES (#{courseId}, #{courseName}, #{id})")
    public int insert(Course course);

    @Update("UPDATE courses SET " +
	    " courseName=#{courseName}, id=#{id} WHERE courseId=#{courseId}")
    public int update(Course course);
}

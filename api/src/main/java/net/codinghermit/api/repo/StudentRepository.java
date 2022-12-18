package net.codinghermit.api.repo;

import org.apache.ibatis.annotations.*;

import net.codinghermit.api.model.Student;

import java.util.List;

@Mapper
public interface StudentRepository {

    @Select("SELECT * FROM students")
    public List<Student> findAll();

    @Select("SELECT * FROM students WHERE studentId = #{studentId}")
    public Student findById(long studentId);

    @Delete("DELETE FROM students WHERE studentId = #{studentId}")
    public int deleteById(long studentId);

    @Insert("INSERT INTO students(studentId, studentName, emailId, \"role\", \"password\") " +
          " VALUES (#{studentId}, #{studentName}, #{emailId}, #{role}, crypt(#{password}, gen_salt('bf', 8)))")
    public int insert(Student user);

    @Update("UPDATE students SET " +
          " studentName=#{studentName}, emailId=#{emailId}, role=#{role}, password=crypt(#{password}, gen_salt('bf', 8)) WHERE studentId=#{studentId}")
    public int update(Student user);
}

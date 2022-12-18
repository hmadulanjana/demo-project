package net.codinghermit.api.repo;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
// import org.apache.ibatis.annotations.Update;

import net.codinghermit.api.model.Enrol;

@Mapper
public interface EnrolRepository {

	@Select("SELECT * FROM enrollments")
	public List<Enrol> findAll();

	@Select("SELECT * FROM enrollments WHERE courseId=#{courseId} AND studentId=#{studentId}")
	public Enrol findById(long courseId, long studentId);

	// @Select("SELECT * FROM enrollments WHERE (courseId, studentId) IN (#{courseId}, #{studentId})")
	// public Enrol findById(long courseId, long studentId);

	@Select("SELECT * FROM enrollments WHERE studentId = #{studentId}")
	public Enrol findByStudentId(long studentId);

	@Select("SELECT * FROM enrollments WHERE courseId = #{courseId}")
	public Enrol findByCourseId(long courseId);

	@Delete("DELETE FROM enrollments WHERE courseId = #{courseId}")
	public int deleteById(long courseId);

	@Insert("INSERT INTO enrollments(courseId, studentId) " +
		" VALUES (#{courseId}, #{studentId})")
	public int insert(Enrol enrol);

	// SELECT * FROM enrollments WHERE courseId=1 AND studentId=1;

	// @Update("UPDATE enrollments SET " +
	// 	" courseId=#{courseId}, studentId=#{studentId} WHERE (courseId, studentId) IN (#{courseId}, #{studentId})")
	// public int update(Enrol enrol);
}
// (key_part_1, key_part_2) IN ( ('B',1), ('C',2) );
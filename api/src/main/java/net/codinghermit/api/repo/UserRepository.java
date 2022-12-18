package net.codinghermit.api.repo;

import org.apache.ibatis.annotations.*;

import net.codinghermit.api.model.User;

import java.util.List;

@Mapper
public interface UserRepository {

    @Select("SELECT * FROM users")
    public List<User> findAll();

    @Select("SELECT * FROM users WHERE id = #{id}")
    public User findById(long id);

    @Delete("DELETE FROM users WHERE id = #{id}")
    public int deleteById(long id);

    @Insert("INSERT INTO users(id, userName, emailId, \"role\", \"password\") " +
    		" VALUES (#{id}, #{userName}, #{emailId}, #{role}, crypt(#{password}, gen_salt('bf', 8)))")
    public int insert(User user);

    @Update("UPDATE users SET " +
		" userName=#{userName}, emailId=#{emailId}, role=#{role}, password=crypt(#{password}, gen_salt('bf', 8)) where id=#{id}")
    public int update(User user);
}

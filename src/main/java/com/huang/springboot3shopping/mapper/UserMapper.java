package com.huang.springboot3shopping.mapper;

import com.huang.springboot3shopping.bean.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @BelongsProject: spring-boot-3
 * @BelongsPackage: com.huang.springboot3shopping.mapper
 * @Author: HuangJinTao
 * @CreateTime: 2023-07-03  13:32
 * @Description: TODO
 * @Version: 1.0
 */
public interface UserMapper {
    public User getUserByName(@Param("username") String username);
    public int setUser(@Param("username") String username, @Param("password") String password,@Param("roleId") int roleId);
    public List<User> getUsers();
    public int updateUser(User user);
    public int deleteUser(@Param("id") int id);
    public User selectUserById(@Param("id") int id);
    public List<User> selectUser(@Param("username") String username, @Param("password") String password, @Param("roleId") String roleId);
}

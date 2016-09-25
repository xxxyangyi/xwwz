package com.hand.service;

import com.hand.entity.User;

import java.util.List;

/**
 * Created by tuberose on 16-9-20.
 */
public interface IUserService {
    /*
    *	创建用户
     */
    public void createUser(User user) throws Exception;

    /*
    *	删除用户
     */
    public void deleteUser(User user) throws Exception;

    /*
    *	更新用户信息
     */
    public void updateUser(User user) throws Exception;

    /*
    *	通过SQL 查找 用户 返回用户列表
    */
    public List<User> FindBySQL(String sql) throws Exception;

    /*
    *	通过user_id 查找 用户 返回用户列表
    */
    public User FindByID(int userId) throws Exception;
}

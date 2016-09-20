package com.hand.service.impl;

import java.util.List;

import com.hand.dao.IUserDao;
import com.hand.entity.User;
import com.hand.service.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Transactional
@Service("userService")
public class UserService implements IUserService {

    @Resource(name = "userDao")
    private IUserDao userDao;

    @Override
    public void createUser(User user) throws Exception {
        userDao.Create(user);
    }

    @Override
    public void deleteUser(User user) throws Exception {
        userDao.Delete(user);
    }

    @Override
    public void updateUser(User user) throws Exception {
        userDao.Update(user);
    }

	
	
}

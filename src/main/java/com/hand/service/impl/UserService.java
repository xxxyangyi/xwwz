package com.hand.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("userService")
public class UserService   {

//	@Resource(name = "userDao")
//	private IUserDao userDao;
//
//	@Override
//	public void AddUser(User user) {
//		userDao.Create(user);
//	}
//
//	@Override
//	public void UpdateUser(User user) {
//		// TODO Auto-generated method stub
//		userDao.Update(user);
//	}
//
//	@Override
//	public User GetUser(String mail) {
//		return userDao.FindOne(mail);
//	}
//
//	@Override
//	public boolean IsUserExisted(String mail, String password) {
//		// TODO Auto-generated method stub
//		try {
//			User user = GetUser(mail);
//
//			if (user != null && user.getPassword().equals(password))
//				return true;
//			return false;
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			return false;
//		}
//	}
//
//	@Override
//	public Integer GetTotal(String sqlSum, Integer numPage) {
//		// TODO Auto-generated method stub
//		Integer sum = userDao.GetSum(sqlSum);
//		return userDao.GetTotal(sum, numPage);
//	}
//
//	@Override
//	public List<User> GetList(String sql, Integer page, Integer numPage,
//			Integer total) {
//		// TODO Auto-generated method stub
//		Integer pre = userDao.GetPre(page, total, numPage);
//
//		return userDao.FindList(sql, pre, numPage);
//	}
//
//	@Override
//	public void DeleteUser(User user) {
//		// TODO Auto-generated method stub
//		userDao.Delete(user);
//	}
//
//	@Override
//	public Integer getManSum() {
//		// TODO Auto-generated method stub
//		return userDao.getManSum();
//	}
//
//	@Override
//	public Integer getWomenSum() {
//		// TODO Auto-generated method stub
//		return userDao.getWomenSum();
//	}
//
//	@Override
//	public Integer getUserSum() {
//		// TODO Auto-generated method stub
//		return userDao.getUserSum();
//	}
//
//	@Override
//	public Integer getExpertSum() {
//		// TODO Auto-generated method stub
//		return userDao.getExpertSum();
//	}
	
	
}

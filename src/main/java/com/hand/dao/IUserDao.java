package com.hand.dao;

import com.hand.dao.common.IOperation;
import com.hand.entity.User;

public interface IUserDao extends IOperation<User> {
	/***
	 * 获取男性用户数量
	 * @return
	 */
	public Integer getManSum();
	
	/***
	 * 获取女性用户数量
	 * @return
	 */
	public Integer getWomenSum();
	/***
	 * 获取游客数量
	 * @return 数量
	 */
	public Integer getUserSum();
	
	/***
	 * 获取专家数量
	 * @return 数量
	 */
	public Integer getExpertSum();
}

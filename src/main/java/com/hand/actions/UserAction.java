package com.hand.actions;

import com.hand.entity.User;
import com.hand.service.IUserService;

import javax.annotation.Resource;

public class UserAction extends BaseAction {

	@Resource(name = "userService")
	private IUserService userService;

	public void index() throws Exception {
		System.out.print("---》index 方法");
		User user = new User();
		user.setAccountName("yang");

		userService.createUser(user);
	}
//
//	public String DoResister() {
//		try {
//			String mail = request.getParameter("mail");
//			String password = request.getParameter("password");
//			String name = request.getParameter("name");
//			Integer identity = Integer.parseInt(request.getParameter("identity"));
//			Integer sex = Integer.parseInt(request.getParameter("sex"));
//
//			User user = new User();
//			user.setMail(mail);
//			user.setName(name);
//			user.setPassword(password);
//			user.setIdentity(identity);
//			user.setSex(sex);
//			user.setIsUsed(1);
//
//			userService.AddUser(user);
//
//			Map session=ActionContext.getContext().getSession();
//			session.put("user",user);
//
//			return "success";
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			return "failed";
//		}
//	}
//
//	// 登陆
//	public void DoLogin() throws Exception{
//		PrintWriter out = response.getWriter();
//		String mail = request.getParameter("mail");
//		String password = request.getParameter("password");
//		System.out.println("登陆");
//		if (userService.IsUserExisted(mail, password)) {
//			User user = userService.GetUser(mail);
//			if (user.getIsUsed() == 0)
//				out.print(-1);
//			else {
//				session.put("user", user);
//				if (((User)session.put("user", user)).getIdentity() == 0) {
//					out.print(URL_PERFIX+"Manager/Index");
//				} else if (user.getIdentity() == 1) {
//					out.print(URL_PERFIX+"Home/Index");
//				} else {
//					out.print(URL_PERFIX+"jsp/view/expert/index.jsp");
//				}
//			}
//		} else {
//			out.print(0);
//		}
//
//	}
//
//	public void DoLogOut(){
//		try{
//			Map session=ActionContext.getContext().getSession();
//			session.remove("user");
//			HttpServletResponse response=ServletActionContext.getResponse();
//			PrintWriter out = response.getWriter();
//			out.print("1");
//		}
//		catch(Exception ex){
//			ex.printStackTrace();
//		}
//	}
}
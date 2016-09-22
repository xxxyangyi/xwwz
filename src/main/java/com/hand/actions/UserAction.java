package com.hand.actions;

import com.hand.entity.User;
import com.hand.service.IUserService;

import javax.annotation.Resource;
import javax.naming.directory.InitialDirContext;
import java.io.PrintWriter;

public class UserAction extends BaseAction {

    @Resource(name = "userService")
    private IUserService userService;

    public void createUser() throws Exception {
        User user = new User();
        response.setContentType("text/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        System.out.println("---》index 方法");
        String accountName = request.getParameter("accountName");
        String nickName = request.getParameter("nickName");
        String realName = request.getParameter("realName");
        String age = request.getParameter("age");
        String sex = request.getParameter("sex");
        String IDcard = request.getParameter("IDcard");
        String password = request.getParameter("password");
        String identity = request.getParameter("identity");
        if (accountName == null
                || nickName == null
                || realName == null
                || age == null
                || sex == null
                || IDcard == null
                || password == null
                || identity == null)
        {
            System.out.println("由于参数导致创建失败");
            out.print(0);
            return;
        }

        user.setAccountName(accountName);
        user.setNickName(nickName);
        user.setRealName(realName);
        user.setAge(Integer.parseInt(age));
        user.setSex(Integer.parseInt(sex));
        user.setIDcard(IDcard);
        user.setPassword(password);
        user.setIdentity(Integer.parseInt(identity));

        try {
            userService.createUser(user);
        } catch (Exception e) {
            System.out.println("深层调用导致创建失败");
            out.print(0);
            e.printStackTrace();
            return;
        }
        System.out.println("创建成功");
        out.print(1);   // 创建成功
    }


    public void updateUser() throws Exception {
        System.out.print("---》updateUser 方法");
        // 更新user 信息

    }

    public void deleteUser() throws Exception {
        System.out.print("---》deleteUser 方法");
        // 更新user 信息

    }

    public void pageingUser() throws Exception {
        System.out.print("---》pageingUser 方法");

        // 更新user 信息

    }

    public void login() {
        System.out.print("---》login 方法");
        // 用户登陆
    }

    public void logout() {
        System.out.print("---》logout 方法");
        // 用户退出
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
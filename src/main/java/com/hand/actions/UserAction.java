package com.hand.actions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hand.entity.User;
import com.hand.paging.Pager;
import com.hand.paging.PagingService;
import com.hand.service.IUserService;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import javax.annotation.Resource;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static com.hand.commonKey.CommonKey.PAGESIZE;

public class UserAction extends BaseAction {

    @Resource(name = "userService")
    private IUserService userService;

    @Resource(name = "pagingService")
    private PagingService<User> pagingUserService;

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
        // 校验用户名是否重复
        String sql = "SELECT * FROM user WHERE accountName='"+accountName+"';";
        List<User> userList = new ArrayList<>();
        try {
            userList = userService.FindBySQL(sql);
        } catch (Exception e) {
            System.out.println("深层调用导致登陆失败");
            out.print(0);
            e.printStackTrace();
            return;
        }
        if(userList.size()!=0){
            System.out.println("用户名重复");
            out.print(2);
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
        if(Integer.parseInt(identity) == 2){ //判断是否是记者
            user.setReviewed(0);// 记者会设置权限
        }else {
            user.setReviewed(1);//非记者不设置权限
        }
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
        User user = new User();
        response.setContentType("text/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        System.out.println("---》index 方法");
        String id          = request.getParameter("id");
        if(id==null){
            System.out.print("没有userID 无法进行更新");
            return;
        }
        String accountName = request.getParameter("accountName");
        String nickName = request.getParameter("nickName");
        String realName = request.getParameter("realName");
        String age = request.getParameter("age");
        String sex = request.getParameter("sex");
        String IDcard = request.getParameter("IDcard");
        String password = request.getParameter("password");
        String identity = request.getParameter("identity");
        String reviewed = request.getParameter("reviewed");
        user.setId(Integer.parseInt(id));
        if(accountName  !=null)user.setAccountName(accountName);
        if(nickName     !=null)user.setNickName(nickName);
        if(realName     !=null)user.setRealName(realName);
        if(age          !=null)user.setAge(Integer.parseInt(age));
        if(sex          !=null)user.setSex(Integer.parseInt(sex));
        if(IDcard       !=null)user.setIDcard(IDcard);
        if(password     !=null)user.setPassword(password);
        if(identity     !=null)user.setIdentity(Integer.parseInt(identity));
        if(reviewed     !=null)user.setReviewed(Integer.parseInt(reviewed));

        try {
            userService.updateUser(user);
        } catch (Exception e) {
            System.out.println("深层调用导致更新失败");
            out.print(0);
            e.printStackTrace();
            return;
        }
        System.out.println("更新成功");
        out.print(1);   // 更新成功

    }

    public void deleteUser() throws Exception {
        // 删除 User
        System.out.print("---》deleteUser 方法");
        User user = new User();
        response.setContentType("text/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String id = request.getParameter("id");
        if(id==null){
            System.out.print("没有userID 无法进行删除");
            out.print(0);
            return;
        }
        user.setId(Integer.parseInt(id));
        try {
            userService.deleteUser(user);
        } catch (Exception e) {
            System.out.println("深层调用导致删除失败");
            out.print(0);
            e.printStackTrace();
            return;
        }
        System.out.println("更新成功");
        out.print(1);   // 更新成功


    }

    public void pageingUser() throws Exception {
        System.out.print("---》pageingUser 方法");
        // user 信息 的分页查询
        int pageNo = Integer.parseInt(request.getParameter("PageNo"));
        String identity = request.getParameter("identity");
        Criterion criterion = null;
        if(identity!=null)criterion =  Restrictions.eq("identity",Integer.parseInt(identity));

        pagingUserService.PagingService(User.class);
        Pager pager = pagingUserService.paging(pageNo,PAGESIZE,criterion);

        response.setContentType("text/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        System.out.println("数据："+pager.toString());
        for (Object user: pager.getResult()){
            System.out.println("user --->：  "+((User)user).toString());
        }
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        out.print(gson.toJson(pager));
        System.out.println("发送数据=="+gson.toJson(pager));

    }

    public void login() throws Exception {
        System.out.println("---》login 方法");
        // 用户登陆
        response.setContentType("text/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out    = response.getWriter();
        String accountName = request.getParameter("accountName");
        String password    = request.getParameter("password");
        if(accountName == null){
            System.out.print("accountName 缺失无法进行登陆");
            out.print("accountName 缺失无法进行登陆");
            return;
        }
        if(accountName == null){
            System.out.print("password 缺失无法进行登陆");
            out.print("password 缺失无法进行登陆");
            return;
        }
        String sql = "SELECT * FROM user WHERE accountName='"+accountName+"' AND password = '"+password+"';";
        List<User> userList = new ArrayList<>();
        try {
            userList = userService.FindBySQL(sql);
        } catch (Exception e) {
            System.out.println("深层调用导致登陆失败");
            out.print(0);
            e.printStackTrace();
            return;
        }
        if(userList.size() == 1){
            System.out.println("登陆成功");
            session.put("user",userList.get(0));
            if(userList.get(0).getReviewed()==null || userList.get(0).getReviewed()==0){
                System.out.println("未通过审核");
                out.print(5); // 未通过审核
                return;
            }
            out.print(userList.get(0).getIdentity()); // 登陆成功
        }else {
            System.out.println("登陆失败\n可能原因:\n1.用户名错误\n2.密码错误\n3.有相同用户名和密码的用户");
            out.print(0); // 登陆失败
        }
    }

    public void logout() throws Exception{
        System.out.println("---》logout 方法");
        // 用户退出
        response.setContentType("text/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        if(session.get("user")==null){
            System.out.println("您还没有进行登陆");
            out.println(0);// 退出登陆成功
            return;
        }
        session.remove("user");
        System.out.println("退出登陆成功");
        out.print('1');// 退出登陆成功
    }

    public void isLogin() throws Exception{
        System.out.println("---》isLogin 方法   判断用户是否登陆");
        // 判断用户是否登陆
        response.setContentType("text/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        if(session.get("user")==null){
            System.out.println("用户没有进行登陆");
            out.print(""); // 用户没有进行登陆
        }else{
            System.out.println("用户已经登陆");
            Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
            out.print(gson.toJson((User)session.get("user")));
        }
    }

}
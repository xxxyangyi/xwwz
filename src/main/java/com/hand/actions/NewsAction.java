package com.hand.actions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hand.entity.Category;
import com.hand.entity.News;
import com.hand.entity.User;
import com.hand.paging.Pager;
import com.hand.paging.PagingService;
import com.hand.service.ICategoryService;
import com.hand.service.INewsService;
import com.hand.service.IUserService;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import javax.annotation.Resource;
import java.io.PrintWriter;
import java.util.*;

import static com.hand.commonKey.CommonKey.PAGESIZE;

public class NewsAction extends BaseAction {

    @Resource(name = "newsService")
    private INewsService newsService;

    @Resource(name = "categoryService")
    private ICategoryService categoryService;

    @Resource(name = "userService")
    private IUserService userService;

    @Resource(name = "pagingService")
    private PagingService<News> pagingNewsService;

    // 创建 新闻
    public void createNews() throws Exception {
        response.setContentType("text/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        System.out.println("---》createNews 方法");
        if(session.get("user")==null || ((User)session.get("user")).getIdentity() != 2){
            System.out.println("您还没有登陆，请先登陆--------ps: 只有记者才能创建新闻！ ");
            out.print(0);
            return;
        }
        News news = new News();
        String title = request.getParameter("title");
        String context = request.getParameter("context");
        String category_id = request.getParameter("category_id");
        if (title == null || context == null || category_id == null)
        {
            System.out.println("由于参数导致创建新闻失败");
            out.print(0);
            return;
        }
        news.setTitle(title);
        news.setContext(context);
        news.setUser_id((User)session.get("user"));
        Set<Category> categorySet = getCategorieSet(category_id);
        news.setCategory(categorySet);
        news.setCreateTime(new Date());
        news.setReviewed(0);
        try {
            newsService.createNews(news);
        } catch (Exception e) {
            System.out.println("深层调用导致创建失败");
            out.print(0);
            e.printStackTrace();
            return;
        }
        System.out.println("创建新闻成功");
        out.print(1);   // 创建成功
    }

    // 修改新闻
    public void updateNews() throws Exception {
        response.setContentType("text/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        System.out.println("---》updateNews 方法");
        if(session.get("user")==null || ((User)session.get("user")).getIdentity() != 2){
            System.out.println("您还没有登陆，请先登陆--------ps: 只有记者才能创建新闻！ ");
            out.print(0);
            return;
        }
        String id = request.getParameter("id");
        String title = request.getParameter("title");
        String context = request.getParameter("context");
        String category_id = request.getParameter("category_id");
        String reviewed = request.getParameter("reviewed");
        if (id == null)
        {
            System.out.println("由于没有新闻的 ID 导致更新新闻失败");
            out.print(0);
            return;
        }
        News news = newsService.FindByID(Integer.parseInt(id));
        if(news == null){
            System.out.println("没有此条新闻记录");
            out.println("0");
            return;
        }
        if(title!=null)news.setTitle(title);
        if(context!=null)news.setContext(context);
        if(reviewed!=null)news.setReviewed(Integer.parseInt(reviewed));

        if(category_id!=null){
            Set<Category> categorySet = getCategorieSet(category_id);
            news.setCategory(categorySet);
        }
        try {
            newsService.updateNews(news);
        } catch (Exception e) {
            System.out.println("深层调用导致更新失败");
            out.print(0);
            e.printStackTrace();
            return;
        }
        System.out.println("更新新闻成功");
        out.print(1);   // 更新新闻成功
    }


    // 删除新闻
    public void deleteNews() throws Exception {
        response.setContentType("text/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        System.out.println("---》deleteNews 方法");
        if(session.get("user")==null || ((User)session.get("user")).getIdentity() != 2){
            System.out.println("您还没有登陆，请先登陆--------ps: 只有记者才能创建新闻！ ");
            out.print(0);
            return;
        }
        String id = request.getParameter("id");
        if (id == null)
        {
            System.out.println("由于没有新闻的 ID 导致 更新新闻失败");
            out.print(0);
            return;
        }

        try {
//            newsService.deleteNews(newsService.FindByID(Integer.parseInt(id)));
            newsService.deleteNews(new News(Integer.parseInt(id)));
        }catch (Exception e){
            System.out.println("深层调用导致删除新闻失败");
            out.print(0);
            e.printStackTrace();
            return;
        }
        System.out.println("删除新闻成功");
        out.print(1); //删除成功
    }
    public void selectByNewsID() throws Exception {
        response.setContentType("text/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        System.out.println("---》selectByNewsID 方法");
        String news_id = request.getParameter("news_id");
        String[] join = new String[]{"user_id"};
        Criterion criterion1 = Restrictions.eq("id",Integer.parseInt(news_id));
        pagingNewsService.PagingService(News.class);
        Pager pager = pagingNewsService.paging(1,PAGESIZE,join,null,null,criterion1);
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        out.print(gson.toJson(pager));
        System.out.print("selectByNewsID 成功");
    }


    // 根据 类别、用户、分页查询新闻
    public void pagingByCategoryNews() throws Exception {
        response.setContentType("text/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        System.out.println("---》pagingByCategoryNews 方法");

        int pageNo = Integer.parseInt(request.getParameter("PageNo"));
        String user_ids = request.getParameter("user_ids");
        String category_ids = request.getParameter("category_ids");
        String reviewed = request.getParameter("reviewed");

        Criterion criterion1 = null;
        Criterion criterion2 = null;
        Criterion criterion3 = null;
        pagingNewsService.PagingService(News.class);

        Pager pager = null;
        if(user_ids!=null){
            criterion1 = Restrictions.eq("user_id",userService.FindByID(Integer.parseInt(user_ids)));
        }

        if(reviewed!=null){
            criterion2 = Restrictions.eq("reviewed",Integer.parseInt(reviewed));
        }

        if(category_ids!=null) {
            criterion3 = Restrictions.in("id",getNewsId(category_ids));
        }

        pager = pagingNewsService.paging(pageNo,PAGESIZE,new String[]{"user_id","category"},null,null,criterion1,criterion2,criterion3);

        response.setContentType("text/json");
        response.setCharacterEncoding("UTF-8");
        System.out.println("数据："+pager.toString());
        for (Object news: pager.getResult()){
            System.out.println("user --->：  "+((News)news).toString());
        }
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        out.print(gson.toJson(pager));
        System.out.println("发送数据=="+gson.toJson(pager));

    }

/////////////////////////////通用方法//////////////////////////////////////////////////////////////////////////////////////

    // 获取 类别列表 的通用方法
    public Set<Category> getCategorieSet (String category_id) throws Exception{
        String[] category_ids =category_id.split("_");
        Set<Category> categorySet = new HashSet<>();
        for(String str : category_ids){
            Category category = categoryService.FindByID(Integer.parseInt(str));
            if(category==null){
                System.out.println("查无此类别");
            }
            categorySet.add(category);
        }
        return categorySet;
    }
    // 根据类别获取新闻id 的通用方法
    public Set<Integer> getNewsId (String category_id) throws Exception{
        String[] category_ids =category_id.split("_");
        Set<Integer> newsIdSet = new HashSet<>();
        for(String str : category_ids){
            Criterion criterion = Restrictions.eq("id",Integer.parseInt(str));
            List <Category> categoryList= categoryService.findByCriteria(new String[]{"news"},criterion);
            for (Category ca : categoryList){
                for(News news:ca.getNews()){
                    newsIdSet.add(news.getId());
                }
            }
        }
        if(newsIdSet.isEmpty()){
            newsIdSet.add(-1);
        }
        return newsIdSet;
    }

    // 获取 用户列表 的通用方法
    public Set<User> getUserSet (String user_id) throws Exception{
        String[] user_ids =user_id.split("_");
        Set<User> userSet = new HashSet<>();
        for(String str : user_ids){
            User user = userService.FindByID(Integer.parseInt(str));
            if(user==null){
                System.out.println("查无此类别");
            }else{
                userSet.add(user);
            }
        }
        return userSet;
    }
}
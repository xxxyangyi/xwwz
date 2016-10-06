package com.hand.actions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hand.entity.Category;
import com.hand.paging.Pager;
import com.hand.paging.PagingService;
import com.hand.service.ICategoryService;
import javax.annotation.Resource;
import java.io.PrintWriter;

import static com.hand.commonKey.CommonKey.PAGESIZE;

public class CategoryAction extends BaseAction {

    @Resource(name = "pagingService")
    private PagingService<Category> pagingCategoryService;

    @Resource(name = "categoryService")
    private ICategoryService categoryService;

    // 添加 新闻种类
    public void createCategory() throws Exception {
        Category category = new Category();
        response.setContentType("text/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        System.out.println("---》createCategory 方法");
        String categoryName = request.getParameter("categoryName");
        if(categoryName == null){
            System.out.println("由于参数导致创建失败");
            out.print(0);
            return;
        }
        category.setCategoryName(categoryName);

        try {
            categoryService.createCategory(category);
        } catch (Exception e) {
            System.out.println("深层调用导致创建失败");
            out.print(0);
            e.printStackTrace();
            return;
        }
        System.out.println("创建类别成功成功");
        out.print(1);   // 创建成功
    }

    // 修改新闻种类
    public void updateCategory() throws Exception {
        response.setContentType("text/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        System.out.println("---》updateCategory 方法");
        String id           = request.getParameter("category_id");
        String categoryName = request.getParameter("categoryName");

        Category category = categoryService.FindByID(Integer.parseInt(id));
        if(category == null){
            System.out.println("没有此条新闻记录");
            out.println("0");
            return;
        }
        category.setCategoryName(categoryName);

        try {
            categoryService.updateCategory(category);
        } catch (Exception e) {
            System.out.println("深层调用导致更新失败");
            out.print(0);
            e.printStackTrace();
            return;
        }
        System.out.println("更新类别成功成功");
        out.print(1);   // 创建成功
    }

    // 删除新闻种类
    public void deleteCategory() throws Exception {
        response.setContentType("text/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        System.out.println("---》deleteCategory 方法");

        String id = request.getParameter("category_id");
        if (id == null)
        {
            System.out.println("由于没有新闻的 ID 导致 更新新闻失败");
            out.print(0);
            return;
        }

        Category category = categoryService.FindByID(Integer.parseInt(id));
        if(category == null){
            System.out.println("没有此条新闻记录");
            out.println("0");
            return;
        }

        try {
            categoryService.deleteCategory(category);
        }catch (Exception e){
            System.out.println("深层调用导致删除新闻失败");
            out.print(0);
            e.printStackTrace();
            return;
        }
        System.out.println("删除类别成功");
        out.print(1); //删除成功
    }

    // 类别分页查询
    public void pageingCategory() throws Exception {
        System.out.print("---》pageingCategory 方法");
        int pageNo = Integer.parseInt(request.getParameter("PageNo"));
        String pageSize = request.getParameter("PageSize");
        pagingCategoryService.PagingService(Category.class);
        Pager pager = null;
        if(pageSize!=null){
            pager = pagingCategoryService.paging(pageNo,Integer.parseInt(pageSize),null);
        }else {
            pager = pagingCategoryService.paging(pageNo,PAGESIZE,null);
        }
        response.setContentType("text/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        System.out.println("数据："+pager.toString());
        for (Object category: pager.getResult()){
            System.out.println("category --->：  "+((Category)category).toString());
        }
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        out.print(gson.toJson(pager));
        System.out.println("发送数据=="+gson.toJson(pager));

    }

}
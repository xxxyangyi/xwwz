package com.hand.paging;

import com.hand.entity.Category;
import com.hand.entity.News;
import com.hand.entity.User;
import org.apache.commons.collections.FastArrayList;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by tuberose on 16-9-4.
 */
@Repository("pagingDao")
public class PagingDao<T extends Serializable> extends AbstractHibernateDao<T> {

    public PagingDao(Class<T> e){
        super();
        setClazz(e);
    }

    public PagingDao(){}

    /*
    *  通过 user 和  类别限制查询结果
    *
    * */
    public Pager newsList (int pageNo, int pageSize, int categoryID,Criterion... criterions){
        Pager pager = null;
        try {
            Criteria criteria = this.getCurrentSession().createCriteria(News.class);
            Criteria criteria2 = this.getCurrentSession().createCriteria(News.class);
            criteria.setFetchMode("user_id", FetchMode.JOIN);
            criteria.setFetchMode("category", FetchMode.JOIN);
            criteria2.setFetchMode("category", FetchMode.JOIN);
            Persis
            List<Integer> list = new FastArrayList();
            list.add(categoryID);
            criteria.createAlias("category", "c")
                    .add(Restrictions.in("c.id",list));
            criteria2.createAlias("category", "c")
                    .add(Restrictions.in("c.id",list));
            if (criterions != null) {
                for (Criterion criterion : criterions) {
                    if (criterion != null) {
                        criteria.add(criterion);
                        criteria2.add(criterion);
                    }

                }
            }
            criteria.setFirstResult((pageNo - 1) * pageSize);
            criteria.setMaxResults(pageSize);
            List<T> result = (List<T>) criteria.list();
            // 获取根据条件分页查询的总行数
            int rowCount = Integer.parseInt((criteria2.setProjection(Projections.rowCount()).uniqueResult()).toString());
            pager = new Pager(pageSize, pageNo, rowCount, result);
            System.out.println("DAO : 数据安放完成");

        } catch (RuntimeException re) {
            System.out.print("DAO : 出现异常" + re);
            throw re;
        } finally {
            System.out.print("DAO : 打印结果");
            return pager;
        }
    }

}

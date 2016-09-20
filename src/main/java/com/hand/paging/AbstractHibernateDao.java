package com.hand.paging;

import com.hand.entity.User;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by tuberose on 16-9-3.
 */
public class AbstractHibernateDao<T extends Serializable> {

    private Class<T> clazz;

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    protected final Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }


    protected final void setClazz(final Class<T> clazzToSet) {
        this.clazz = clazzToSet;
    }

    /**
     * @param pageNo     当前页数
     * @param pageSize   每页显示的条数
     * @param criterions 不定参数Criterion
     * @return 查询结果Pager
     * @function 分页显示符合所有的记录数，将查询结果封装为Pager
     */
    public Pager findPageByCriteria(int pageNo, int pageSize, Criterion... criterions) {
        Pager pager = null;
        try {
            Criteria criteria = this.getCurrentSession().createCriteria(clazz);
            Criteria criteria2 = this.getCurrentSession().createCriteria(clazz);
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

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

}

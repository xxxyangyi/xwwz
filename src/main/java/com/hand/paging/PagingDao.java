package com.hand.paging;

import org.springframework.stereotype.Repository;
import java.io.Serializable;

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

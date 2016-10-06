package com.hand.init;

import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;

/**
 * Created by tuberose on 16-9-1.
 */
public class InitDataListener implements InitializingBean {


    public InitDataListener() {
        System.out.println("InitSequenceBean: constructor");
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("InitSequenceBean: postConstruct");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitSequenceBean: afterPropertiesSet");
    }

    public void initMethod() {
        System.out.println("InitSequenceBean: init-method");
    }

}

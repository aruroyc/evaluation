package com.arktech.analytics.api;

import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.xml.crypto.Data;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.invoke.SerializedLambda;
import java.util.*;

/**
 * Created by roychoud on 06/03/17.
 */
public abstract class DataPersister {

    @Resource
    private static List<DataProcessor> dataProcessors;

    private Map<String,DataProcessor> dataProcessorMap = new LinkedHashMap<>();

    @PostConstruct
    private void init() {
        if (dataProcessorMap!=null)
            dataProcessors.forEach(dataProcessor -> {
                dataProcessorMap.put(dataProcessor.getClass().getAnnotation(Qualifier.class).value(), dataProcessor);
            });
    }
    public boolean doPersist(Serializable eachObject)
    {
        return doPersistImpl((Serializable) dataProcessorMap.get(this.getClass().getAnnotation(Qualifier.class).value()).doProcess
                (eachObject));
    }
    public Collection<?> retrieve(Collection<Serializable> ids)
    {
        return retrieveImpl((Collection<Serializable>) dataProcessorMap.get(this.getClass().getAnnotation(Qualifier.class).value()).doProcess
                (ids));
    }
    protected abstract boolean doPersistImpl(Serializable eachObject);

    protected abstract Collection<?> retrieveImpl(Collection<Serializable> ids);

}

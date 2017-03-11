package com.arktech.analytics.processors;

import com.arktech.analytics.api.AnalyticsQueue;
import com.arktech.analytics.api.DataProcessor;
import com.arktech.analytics.persistence.NoSQLDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.Serializable;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by roychoud on 07/03/17.
 */
@Service
@Qualifier("defaultQueue")
public class DefaultAnalyticsQueue implements AnalyticsQueue {

    @Value("${analytics.consumers}")
    private int maxAnalyticsConsumers;

    @Autowired
    private NoSQLDataRepository repository;
    @Resource
    private static List<DataProcessor> dataProcessors;

    private Map<String,DataProcessor> dataProcessorMap = new LinkedHashMap<>();
    private ExecutorService executorService;

    @PostConstruct
    private void init() {
        if (dataProcessorMap.isEmpty())
            dataProcessors.forEach(dataProcessor -> {
                dataProcessorMap.put(dataProcessor.getClass().getAnnotation(Qualifier.class).value(), dataProcessor);
            });
        executorService= Executors.newFixedThreadPool(maxAnalyticsConsumers);
    }

    public void pushData(Object object,String analyticsFacet,boolean doProcess)
    {
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try{
                    repository.save((Serializable)(doProcess?dataProcessorMap.get
                            (analyticsFacet)
                            .doProcess(object):object));
                }
                catch (Exception e)
                {
                    //TODO Logging
                }
            }
        });

    }
    public void pushData(Collection<?> object,String analyticsFacet,boolean doProcess)
    {
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try{
                    repository.save((Collection<?>)(doProcess?dataProcessorMap.get
                            (analyticsFacet)
                            .parallelProcess(object):object));
                }
                catch (Exception e)
                {
                    //TODO Logging
                }
            }
        });
    }

}

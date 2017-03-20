package com.arktech.analytics.processors;

import com.arktech.analytics.api.AnalyticsQueue;
import com.arktech.analytics.api.DataProcessor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.repository.MongoRepository;
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
    private Logger logger = LogManager.getLogger(DefaultAnalyticsQueue.class);
    @Resource
    private List<MongoRepository> repositories;
    @Resource
    private List<DataProcessor> dataProcessors;

    private Map<String,DataProcessor> dataProcessorMap = new LinkedHashMap<>();
    private Map<String,MongoRepository> dataPersisterMap = new LinkedHashMap<>();
    private ExecutorService executorService;

    @PostConstruct
    private void init() {

            if (dataProcessorMap.isEmpty())
                dataProcessors.forEach(dataProcessor -> {
                    dataProcessorMap.put(dataProcessor.getClass().getAnnotation(Qualifier.class).value(), dataProcessor);
                });
            if (dataPersisterMap.isEmpty())
                repositories.forEach(repository -> {
                    try {
                        dataPersisterMap.put(repository.getClass().getField("qualifier").get(repository).toString(),
                                repository);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
            executorService= Executors.newFixedThreadPool(maxAnalyticsConsumers);
    }

    public void pushData(Object object,String analyticsFacet,boolean doProcess)
    {
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try{
                    dataPersisterMap.get(analyticsFacet).save((Serializable)(doProcess?dataProcessorMap.get
                            (analyticsFacet)
                            .doProcess(object):object));
                }
                catch (Exception e)
                {
                    logger.error("Error while asynchronously pushing data to analytics :\n"+object.toString());
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
                    dataPersisterMap.get(analyticsFacet).save((Collection<?>)(doProcess?dataProcessorMap.get
                            (analyticsFacet)
                            .parallelProcess(object):object));
                }
                catch (Exception e)
                {
                    logger.error("Error while asynchronously pushing "+object.size()+" data objects to analytics " +
                            ":\n"+object.toString());
                }
            }
        });
    }

}

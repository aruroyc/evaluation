package com.arktech.analytics.processors;

import com.arktech.analytics.api.ChartConstructor;
import com.arktech.analytics.api.DataProcessor;
import com.arktech.analytics.report.charts.ChartInfo;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by aruroyc on 11/03/17.
 */
public class ChartProcessor implements DataProcessor {
    @Resource
    private List<ChartConstructor> chartConstructors;

    @Override
    public Object doProcess(Object object) {
        if (!(object instanceof ChartInfo))
        {
            throw new RuntimeException("Incorrect params passed to method!");
        }
        switch(((ChartInfo)object).chartType)
        {
            default:
                throw new RuntimeException("No chart implementation for ChartType : "+((ChartInfo)object).chartType);
        }
    }
}

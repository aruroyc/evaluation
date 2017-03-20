package com.arktech.analytics.report.charts.constructors;

import com.arktech.analytics.api.ChartConstructor;
import com.arktech.analytics.report.charts.ChartInfo;
import org.springframework.stereotype.Service;

/**
 * Created by aruroyc on 16/03/17.
 */
@Service
public class NoChartConstructor implements ChartConstructor {

    @Override
    public ChartInfo constructChart(ChartInfo chartInfo) {
        throw new RuntimeException("Sobai mukhe nao!");
    }
}

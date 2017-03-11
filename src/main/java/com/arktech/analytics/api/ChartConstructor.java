package com.arktech.analytics.api;

import com.arktech.analytics.report.charts.ChartInfo;

/**
 * Created by aruroyc on 11/03/17.
 */
public interface ChartConstructor {

    public ChartInfo constructChart(ChartInfo chartInfo);
}

package com.arktech.analytics.api;

import com.arktech.analytics.report.charts.ChartInfo;

/**
 * API that specifies a single method constructChart, which can be implemented by individual
 * types of chart constructors. eg: PieChartConstructor,LineChartConstructor etc
 * Created by aruroyc on 11/03/17.
 */
public interface ChartConstructor {

    public ChartInfo constructChart(ChartInfo chartInfo);
}

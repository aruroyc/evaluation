package com.arktech.analytics.report.charts;

import com.arktech.analytics.report.ReportFacet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by aruroyc on 11/03/17.
 */
public class ChartInfo {

    private String _id;
    public String reportId;
    public ChartType chartType;
    public List<ChartLegend> chartMetrics = new ArrayList<>();
    public Object chartStructure;

}

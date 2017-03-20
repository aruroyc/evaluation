package com.arktech.analytics.report.charts;

import com.arktech.analytics.report.ReportFacet;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by aruroyc on 11/03/17.
 *
 * This class should be used to hold and persist data for a specific chart that can be mapped to a single report.
 * The chartStructure variable can be populated using the constructChart method from ChartConstructor.
 */
public class ChartInfo {

    @Id
    private String _id;
    public String reportId;
    public ChartType chartType;
    public List<ChartLegend> chartMetrics = new ArrayList<>();
    public Object chartStructure;
}

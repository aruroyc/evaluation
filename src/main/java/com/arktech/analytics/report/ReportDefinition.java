package com.arktech.analytics.report;

import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.List;

/**
 * Created by aruroyc on 11/03/17.
 */
public class ReportDefinition {

    @Id
    public Serializable reportId;

    public String ownerId;
    public String reportName;
    public List<ReportFacet> reportFacetList;
    public List<ReportFilter> reportLevelFilters;
    public String filterConjunction;
    public boolean isLiveReport;
}


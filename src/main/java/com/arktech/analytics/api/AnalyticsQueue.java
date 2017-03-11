package com.arktech.analytics.api;

import java.util.Collection;

/**
 * Created by aruroyc on 11/03/17.
 */
public interface AnalyticsQueue {
    /**
     * Pushes data for processing to analytics module
     * @param object
     * @param analyticsFacet
     * @param doProcess
     */
    public void pushData(Object object,String analyticsFacet,boolean doProcess);

    /**
     *  Pushes data for processing to analytics module
     * @param object
     * @param analyticsFacet
     * @param doProcess
     */
    public void pushData(Collection<?> object, String analyticsFacet, boolean doProcess);
}

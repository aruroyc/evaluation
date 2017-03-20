package com.arktech.analytics.persistence;

import com.arktech.analytics.report.charts.ChartInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


/**
 * Created by roychoud on 06/03/17.
 */
@Repository
public interface ChartInfoRepository extends MongoRepository<ChartInfo,String > {

    final String qualifier="Chart";
}

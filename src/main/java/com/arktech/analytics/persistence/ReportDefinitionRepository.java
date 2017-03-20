package com.arktech.analytics.persistence;

import com.arktech.analytics.report.ReportDefinition;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * Created by aruroyc on 19/03/17.
 */
@Repository
@Service("Chartzz")
public interface ReportDefinitionRepository extends MongoRepository<ReportDefinition,String>{

    final String qualifier="Report";
}

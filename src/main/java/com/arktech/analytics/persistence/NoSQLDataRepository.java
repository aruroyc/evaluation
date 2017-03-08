package com.arktech.analytics.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * Created by roychoud on 06/03/17.
 */
@Repository
public interface NoSQLDataRepository extends MongoRepository<Object,Serializable> {
}

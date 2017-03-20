package com.arktech.analytics.api;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * API that specifies a abstract method doProcess to be implement by
 * individual processors based on how they want to modify the object.
 * Also specifies two methods to allow iterative and parallel processing of a collection of objects.
 * Created by roychoud on 06/03/17.
 */
public interface DataProcessor {

    public Object doProcess(Object object);

    default List<?> parallelProcess(Collection<?> collection)
    {
        return collection.parallelStream().map(eachObject ->{
            return doProcess(eachObject);
        }).collect(Collectors.toList());
    }
    default List<?> inOrderProcess(Collection<?> collection)
    {
        return collection.stream().map(eachObject ->{
            return doProcess(eachObject);
        }).collect(Collectors.toList());
    }
}

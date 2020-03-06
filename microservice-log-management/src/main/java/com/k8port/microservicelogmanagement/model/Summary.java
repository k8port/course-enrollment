package com.k8port.microservicelogmanagement.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import static org.springframework.data.cassandra.core.cql.Ordering.DESCENDING;
import static org.springframework.data.cassandra.core.cql.PrimaryKeyType.CLUSTERED;
import static org.springframework.data.cassandra.core.cql.PrimaryKeyType.PARTITIONED;

@Data
@NoArgsConstructor
@Table("summary")
public class Summary implements IModel {

    @PrimaryKeyColumn(name = "course_id", type = PARTITIONED)
    private Long courseId;

    @PrimaryKeyColumn(name = "hit_count", ordinal = 0, type = CLUSTERED, ordering = DESCENDING)
    private Long hitCount;

}

package com.k8port.microservicelogmanagement.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.DropKeyspaceSpecification;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification.createKeyspace;
import static org.springframework.data.cassandra.core.cql.keyspace.DropKeyspaceSpecification.dropKeyspace;
import static org.springframework.data.cassandra.core.cql.keyspace.KeyspaceOption.DURABLE_WRITES;

@Configuration
public class CassandraConfig extends AbstractCassandraConfiguration {

    @Value(value = "${spring.data.cassandra.keyspace-name}")
    private String KEYSPACE;

    @Override
    public SchemaAction getSchemaAction() {
        return SchemaAction.CREATE_IF_NOT_EXISTS;
    }

    @Override
    protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {
        CreateKeyspaceSpecification specification = createKeyspace(KEYSPACE)
                .ifNotExists().with(DURABLE_WRITES, true)
                .withSimpleReplication();
        return singletonList(specification);
    }

    @Override
    protected List<DropKeyspaceSpecification> getKeyspaceDrops() {
        return singletonList(dropKeyspace(KEYSPACE));
    }

    @Override
    protected String getKeyspaceName() {
        return KEYSPACE;
    }

    @Override
    public String[] getEntityBasePackages() {
        return new String[]{"com.k8port.microservicelogmanagement.model"};
    }

    @Override
    protected boolean getMetricsEnabled() {
        return false;
    }

}

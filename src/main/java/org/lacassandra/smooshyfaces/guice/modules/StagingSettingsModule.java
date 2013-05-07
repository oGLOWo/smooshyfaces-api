package org.lacassandra.smooshyfaces.guice.modules;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class StagingSettingsModule extends AbstractModule {
    @Override
    protected void configure() {
        // Astyanax/Cassandra Settings
        bind(String.class).annotatedWith(Names.named("SEED_HOSTS")).toInstance("your-staging-server-1.com:9160,your-staging-server-2.com:9160");
        bind(String.class).annotatedWith(Names.named("CASSANDRA_CLUSTER_NAME")).toInstance("StagingCluster");
        bind(String.class).annotatedWith(Names.named("CASSANDRA_CONNECTION_POOL_NAME")).toInstance("SmooshyFacesPool");
        bind(String.class).annotatedWith(Names.named("CASSANDRA_KEYSPACE_NAME")).toInstance("smooshyfaces");
        bind(Integer.class).annotatedWith(Names.named("THRIFT_PORT")).toInstance(9160);
        bind(Integer.class).annotatedWith(Names.named("MAX_BLOCKED_THREADS_PER_HOST")).toInstance(25);
        bind(Integer.class).annotatedWith(Names.named("MAX_TIMEOUT_COUNT")).toInstance(3);
        bind(Integer.class).annotatedWith(Names.named("MAX_TIMEOUT_WHEN_EXHAUSTED")).toInstance(3000);
        bind(Integer.class).annotatedWith(Names.named("MAX_CONNECTIONS_PER_HOST")).toInstance(3);
        bind(Integer.class).annotatedWith(Names.named("MAX_PENDING_CONNECTIONS_PER_HOST")).toInstance(5);
        bind(Integer.class).annotatedWith(Names.named("MAX_CONNECTIONS")).toInstance(1);

        // API Servers available
        bind(String.class).annotatedWith(Names.named("API_SERVERS")).toInstance("staging-api.yourdomain.com");

        // Paging
        bindConstant().annotatedWith(Names.named("DEFAULT_PAGE_SIZE")).to(10);
        bindConstant().annotatedWith(Names.named("MINIMUM_PAGE_SIZE")).to(1);
        bindConstant().annotatedWith(Names.named("MAXIMUM_PAGE_SIZE")).to(20);
    }
}

package org.lacassandra.smooshyfaces.guice.providers;


import com.netflix.astyanax.AstyanaxContext;
import com.netflix.astyanax.Keyspace;
import com.netflix.astyanax.connectionpool.NodeDiscoveryType;
import com.netflix.astyanax.connectionpool.impl.ConnectionPoolConfigurationImpl;
import com.netflix.astyanax.connectionpool.impl.Slf4jConnectionPoolMonitorImpl;
import com.netflix.astyanax.impl.AstyanaxConfigurationImpl;
import com.netflix.astyanax.model.ConsistencyLevel;
import com.netflix.astyanax.thrift.ThriftFamilyFactory;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;

public class AstyanaxKeyspaceContextProvider implements Provider<AstyanaxContext<Keyspace>> {
    protected int thriftPort;
    protected String seedHosts;
    protected int maxBlockedThreadsPerHost;
    protected int maxTimeoutCount;
    protected int maxTimeoutWhenExhausted;
    protected int maxConnectionsPerHost;
    protected int maxPendingConnectionsPerHost;
    protected int maxConnectinos;
    protected String clusterName;
    protected String poolName;
    protected String keyspaceName;

    public String getClusterName() {
        return clusterName;
    }

    @Inject
    public void setClusterName(@Named("CASSANDRA_CLUSTER_NAME") final String clusterName) {
        this.clusterName = clusterName;
    }

    public String getPoolName() {
        return poolName;
    }

    @Inject
    public void setPoolName(@Named("CASSANDRA_CONNECTION_POOL_NAME") final String poolName) {
        this.poolName = poolName;
    }

    public String getKeyspaceName() {
        return keyspaceName;
    }

    @Inject
    public void setKeyspaceName(@Named("CASSANDRA_KEYSPACE_NAME") final String keyspaceName) {
        this.keyspaceName = keyspaceName;
    }

    public int getThriftPort() {
        return thriftPort;
    }

    @Inject
    public void setThriftPort(@Named("THRIFT_PORT") int thriftPort) {
        this.thriftPort = thriftPort;
    }

    public String getSeedHosts() {
        return seedHosts;
    }

    @Inject
    public void setSeedHosts(@Named("SEED_HOSTS") String seedHosts) {
        this.seedHosts = seedHosts;
    }

    public int getMaxBlockedThreadsPerHost() {
        return maxBlockedThreadsPerHost;
    }

    @Inject
    public void setMaxBlockedThreadsPerHost(@Named("MAX_BLOCKED_THREADS_PER_HOST") int maxBlockedThreadsPerHost) {
        this.maxBlockedThreadsPerHost = maxBlockedThreadsPerHost;
    }

    public int getMaxTimeoutCount() {
        return maxTimeoutCount;
    }

    @Inject
    public void setMaxTimeoutCount(@Named("MAX_TIMEOUT_COUNT") int maxTimeoutCount) {
        this.maxTimeoutCount = maxTimeoutCount;
    }

    public int getMaxTimeoutWhenExhausted() {
        return maxTimeoutWhenExhausted;
    }

    @Inject
    public void setMaxTimeoutWhenExhausted(@Named("MAX_TIMEOUT_WHEN_EXHAUSTED") int maxTimeoutWhenExhausted) {
        this.maxTimeoutWhenExhausted = maxTimeoutWhenExhausted;
    }

    public int getMaxConnectionsPerHost() {
        return maxConnectionsPerHost;
    }

    @Inject
    public void setMaxConnectionsPerHost(@Named("MAX_CONNECTIONS_PER_HOST") int maxConnectionsPerHost) {
        this.maxConnectionsPerHost = maxConnectionsPerHost;
    }

    public int getMaxPendingConnectionsPerHost() {
        return maxPendingConnectionsPerHost;
    }

    @Inject
    public void setMaxPendingConnectionsPerHost(@Named("MAX_PENDING_CONNECTIONS_PER_HOST") int maxPendingConnectionsPerHost) {
        this.maxPendingConnectionsPerHost = maxPendingConnectionsPerHost;
    }

    public int getMaxConnectinos() {
        return maxConnectinos;
    }

    @Inject
    public void setMaxConnectinos(@Named("MAX_CONNECTIONS") int maxConnectinos) {
        this.maxConnectinos = maxConnectinos;
    }

    @Override
    public AstyanaxContext<Keyspace> get() {
        AstyanaxContext<Keyspace> context = new AstyanaxContext.Builder()
                .forCluster(getClusterName())
                .forKeyspace(getKeyspaceName())
                .withAstyanaxConfiguration(new AstyanaxConfigurationImpl()
                        .setDiscoveryType(NodeDiscoveryType.TOKEN_AWARE)
                        .setDefaultReadConsistencyLevel(ConsistencyLevel.CL_QUORUM)
                        .setDefaultWriteConsistencyLevel(ConsistencyLevel.CL_QUORUM)
                )
                .withConnectionPoolConfiguration(new ConnectionPoolConfigurationImpl(getPoolName())
                        .setPort(getThriftPort())
                        .setSeeds(getSeedHosts())
                        .setMaxBlockedThreadsPerHost(getMaxBlockedThreadsPerHost())
                        .setMaxTimeoutCount(getMaxTimeoutCount())
                        .setMaxTimeoutWhenExhausted(getMaxTimeoutWhenExhausted())
                        .setMaxConnsPerHost(getMaxConnectionsPerHost())
                        .setMaxPendingConnectionsPerHost(getMaxPendingConnectionsPerHost())
                        .setMaxConns(getMaxConnectinos())
                )
                .withConnectionPoolMonitor(new Slf4jConnectionPoolMonitorImpl())
                .buildKeyspace(ThriftFamilyFactory.getInstance());
        context.start();
        return context;
    }
}

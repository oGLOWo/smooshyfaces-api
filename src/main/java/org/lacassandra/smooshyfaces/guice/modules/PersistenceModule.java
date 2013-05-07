package org.lacassandra.smooshyfaces.guice.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.TypeLiteral;
import com.netflix.astyanax.AstyanaxContext;
import com.netflix.astyanax.Keyspace;
import org.lacassandra.smooshyfaces.guice.annotations.Cassandra;
import org.lacassandra.smooshyfaces.guice.providers.AstyanaxKeyspaceContextProvider;
import org.lacassandra.smooshyfaces.persistence.DAOFactory;
import org.lacassandra.smooshyfaces.persistence.cassandra.CassandraDAOFactory;

public class PersistenceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(new TypeLiteral<AstyanaxContext<Keyspace>>(){}).toProvider(AstyanaxKeyspaceContextProvider.class).in(Scopes.SINGLETON);
        bind(DAOFactory.class).annotatedWith(Cassandra.class).to(CassandraDAOFactory.class).in(Scopes.SINGLETON);
    }
}

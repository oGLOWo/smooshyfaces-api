package org.lacassandra.smooshyfaces.analytics;

import com.google.inject.Inject;
import org.lacassandra.smooshyfaces.guice.annotations.Cassandra;
import org.lacassandra.smooshyfaces.guice.annotations.InjectSLF4JLog;
import org.lacassandra.smooshyfaces.persistence.DAOFactory;
import org.slf4j.Logger;

public class DefaultAnalytics implements Analytics {
    @InjectSLF4JLog
    private Logger logger;

    @Inject
    @Cassandra
    protected DAOFactory daoFactory;
}

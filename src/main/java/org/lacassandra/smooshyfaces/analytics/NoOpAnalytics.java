package org.lacassandra.smooshyfaces.analytics;

import com.google.inject.Inject;
import org.lacassandra.smooshyfaces.guice.annotations.Cassandra;
import org.lacassandra.smooshyfaces.persistence.DAOFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NoOpAnalytics implements Analytics {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Inject
    @Cassandra
    protected DAOFactory daoFactory;

}

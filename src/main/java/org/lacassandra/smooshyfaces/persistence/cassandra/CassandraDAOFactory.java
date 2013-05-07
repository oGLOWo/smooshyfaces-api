package org.lacassandra.smooshyfaces.persistence.cassandra;

import com.google.inject.Injector;
import org.lacassandra.smooshyfaces.persistence.*;

import javax.inject.Inject;

public class CassandraDAOFactory extends DAOFactory {
    @Inject
    protected Injector injector;

    protected <T extends BaseCassandraDAO> T inject(Class<T> clazz) {
        return injector.getInstance(clazz);
    }

    @Override
    public UserDAO createUserDAO() {
        return inject(CassandraUserDAO.class);
    }

    @Override
    public UserSessionDAO createUserSessionDAO() {
        return inject(CassandraUserSessionDAO.class);
    }

    @Override
    public PictureDAO createPictureDAO() {
        return inject(CassandraPictureDAO.class);
    }
}

package org.lacassandra.smooshyfaces.api;

import org.lacassandra.smooshyfaces.guice.annotations.Cassandra;
import org.lacassandra.smooshyfaces.guice.annotations.InjectSLF4JLog;
import org.lacassandra.smooshyfaces.persistence.DAOFactory;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.ws.rs.Path;

@Path("/pictures")
public class PicturesResource {
    @InjectSLF4JLog
    private Logger logger;

    @Inject
    @Cassandra
    protected DAOFactory daoFactory;

    // implement PUT and POST where PUT takes the id of a current picture
    // and POST uploads a new picture

    // You should also add a POST method for pictures/{picture-id}/comments
    // so people can comment
    // You should add a GET on pictures/{picture-id}/comments to get the comments
    // for the picture
}

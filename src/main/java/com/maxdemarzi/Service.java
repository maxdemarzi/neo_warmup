package com.maxdemarzi;

import org.codehaus.jackson.map.ObjectMapper;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Transaction;
import org.neo4j.tooling.GlobalGraphOperations;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Path("/service")
public class Service {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @GET
    @Path("/helloworld")
    public Response helloWorld() throws IOException {
        Map<String, String> results = new HashMap<String,String>(){{
            put("response","hello world");
        }};
        return Response.ok().entity(objectMapper.writeValueAsString(results)).build();
    }

    @GET
    @Path("/warmup")
    public Response warmUp(@Context GraphDatabaseService db) throws IOException {
        try ( Transaction tx = db.beginTx() )
        {

            for (Node node : GlobalGraphOperations.at(db).getAllNodes()) {
                node.getPropertyKeys();
            }

            for ( Relationship relationship : GlobalGraphOperations.at(db).getAllRelationships()){
                relationship.getPropertyKeys();
                relationship.getNodes();
            }
        }

        Map<String, String> results = new HashMap<String,String>(){{
            put("response","Warmed up and ready to go!");
        }};

        return Response.ok().entity(objectMapper.writeValueAsString(results)).build();
    }

}

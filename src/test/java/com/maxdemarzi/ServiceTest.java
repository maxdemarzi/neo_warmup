package com.maxdemarzi;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.test.TestGraphDatabaseFactory;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class ServiceTest {
    public static GraphDatabaseService db;
    public static Service service;
    public static final ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setUp() throws IOException {
        db = new TestGraphDatabaseFactory().newImpermanentDatabase();
        service = new Service();
    }

    @After
    public void tearDown() {
        db.shutdown();
    }


    @Test
    public void shouldRespondToHelloWorld() throws IOException {
        Response response = service.helloWorld();
        HashMap actual = objectMapper.readValue((String) response.getEntity(), HashMap.class);
        assertEquals(HELLO_WORLD_MAP, actual);
    }

    @Test
    public void shouldWarmUp() throws IOException {
        Response response = service.warmUp(db);
        HashMap actual = objectMapper.readValue((String) response.getEntity(), HashMap.class);
        assertEquals(WARM_UP_MAP, actual);
    }



    public static HashMap<String, String> HELLO_WORLD_MAP = new HashMap<String, String>(){{
        put("response","hello world");
    }};

    public static HashMap<String, String> WARM_UP_MAP = new HashMap<String, String>(){{
        put("response","Warmed up and ready to go!");
    }};

}

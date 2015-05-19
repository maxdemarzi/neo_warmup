Neo_Warm Unmanaged Extension
================================

This is an unmanaged extension warming up the database.

1. Build it:

        mvn clean package

2. Copy target/warmup-1.0.jar to the plugins/ directory of your Neo4j server.

3. Configure Neo4j by adding a line to conf/neo4j-server.properties:

        org.neo4j.server.thirdparty_jaxrs_classes=com.maxdemarzi=/v1

4. Start Neo4j server.

5. Query it over HTTP:

        curl http://localhost:7474/v1/service/helloworld
        curl http://localhost:7474/v1/service/warmup

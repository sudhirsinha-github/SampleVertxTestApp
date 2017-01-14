package com.test;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

/**
 * Created by Sudhir on 05/10/16.
 */
public class ServiceDeployer {
    private static final Logger logger = LoggerFactory.getLogger(ServiceDeployer.class);
     public static final String ENVVALUE = System.getenv("ENV");

    public static void main(String[] args){
        Vertx vertx = Vertx.vertx();
        Date date = new Date();
        logger.info(" Worker API Service started - ");
        String configurationFile = args[0];
        JsonObject configuration;


        try {
            configuration = new JsonObject(
                    new String(Files
                            .readAllBytes(Paths.get(configurationFile))
                    )
            );

            // Changes for ENV VALUE COnfig reading
            JsonObject envConfig = ENVVALUE != null ? configuration.getJsonObject(ENVVALUE) :
                    configuration.getJsonObject("DEV");

            DeploymentOptions options = new DeploymentOptions().setConfig(envConfig);
            logger.info("Configuration was loaded");

           // options.setInstances(4);
            vertx.deployVerticle("com.test.verticle.MyServiceVerticle", options);
        }
        catch (Exception exception) {
            logger.error(exception.getMessage());
        }
    }
}

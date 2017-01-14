package com.test.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Context;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.Router;

/**
 * Created by Sudhir on 05/10/16.
 */
public class MyServiceVerticle extends AbstractVerticle {
    private static final Logger logger = LoggerFactory.getLogger(MyServiceVerticle.class);

    private HttpServer httpServer;
    private Router router;

    private Vertx vertx;
private String getmongoUrl;

    @Override
    public void init(Vertx vertx, Context context) {
        super.init(vertx, context);
        getmongoUrl= vertx.getOrCreateContext().config().getString("mongoBaseURL");
        this.vertx = vertx;
    }

    @Override
    public void start() {
        logger.info("Vertx is going to start @@@--->>>>" + getmongoUrl);
    }


    @Override
    public void stop() {
        httpServer
                .close();
    }
}

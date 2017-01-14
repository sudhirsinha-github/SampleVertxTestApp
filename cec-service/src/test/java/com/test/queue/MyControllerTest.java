package com.test.queue;

import com.test.verticle.MyServiceVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

/**
 * Created by sudhirkumar on 1/9/17.
 */
@RunWith(VertxUnitRunner.class)
public class MyControllerTest {

    Vertx vertx;

    DeploymentOptions options = null;

    @Before
    public void setUp(TestContext context) throws IOException {

        vertx = Vertx.vertx();
        // vertx = Vertx.vertx();
        JsonObject obj = new JsonObject();
        obj.put("mongoBaseURL", "http://test.com");

        options = new DeploymentOptions().setConfig(obj);
        vertx.deployVerticle(MyServiceVerticle.class.getName(), options, context.asyncAssertSuccess());

        try {
            Thread.sleep(700);
        } catch (Exception ex) {
        }


        System.out.println("TEST -- CONFIG VALUE SEARCH ****" + vertx.getOrCreateContext().config().getString("mongoBaseURL"));
    }

   @Test
    public void consumeTest(TestContext context)
    {
        System.out.println("consumeTest ---- TEST -- CONFIG VALUE SEARCH ****"  +vertx.getOrCreateContext().config().getString("mongoBaseURL"));
    }

}
package com.sourabh.vertx_starter.eventbus;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestResponseExample extends AbstractVerticle {

  public static void main(String[] args) {
    var vertx= Vertx.vertx();
    vertx.deployVerticle(new RequestVerticle());
    vertx.deployVerticle(new ResponseVerticle());
  }

  static class RequestVerticle extends AbstractVerticle{

    private static final Logger LOG = LoggerFactory.getLogger(RequestVerticle.class);
    public static final String ADDRESS = "my.request.address";

    @Override
    public void start(final Promise<Void> startPromise) throws Exception {
      startPromise.complete();
      var eventBus = vertx.eventBus();
      final String message = "Hello World!!";
      LOG.debug("Sending {}", message);
      eventBus.<String>request(ADDRESS, message, reply -> {
        LOG.debug("Response {}", reply.result().body());
      });
    }
  }

  static class ResponseVerticle extends AbstractVerticle{

    private static final Logger LOG = LoggerFactory.getLogger(ResponseVerticle.class);
    @Override
    public void start(final Promise<Void> startPromise) throws Exception {
      startPromise.complete();
      vertx.eventBus().<String>consumer(RequestVerticle.ADDRESS, message -> {
        LOG.debug("Recieved message {}", message.body());
        message.reply("Recieved our Message. Thanks !!");
      });
    }
  }
}

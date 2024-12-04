package com.sourabh.vertx_starter.eventbus;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PointToPointExample extends AbstractVerticle {

  public static void main(String[] args) {
    var vertx= Vertx.vertx();
    vertx.deployVerticle(new Sender());
    vertx.deployVerticle(new Reciever());

  }

  static class Sender extends AbstractVerticle{
    @Override
    public void start(final Promise<Void> startPromise) throws Exception {
      startPromise.complete();
      vertx.setPeriodic(1000, id -> {
        //sending message every second
        vertx.eventBus().send(Sender.class.getName(), "Sending a message....");
      });

    }
  }

  static class Reciever extends AbstractVerticle{

    private static final Logger LOG = LoggerFactory.getLogger(Reciever.class);

    @Override
    public void start(final Promise<Void> startPromise) throws Exception {
      startPromise.complete();
      vertx.eventBus().<String>consumer(Sender.class.getName(), message -> {
        LOG.debug("Recieved: {}", message.body());
      });
    }
  }
}

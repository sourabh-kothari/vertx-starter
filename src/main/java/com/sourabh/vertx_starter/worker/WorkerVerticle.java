package com.sourabh.vertx_starter.worker;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WorkerVerticle  extends AbstractVerticle {

  private static final Logger LOG = LoggerFactory.getLogger(WorkerVerticle.class);

  public static void main(String[] args) {

  }

  @Override
  public void start(final Promise<Void> startPromise) throws Exception {
    LOG.debug("Deployed as worker verticle.");
    startPromise.complete();
    Thread.sleep(5000);
    LOG.debug("Blocking operation done");
  }
}

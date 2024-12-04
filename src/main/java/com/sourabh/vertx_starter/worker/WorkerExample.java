package com.sourabh.vertx_starter.worker;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WorkerExample extends AbstractVerticle {

  private static final Logger LOG = LoggerFactory.getLogger(WorkerExample.class);

  public static void main(String[] args) {
    var vertx= Vertx.vertx();
    vertx.deployVerticle(new WorkerExample());
  }

  @Override
  public void start(final Promise<Void> startPromise) throws Exception {

    vertx.deployVerticle(new WorkerVerticle(),
      new DeploymentOptions()
        .setWorker(true)
        .setWorkerPoolSize(1)
        .setWorkerPoolName("my-worker-verticle"));
    startPromise.complete();

    executeBlockingCode();
  }

  private void executeBlockingCode() {
    vertx.executeBlocking(event -> {
      LOG.debug("Executing Blocking Code");
      try {
        Thread.sleep(5000);
        event.complete();
      } catch (InterruptedException e) {
        LOG.debug("Failed:", e);
        event.fail(e);
      }

    }, result -> {
      if(result.succeeded()){
        LOG.debug("Blocking Call Done");
      } else{
        LOG.debug("Blocking call Failed Due to:", result.cause());
      }
    });
  }
}

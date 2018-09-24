package org.camunda.bpm.getstarted.chargecard;

import java.util.logging.Logger;

import org.camunda.bpm.client.ExternalTaskClient;

public class SendConfirmationEmail {
	private final static Logger LOGGER = Logger.getLogger(ChargeCardWorker.class.getName());

	  public static void main(String[] args) {
	  
	    ExternalTaskClient client = ExternalTaskClient.create()
	        .baseUrl("http://localhost:8080/engine-rest")
	        .build();

	    // subscribe to an external task topic as specified in the process
	    client.subscribe("send-email")
	        .lockDuration(1000) // the default lock duration is 20 seconds, but you can override this
	        .handler((externalTask, externalTaskService) -> {
	          // Put your business logic here

	          // Get a process variable
	          LOGGER.info("Order confirmation Email is sent. Thank you!!");

	          // Complete the task
	          externalTaskService.complete(externalTask);
	        })
	        .open();
	  }

}

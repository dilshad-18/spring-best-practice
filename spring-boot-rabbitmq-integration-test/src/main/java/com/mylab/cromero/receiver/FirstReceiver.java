package com.mylab.cromero.receiver;

import static com.mylab.cromero.model.Constant.EXCHANGE_NAME;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class FirstReceiver {
    public final static String QUEUE_ROUTINGKEY = "routingKey1-boot";
    private final static String QUEUE_NAME = "spring-boot";

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private Integer counter = 0;

    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = QUEUE_NAME, durable = "true"),
            exchange = @Exchange(value = EXCHANGE_NAME, ignoreDeclarationExceptions = "true"),
            key = QUEUE_ROUTINGKEY))
    public void receiveMessage(String message) {
        logger.info("From receiver 1: Received <{}>" + message);
        counter++;
    }

    public Integer getCounter() {
        return counter;
    }

    public void initCounter() {
        this.counter = 0;
    }

}

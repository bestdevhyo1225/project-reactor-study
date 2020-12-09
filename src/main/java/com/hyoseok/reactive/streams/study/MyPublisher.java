package com.hyoseok.reactive.streams.study;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyPublisher implements Publisher<Integer> {

    private final Logger logger = LoggerFactory.getLogger(MyPublisher.class);

    private final ExecutorService executorService = Executors.newFixedThreadPool(3);

    @Override
    public void subscribe(Subscriber<? super Integer> subscriber) {
        logger.info("publisher - subscribe");

        subscriber.onSubscribe(new MySubscription(subscriber, executorService));
    }
}

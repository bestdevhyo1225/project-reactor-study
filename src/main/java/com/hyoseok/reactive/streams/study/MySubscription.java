package com.hyoseok.reactive.streams.study;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

public class MySubscription implements Subscription {

    private final Logger logger = LoggerFactory.getLogger(MySubscription.class);
    private final Subscriber<? super Integer> subscriber;
    private final ExecutorService executorService;
    private final AtomicInteger value;

    public MySubscription(Subscriber<? super Integer> subscriber, ExecutorService executorService) {
        this.subscriber = subscriber;
        this.executorService = executorService;
        this.value = new AtomicInteger();
    }

    @Override
    public void request(long n) {
        if (n < 0) return;

        for (int i = 0; i < n; i++) {
            executorService.execute(() -> {
                int count = value.incrementAndGet();

                if (count > 10) {
                    logger.info("Item is Over ");
                    subscriber.onComplete();
                } else {
                    logger.info("Push Item + " + count);
                    subscriber.onNext(count);
                }
            });
        }
    }

    @Override
    public void cancel() {
        subscriber.onComplete();
    }
}

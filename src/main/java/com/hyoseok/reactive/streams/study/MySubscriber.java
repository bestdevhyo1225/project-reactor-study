package com.hyoseok.reactive.streams.study;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MySubscriber implements Subscriber<Integer> {

    private final Logger logger = LoggerFactory.getLogger(MySubscriber.class);
    private final Integer DEMAND_COUNT = 3;
    private Subscription subscription;
    private Integer count;

    @Override
    public void onSubscribe(Subscription subscription) {
        logger.info("subscriber - onSubscribe");

        count = DEMAND_COUNT;
        this.subscription = subscription;
        this.subscription.request(DEMAND_COUNT);
    }

    @Override
    public void onNext(Integer integer) {
        logger.info("subscriber - onNext");

        synchronized (this) {
            count--;

            if (count == 0) {
                logger.info("count is zero");
                count = DEMAND_COUNT;
                subscription.request(count);
            }
        }
    }

    @Override
    public void onError(Throwable t) {
        logger.info("subscriber - onError");
    }

    @Override
    public void onComplete() {
        logger.info("subscriber - onComplete");
    }
}

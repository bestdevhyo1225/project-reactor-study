package com.hyoseok.reactive.streams.study;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) {
		Publisher<Integer> myPublisher = new MyPublisher();
		Subscriber<Integer> mySubscriber = new MySubscriber();
		myPublisher.subscribe(mySubscriber);
	}
}

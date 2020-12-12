package com.hyoseok.reactive.streams.study;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Signal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Subscriber 테스트")
class SubscriberTest {

    @Test
    void flux_subscribe() {
        // given
        List<String> names = new ArrayList<>();
        Flux<String> flux = Flux.just("박철수", "김영희", "홍길동").log();

        // when
        flux.subscribe(names::add);

        // then
        assertThat(names.get(0)).isEqualTo("박철수");
        assertThat(names.get(1)).isEqualTo("김영희");
        assertThat(names.get(2)).isEqualTo("홍길동");
    }

    @Test
    void flux_subscribe_complete() {
        // given
        List<String> names = new ArrayList<>();
        List<Signal<String>> signals = new ArrayList<>();
        Flux<String> flux = Flux.just("JangHyoSeok", "HongGilDong")
                .log()
                .doOnEach(signals::add);

        // when
        flux.subscribe(
                names::add,
                error -> {},
                () -> {
                    // given...
                    assertThat(names.get(0)).isEqualTo("JangHyoSeok");
                    assertThat(names.get(1)).isEqualTo("HongGilDong");

                    assertThat(signals.size()).isEqualTo(3);
                    assertThat(signals.get(0).isOnNext()).isTrue();
                    assertThat(signals.get(1).isOnNext()).isTrue();
                    assertThat(signals.get(2).isOnComplete()).isTrue();
                }
        );
    }

    @Test
    void flux_subscribe_subscription() {
        // given
        List<String> names = new ArrayList<>();
        Flux<String> flux = Flux.just("A", "B", "C", "D", "E").log();

        // when
        flux.subscribe(
                names::add,
                error -> {},
                () -> {},
                subscription -> subscription.request(1)
        );

        // then
        assertThat(names.size()).isEqualTo(1);
        assertThat(names.get(0)).isEqualTo("A");
    }

}

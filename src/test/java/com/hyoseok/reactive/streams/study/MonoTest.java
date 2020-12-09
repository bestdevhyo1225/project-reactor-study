package com.hyoseok.reactive.streams.study;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Signal;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Mono 테스트")
class MonoTest {

    @Test
    void mono_just() {
        // given
        int initialCapacity = 4;
        List<Signal<Integer>> signals = new ArrayList<>(initialCapacity);

        Mono<Integer> mono = Mono.just(1).log()
                .doOnEach(integerSignal -> {
                    signals.add(integerSignal);
                    System.out.println("Signal : " + integerSignal);
                });

        Integer[] result = new Integer[1];

        // when
        mono.subscribe(integer -> result[0] = integer);

        // then
        assertThat(signals.size()).isEqualTo(2);
        assertThat(signals.get(0).getType().name()).isEqualTo("ON_NEXT");
        assertThat(signals.get(1).getType().name()).isEqualTo("ON_COMPLETE");
        assertThat(result[0].intValue()).isEqualTo(1);
    }

    @Test
    void mono_empty() {
        Mono<String> mono = Mono.empty();

        assertThat(mono.block()).isNull();
    }

}

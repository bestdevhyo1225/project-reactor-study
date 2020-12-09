package com.hyoseok.reactive.streams.study;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Flux 테스트")
class FluxTest {

    @Test
    void flux_just() {
        // given
        List<String> names = new ArrayList<>();
        Flux<String> flux = Flux.just("A", "B").log();

        // when
        flux.subscribe(names::add);

        // then
        assertThat(names.get(0)).isEqualTo("A");
        assertThat(names.get(1)).isEqualTo("B");
    }

    @Test
    void flux_range() {
        // given
        List<Integer> numbers = new ArrayList<>();
        Flux<Integer> flux = Flux.range(1, 3).log();

        // when
        flux.subscribe(numbers::add);

        // then
        assertThat(numbers.size()).isEqualTo(3);
        assertThat(numbers.get(0)).isEqualTo(1);
        assertThat(numbers.get(1)).isEqualTo(2);
        assertThat(numbers.get(2)).isEqualTo(3);
    }

    @Test
    void flux_fromArray() {
        // given
        List<String> names = new ArrayList<>();
        Flux<String> flux = Flux.fromArray(new String[]{ "AA", "BB", "CC" }).log();

        // when
        flux.subscribe(names::add);

        // then
        assertThat(names.get(0)).isEqualTo("AA");
        assertThat(names.get(1)).isEqualTo("BB");
        assertThat(names.get(2)).isEqualTo("CC");
    }

    @Test
    void flux_fromIterable() {
        // given
        List<String> names = new ArrayList<>();
        Flux<String> flux = Flux.fromIterable(Arrays.asList("A-A", "B-B", "C-C")).log();

        // when
        flux.subscribe(names::add);

        // then
        assertThat(names.get(0)).isEqualTo("A-A");
        assertThat(names.get(1)).isEqualTo("B-B");
        assertThat(names.get(2)).isEqualTo("C-C");
    }

    @Test
    void flux_empty() {
        // given
        List<String> names = new ArrayList<>();
        Flux<String> flux = Flux.empty();

        // when
        flux.subscribe(names::add);

        // then
        assertThat(names.size()).isZero();
    }

    @Test
    void flux_lazy() {
        int start = 1;
        int count = 9;

        Flux<Integer> flux = Flux.range(start, count)
                .flatMap(n -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return Mono.just(3 * n);
                })
                .log();

        System.out.println("----- 아직 구독을 하지 않았기 때문에 데이터를 전달하지 않는다... -----");

        flux.subscribe(
                System.out::println,
                null,
                () -> System.out.println("데이터 수신 완료")
        );

        System.out.println("----- 전체 완료... ----");
    }

}

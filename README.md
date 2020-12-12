# Reactive Streams Study

- [x] Publisher, Subscriber, Subscription 구현체 직접 만들기

- [x] Publisher 구현체인 Flux, Mono 사용해보기

- [x] Subscriber 공부하고, 사용해보기

<br>

## Reactive Streams 정리

> request(unbounded)에서 unbounded가 의미하는 것은?

- 내부 로직에서 `MAX`값을 설정하게 된다는 것을 의미한다.
- `Back-Pressure`기능을 활용하지 않고, 모든 데이터를 요청하는 것이다.
- `Back-Pressure`기능을 사용해서, `request(n)`에서 `n`을 변경하고 싶다면, `subscriptionConsumer`를 넘겨줘야 한다. 

<br>

## 참고 자료

- [Reactor 3 Reference Guide](https://projectreactor.io/docs/core/release/reference/)

- [Project Reactor 시리즈](https://brunch.co.kr/@springboot/152)


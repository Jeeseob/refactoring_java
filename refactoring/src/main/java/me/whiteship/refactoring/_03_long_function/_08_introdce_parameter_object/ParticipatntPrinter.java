package me.whiteship.refactoring._03_long_function._08_introdce_parameter_object;

/**
 * Refactor 중 Introduce Parameter Object를 사용하면,
 * 해당 매개변수들을 값으로 갖는 Record를 만들어 준다.
 */
public record ParticipatntPrinter(int totalNumberOfEvents, Participant p) {
}
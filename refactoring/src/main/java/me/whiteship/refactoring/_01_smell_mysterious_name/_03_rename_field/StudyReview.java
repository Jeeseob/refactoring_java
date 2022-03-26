package me.whiteship.refactoring._01_smell_mysterious_name._03_rename_field;

// Record는 java 14 이상 버젼부터 사용가능하다.

// Record - 특정 데이터와 관련있는 필드들을 묶어놓는 자료구조이다.

/** Record는 java 14 이상 버젼부터 사용가능
 *
 *  Record - immutable(불변)이 특징인 자료구조
 *      - 특정 데이터와 관련있는 필드들을 묶어놓는 자료구조이다.
 *      - 파이썬의 Dictionary와 비슷한 형태이다.
 *
 *      private final + setter가 없는 객체와 비슷하다.
 *
 *      자동으로 constructor, getter, toString, hashCode, Equals 등의 함수가 구현된다.
 */

public record StudyReview(String reviewer, String review) {
}

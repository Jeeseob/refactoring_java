package me.whiteship.refactoring._03_long_function._09_preserve_whole_object;

import java.util.HashMap;
import java.util.Map;

public record Participant(String username, Map<Integer, Boolean> homework) {
    public Participant(String username) {
        this(username, new HashMap<>());
    }

    public void setHomeworkDone(int index) {
        this.homework.put(index, true);
    }

    /**
     * Refactor에서 move Instance Method를 사용하면, 함수를 다른 객체로 이동할 수 있따.
     * @param studyDashBoard
     * @return
     */
    double getRate(int studyDashBoard) {
        long count = homework().values().stream()
                .filter(v -> v == true)
                .count();
        return (double) (count * 100 / studyDashBoard);
    }
}

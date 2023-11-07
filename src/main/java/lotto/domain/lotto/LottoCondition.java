package lotto.domain.lotto;

import java.util.List;

public enum LottoCondition {

    LENGTH(6),
    MIN_RANGE(1),
    MAX_RANGE(45);

    private final int value;

    LottoCondition(final int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

    public static boolean isInvalidLength(final List<Integer> numbers) {
        return numbers.size() != LENGTH.value;
    }

    public static boolean isInvalidRange(final List<Integer> numbers) {
        return numbers.stream()
                .anyMatch(LottoCondition::isOutOfRange);
    }

    private static boolean isOutOfRange(final Integer number) {
        return MIN_RANGE.value > number || MAX_RANGE.value < number;
    }
}

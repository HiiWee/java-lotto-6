package lotto.domain.lotto;

import java.util.List;

public enum LottoCondition {

    LOTTO_LENGTH(6),
    START_NUMBER(1),
    END_NUMBER(45);

    private final int value;

    LottoCondition(final int value) {
        this.value = value;
    }

    public static boolean isInvalidNumbers(final List<Integer> numbers) {
        return isOutOfLength(numbers) || isOutOfRange(numbers);
    }

    private static boolean isOutOfLength(final List<Integer> numbers) {
        return numbers.size() != LOTTO_LENGTH.value;
    }

    private static boolean isOutOfRange(final List<Integer> numbers) {
        return numbers.stream()
                .anyMatch(number -> number < START_NUMBER.value || number > END_NUMBER.value);
    }

    public int value() {
        return value;
    }
}

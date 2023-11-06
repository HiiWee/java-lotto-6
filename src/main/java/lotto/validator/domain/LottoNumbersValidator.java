package lotto.validator.domain;

import java.util.List;
import lotto.domain.lotto.LottoCondition;
import lotto.validator.domain.exception.DomainExceptionMessage;

public class LottoNumbersValidator {

    private LottoNumbersValidator() {
    }

    public static void validate(final List<Integer> numbers) {
        validateLength(numbers);
        validateDuplicates(numbers);
    }

    private static void validateLength(final List<Integer> numbers) {
        if (LottoCondition.isInvalidNumbers(numbers)) {
            throw DomainExceptionMessage.INVALID_LOTTO_NUMBERS.create();
        }
    }

    private static void validateDuplicates(final List<Integer> numbers) {
        if (isDuplicates(numbers)) {
            throw DomainExceptionMessage.DUPLICATES_LOTTO_NUMBERS.create();
        }
    }

    private static boolean isDuplicates(final List<Integer> numbers) {
        return numbers.stream()
                .distinct()
                .count() != numbers.size();
    }
}

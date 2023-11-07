package lotto.validator.domain;

import java.util.List;
import lotto.domain.lotto.LottoCondition;
import lotto.validator.domain.exception.DomainExceptionMessage;

public class LottoNumbersValidator {

    private LottoNumbersValidator() {
    }

    public static void validate(List<Integer> numbers) {
        validateLength(numbers);
        validateRange(numbers);
        validateDuplicates(numbers);
    }

    private static void validateLength(final List<Integer> numbers) {
        if (LottoCondition.isInvalidLength(numbers)) {
            throw DomainExceptionMessage.INVALID_LOTTO_LENGTH.create();
        }
    }

    private static void validateRange(final List<Integer> numbers) {
        if (LottoCondition.isInvalidRange(numbers)) {
            throw DomainExceptionMessage.OUT_OF_RANGE_LOTTO_NUMBER.create();
        }
    }

    private static void validateDuplicates(final List<Integer> numbers) {
        if (isDuplicates(numbers)) {
            throw DomainExceptionMessage.DUPLICATES_LOTTO_NUMBER.create();
        }
    }

    private static boolean isDuplicates(final List<Integer> numbers) {
        return numbers.stream()
                .distinct()
                .count() != numbers.size();
    }
}

package lotto.validator.domain;

import java.util.List;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoCondition;
import lotto.validator.domain.exception.DomainExceptionMessage;

public class BonusNumberValidator {

    private BonusNumberValidator() {
    }

    public static void validate(final Lotto winningNumbers, final int bonusNumber) {
        validateRange(bonusNumber);
        validateDuplicates(winningNumbers, bonusNumber);
    }

    private static void validateRange(final int bonusNumber) {
        if (LottoCondition.isOutOfRange(List.of(bonusNumber))) {
            throw DomainExceptionMessage.OUT_OF_RANGE_BONUS_NUMBER.create();
        }
    }

    private static void validateDuplicates(final Lotto winningNumbers, final int bonusNumber) {
        if (winningNumbers.contains(bonusNumber)) {
            throw DomainExceptionMessage.DUPLICATES_LOTTO_NUMBERS.create();
        }
    }
}

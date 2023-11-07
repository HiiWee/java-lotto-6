package lotto.validator.domain;

import java.util.List;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoCondition;
import lotto.validator.domain.exception.DomainExceptionMessage;

public class BonusNumberValidator {

    private BonusNumberValidator() {
    }

    public static void validate(final Lotto lotto, final int bonusNumber) {
        validateRange(bonusNumber);
        validateDuplicates(lotto, bonusNumber);
    }

    private static void validateRange(final int bonusNumber) {
        if (LottoCondition.isInvalidRange(List.of(bonusNumber))) {
            throw DomainExceptionMessage.OUT_OF_RANGE_LOTTO_NUMBER.create();
        }
    }

    private static void validateDuplicates(final Lotto lotto, final int bonusNumber) {
        if (lotto.contains(bonusNumber)) {
            throw DomainExceptionMessage.DUPLICATES_LOTTO_NUMBER.create();
        }
    }
}

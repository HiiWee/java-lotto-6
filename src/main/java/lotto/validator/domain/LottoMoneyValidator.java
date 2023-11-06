package lotto.validator.domain;

import lotto.domain.LottoMoneyCondition;
import lotto.validator.domain.exception.DomainExceptionMessage;

public class LottoMoneyValidator {

    private LottoMoneyValidator() {
    }

    public static void validate(final int money) {
        if (LottoMoneyCondition.isInvalidMoney(money)) {
            throw DomainExceptionMessage.INVALID_INPUT_MONEY_EXCEPTION.create();
        }
    }
}

package lotto.validator.domain;

import lotto.domain.money.LottoMoneyCondition;
import lotto.validator.domain.exception.DomainExceptionMessage;

public class LottoMoneyValidator {

    private LottoMoneyValidator() {
    }

    public static void validate(final int money) {
        if (LottoMoneyCondition.isInvalidMoney(money)) {
            throw DomainExceptionMessage.INVALID_LOTTO_MONEY.create();
        }
    }
}

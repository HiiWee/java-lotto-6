package lotto.domain.money;

import lotto.validator.domain.LottoMoneyValidator;

public record LottoMoney(int money) {

    public static LottoMoney creatFrom(final int money) {
        LottoMoneyValidator.validate(money);
        return new LottoMoney(money);
    }

    public int calculateBuyingCount() {
        return money / LottoMoneyCondition.MONEY_UNIT.value();
    }
}

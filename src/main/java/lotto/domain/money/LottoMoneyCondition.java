package lotto.domain.money;

public enum LottoMoneyCondition {

    MONEY_UNIT(1_000),
    START_RANGE(1_000),
    END_RANGE(1_000_000);

    private final int value;

    LottoMoneyCondition(final int value) {
        this.value = value;
    }

    public static boolean isInvalidMoney(final int money) {
        return isNotInRange(money) || isInvalidUnit(money);
    }

    private static boolean isNotInRange(final int money) {
        return START_RANGE.value > money || END_RANGE.value < money;
    }

    private static boolean isInvalidUnit(final int money) {
        return money % MONEY_UNIT.value != 0;
    }

    public int value() {
        return value;
    }
}

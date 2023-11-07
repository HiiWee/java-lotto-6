package lotto.domain.money;

public enum LottoMoneyCondition {

    MONEY_UNIT(1_000),
    MIN_RANGE(1_000),
    MAX_RANGE(1_000_000);

    private final int value;

    LottoMoneyCondition(final int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

    public static boolean isInvalidMoney(final int money) {
        return isInvalidUnit(money) || isOutOfRange(money);
    }

    private static boolean isInvalidUnit(final int money) {
        return money % MONEY_UNIT.value != 0;
    }

    private static boolean isOutOfRange(final int money) {
        return MIN_RANGE.value > money || MAX_RANGE.value < money;
    }
}

package lotto.domain.money;

public record LottoMoney(int value) {

    private static final String INVALID_MONEY_VALUE = "[ERROR] 금액은 천원이상 만원 이하의 1000원 단위의 금액이어야 합니다.";

    public static LottoMoney from(final int value) {
        validate(value);
        return new LottoMoney(value);
    }

    private static void validate(final int value) {
        if (MoneyCondition.isInvalidValue(value)) {
            throw new IllegalArgumentException(INVALID_MONEY_VALUE);
        }
    }

    public int createBuyingCount() {
        return value % MoneyCondition.MONEY_UNIT.value();
    }
}

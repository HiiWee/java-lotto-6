package lotto.validator.domain.exception;

public enum DomainExceptionMessage {
    INVALID_INPUT_MONEY_EXCEPTION("[ERROR] 구입 금액은 천원 이상 백만원 이하의 1000원 단위의 금액이어야 합니다."),
    INVALID_LOTTO_NUMBERS("[ERROR] 로또 번호는 1~45사이의 6개의 숫자로만 이루어져야 합니다."),
    DUPLICATES_LOTTO_NUMBERS("[ERROR] 로또 번호는 중복되면 안됩니다.");

    private final String message;

    DomainExceptionMessage(final String message) {
        this.message = message;
    }

    public DomainIllegalArgumentException create() {
        return new DomainIllegalArgumentException(message);
    }

    public String message() {
        return message;
    }
}

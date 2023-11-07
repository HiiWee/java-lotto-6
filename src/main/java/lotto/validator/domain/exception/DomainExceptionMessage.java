package lotto.validator.domain.exception;

public enum DomainExceptionMessage {

    INVALID_LOTTO_MONEY("[ERROR] 금액은 천원 이상 백만원 이하의 1000원 단위의 금액이어야 합니다."),
    INVALID_LOTTO_LENGTH("[ERROR] 로또 숫자 길이는 6이어야 합니다."),
    OUT_OF_RANGE_LOTTO_NUMBER("[ERROR] 로또 숫자의 범위는 1 ~ 45사이여야 합니다."),
    DUPLICATES_LOTTO_NUMBER("[ERROR] 로또 숫자는 중복될 수 없습니다.");

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

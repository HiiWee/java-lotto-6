package lotto.validator.input.exception;

public enum InputExceptionMessage {

    NUMBER_FORMAT_EXCEPTION("[ERROR] 숫자만 입력해야 합니다.");

    private final String message;

    InputExceptionMessage(final String message) {
        this.message = message;
    }

    public InputIllegalArgumentException create() {
        return new InputIllegalArgumentException(message);
    }
}

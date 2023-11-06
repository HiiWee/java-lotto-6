package lotto.validator;

import lotto.validator.input.InputExceptionMessage;

public class InputCommonValidator {

    private InputCommonValidator() {
    }

    public static void validateNumber(final String inputPrice) {
        try {
            Integer.parseInt(inputPrice);
        } catch (NumberFormatException e) {
            throw InputExceptionMessage.NUMBER_FORMAT_EXCEPTION.create();
        }
    }
}

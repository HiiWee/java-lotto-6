package lotto.validator.input;

import java.util.List;
import lotto.validator.input.exception.InputExceptionMessage;

public class InputCommonValidator {

    private InputCommonValidator() {
    }

    public static void validateNumber(final String number) {
        validateSingleNumber(number);
    }

    public static void validateNumbers(final List<String> numbers) {
        numbers.forEach(InputCommonValidator::validateSingleNumber);
    }

    private static void validateSingleNumber(final String inputPrice) {
        try {
            Integer.parseInt(inputPrice);
        } catch (NumberFormatException e) {
            throw InputExceptionMessage.NUMBER_FORMAT_EXCEPTION.create();
        }
    }
}

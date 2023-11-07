package lotto.view;

import java.util.List;
import lotto.resolver.ExceptionResolver;

public class InputView {

    private static final String INPUT_BUYING_PRICE_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String INPUT_WINNING_NUMBERS_MESSAGE = "당첨 번호를 입력해 주세요.";
    private static final String INPUT_BONUS_NUMBER_MESSAGE = "보너스 번호를 입력해 주세요.";
    private static final String DELIMITER = ",";

    public int inputBuyingPrice() {
        printMessage(INPUT_BUYING_PRICE_MESSAGE);
        return ExceptionResolver.resolveInput(InputNumberReader::readNumber);
    }

    public List<Integer> inputWinningNumbers() {
        printMessage(INPUT_WINNING_NUMBERS_MESSAGE);
        return ExceptionResolver.resolveInputWithParameter(InputNumberReader::readNumbers, DELIMITER);
    }

    public int inputBonusNumber() {
        System.out.println(INPUT_BONUS_NUMBER_MESSAGE);
        return ExceptionResolver.resolveInput(InputNumberReader::readNumber);
    }

    private void printMessage(final String message) {
        System.out.println(message);
    }
}

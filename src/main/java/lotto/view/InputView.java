package lotto.view;

import lotto.resolver.ExceptionResolver;

public class InputView {

    private static final String INPUT_BUYING_PRICE_MESSAGE = "구입금액을 입력해 주세요.";

    public int inputBuyingPrice() {
        System.out.println(INPUT_BUYING_PRICE_MESSAGE);
        return ExceptionResolver.resolveInput(InputNumberReader::readNumber);
    }
}

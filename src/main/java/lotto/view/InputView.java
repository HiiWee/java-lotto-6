package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;

public class InputView {

    private static final String INPUT_PRICE_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String INPUT_WINNING_NUMBERS_MESSAGE = "당첨 번호를 입력해 주세요.";
    private static final String SPLIT_DELIMITER = ",";
    private static final String INPUT_BONUS_NUMBER_MESSAGE = "보너스 번호를 입력해 주세요.";

    public String inputPrice() {
        System.out.println(INPUT_PRICE_MESSAGE);
        return readLine().trim();
    }

    public List<String> inputWinningNumber() {
        System.out.println(INPUT_WINNING_NUMBERS_MESSAGE);
        return Arrays.stream(readLine().split(SPLIT_DELIMITER))
                .map(String::trim)
                .toList();
    }

    private String readLine() {
        return Console.readLine();
    }

    public String inputBonusNumber() {
        System.out.println(INPUT_BONUS_NUMBER_MESSAGE);
        return readLine().trim();
    }
}

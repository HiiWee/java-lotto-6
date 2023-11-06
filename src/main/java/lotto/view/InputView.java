package lotto.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    public String inputPrice() {
        System.out.println("구입금액을 입력해 주세요.");
        return readLine().trim();
    }

    private String readLine() {
        return Console.readLine();
    }
}

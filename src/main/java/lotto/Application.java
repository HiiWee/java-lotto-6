package lotto;

import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {
    public static void main(String[] args) {
        LottoGame lottoGame = new LottoGame(new InputView(), new OutputView());
        lottoGame.startGame();
    }
}

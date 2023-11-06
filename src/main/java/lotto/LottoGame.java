package lotto;

import lotto.domain.LottoMachine;
import lotto.resolver.ExceptionResolver;
import lotto.validator.InputCommonValidator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoGame {

    private final InputView inputView;
    private final OutputView outputView;
    private final LottoMachine lottoMachine;

    public LottoGame(final InputView inputView, final OutputView outputView, final LottoMachine lottoMachine) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoMachine = lottoMachine;
    }

    public void startGame() {
        ExceptionResolver.resolveProcess(LottoGame::buyLotto, this);
    }

    private void buyLotto() {
        String inputPrice = inputView.inputPrice();
        InputCommonValidator.validateNumber(inputPrice);
    }

}

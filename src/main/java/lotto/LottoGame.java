package lotto;

import lotto.domain.LottoMachine;
import lotto.dto.BuyingResults;
import lotto.generator.RandomNumbersGenerator;
import lotto.resolver.ExceptionResolver;
import lotto.validator.input.InputCommonValidator;
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
        ExceptionResolver.resolveProcess(LottoGame::buyLottos, this);
        printBuyingLottos();
    }

    private void buyLottos() {
        String inputPrice = ExceptionResolver.resolveInput(this::inputPrice);
        int price = Integer.parseInt(inputPrice);
        lottoMachine.buyLottos(price, RandomNumbersGenerator.getGenerateSupplier());
    }

    private void printBuyingLottos() {
        BuyingResults buyingResults = lottoMachine.createBuyingResults();
        String resultMessage = buyingResults.createTotalResultMessage();
        outputView.printBuyingLottos(buyingResults.getBuyingCount(), resultMessage);
    }

    private String inputPrice() {
        String inputPrice = inputView.inputPrice();
        InputCommonValidator.validateNumber(inputPrice);
        return inputPrice;
    }

}

package lotto;

import java.util.List;
import lotto.domain.LottoMachine;
import lotto.dto.BuyingResults;
import lotto.dto.WinningResults;
import lotto.generator.RandomNumbersGenerator;
import lotto.generator.WinningMessageGenerator;
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
        ExceptionResolver.resolveProcess(LottoGame::inputWinningLotto, this);
        printWinningResults();
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

    private void inputWinningLotto() {
        List<Integer> numbers = ExceptionResolver.resolveInput(this::inputWinningNumbers);
        int bonusNumber = ExceptionResolver.resolveInput(this::inputBonusNumber);
        lottoMachine.addWinningLotto(numbers, bonusNumber);
    }

    private void printWinningResults() {
        WinningResults winningResults = lottoMachine.createWinningResults();
        String winningResultsMessage = WinningMessageGenerator.generate(winningResults);
        outputView.printWinningResults(winningResultsMessage);
    }

    private List<Integer> inputWinningNumbers() {
        List<String> numbers = inputView.inputWinningNumber();
        InputCommonValidator.validateNumbers(numbers);
        return convertToNumbers(numbers);
    }

    private int inputBonusNumber() {
        String number = inputView.inputBonusNumber();
        InputCommonValidator.validateNumber(number);
        return Integer.parseInt(number);
    }

    private List<Integer> convertToNumbers(final List<String> numbers) {
        return numbers.stream()
                .map(Integer::parseInt)
                .toList();
    }

    private String inputPrice() {
        String inputPrice = inputView.inputPrice();
        InputCommonValidator.validateNumber(inputPrice);
        return inputPrice;
    }
}

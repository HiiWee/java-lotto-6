package lotto.domain;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import java.util.function.Supplier;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoRepository;
import lotto.domain.lotto.LottoRewardCondition;
import lotto.domain.lotto.Lottos;
import lotto.domain.lotto.WinningLotto;
import lotto.dto.BuyingResults;
import lotto.dto.WinningResults;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoMachineTest {

    LottoRepository lottoRepository;
    LottoMachine lottoMachine;

    @BeforeEach
    void setUp() {
        lottoRepository = new LottoRepository();
        lottoMachine = new LottoMachine(lottoRepository);
    }

    @DisplayName("로또를 구매할 수 있다.")
    @Test
    void buyLottos() {
        // given
        int price = 2000;
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        Supplier<List<Integer>> stupLottoNumberSupplier = () -> numbers;
        WinningLotto winningLotto = WinningLotto.createFrom(numbers, 10);

        // when
        lottoMachine.buyLottos(price, stupLottoNumberSupplier);
        Lottos lottos = lottoRepository.findBuyingLottos().get();
        List<LottoRewardCondition> rewards = lottos.compareWinningLotto(winningLotto);

        // then
        assertAll(
                () -> assertThat(rewards).containsExactly(
                        LottoRewardCondition.FIRST_REWARD,
                        LottoRewardCondition.FIRST_REWARD)
        );
    }

    @DisplayName("구매한 로또 결과를 생성할 수 있다.")
    @Test
    void createBuyingResults() {
        // given
        int expectBuyingCount = 2;
        String expectResultMessage = """
                [1, 2, 3, 4, 5, 6]
                [1, 2, 3, 4, 5, 6]""";
        int price = 2000;
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        Supplier<List<Integer>> stupLottoNumberSupplier = () -> numbers;
        lottoMachine.buyLottos(price, stupLottoNumberSupplier);

        // when
        BuyingResults buyingResults = lottoMachine.createBuyingResults();
        int actualBuyingCount = buyingResults.getBuyingCount();
        String actualResultMessage = buyingResults.createTotalResultMessage();

        // then
        assertAll(
                () -> assertThat(actualBuyingCount).isEqualTo(expectBuyingCount),
                () -> assertThat(actualResultMessage).isEqualTo(expectResultMessage)
        );
    }

    @DisplayName("당첨 로또를 추가할 수 있다.")
    @Test
    void addWinningLotto() {
        // given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        int bonusNumber = 7;

        // when
        lottoMachine.addWinningLotto(numbers, bonusNumber);
        WinningLotto findWinningLotto = lottoRepository.findWinningLotto().get();
        LottoRewardCondition result = findWinningLotto.findCompareResult(
                Lotto.createFrom(List.of(1, 2, 3, 4, 5, 7)));

        // then
        assertThat(result).isEqualTo(LottoRewardCondition.SECOND_REWARD);
    }

    @DisplayName("당첨 결과를 받아볼 수 있다.")
    @Test
    void createWinningResults() {
        // given
        double expectProfitRatio = LottoRewardCondition.FIRST_REWARD.getPrizeMoney() * 2 / 2000.0 * 100;
        List<Integer> expectedWinningCounts = List.of(0, 0, 0, 0, 2);
        lottoMachine.buyLottos(2000, () -> List.of(1, 2, 3, 4, 5, 6));
        lottoMachine.addWinningLotto(List.of(1, 2, 3, 4, 5, 6), 7);

        // when
        WinningResults winningResults = lottoMachine.createWinningResults();
        List<Integer> actualWinningCounts = winningResults.calculateWinningCounts();
        double actualProfitRatio = winningResults.calculateProfitRatio();

        // then
        assertAll(
                () -> assertThat(actualWinningCounts).isEqualTo(expectedWinningCounts),
                () -> assertThat(expectProfitRatio).isEqualTo(actualProfitRatio)
        );
    }
}
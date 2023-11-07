package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
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
        WinningLotto winningLotto = WinningLotto.createFrom(List.of(1, 2, 3, 4, 5, 6), 7);
        Supplier<List<Integer>> stubGenerator = () -> List.of(1, 2, 3, 4, 5, 6);
        int price = 2000;

        // when
        lottoMachine.buyLottos(stubGenerator, price);
        Lottos buyingLottos = lottoRepository.findBuyingLottos().get();
        List<LottoRewardCondition> compareResults = buyingLottos.createCompareResults(winningLotto);

        // then
        assertThat(compareResults).containsExactly(
                LottoRewardCondition.FIRST_WINNING,
                LottoRewardCondition.FIRST_WINNING
        );
    }

    @DisplayName("로또 구매 결과를 생성할 수 있다.")
    @Test
    void createBuyingResults() {
        // given
        String expectResultsMessage = "[1, 2, 3, 4, 5, 6]";
        Supplier<List<Integer>> stubGenerator = () -> List.of(1, 2, 3, 4, 5, 6);
        int price = 1000;
        lottoMachine.buyLottos(stubGenerator, price);

        // when
        BuyingResults buyingResults = lottoMachine.createBuyingResults();
        String resultsMessage = buyingResults.createMessage();

        // then
        assertThat(resultsMessage).isEqualTo(expectResultsMessage);
    }

    @DisplayName("당첨 로또를 추가할 수 있다.")
    @Test
    void addWinningLotto() {
        // given
        Lotto lotto = Lotto.createFrom(List.of(1, 2, 3, 4, 5, 6));

        // when
        lottoMachine.addWinningLotto(List.of(1, 2, 3, 4, 5, 6), 10);
        WinningLotto winningLotto = lottoRepository.findWinningLotto().get();
        LottoRewardCondition compareResult = winningLotto.createCompareResult(lotto);

        // then
        assertThat(compareResult).isEqualTo(LottoRewardCondition.FIRST_WINNING);
    }

    @DisplayName("당첨 결과를 생성할 수 있다.")
    @Test
    void createWinningResults() {
        // given
        double expectProfitRatio = LottoRewardCondition.FIRST_WINNING.prizeMoney() * 2 / 2000 * 100;
        Lotto lotto = Lotto.createFrom(List.of(1, 2, 3, 4, 5, 6));
        lottoMachine.buyLottos(() -> List.of(1, 2, 3, 4, 5, 6), 2000);
        lottoMachine.addWinningLotto(List.of(1, 2, 3, 4, 5, 6), 10);

        // when
        WinningResults winningResults = lottoMachine.createWinningResults();
        double profitRatio = winningResults.calculateProfitRatio();
        List<Integer> rewardCounts = winningResults.createRewardCounts();

        // then
        assertAll(
                () -> assertThat(profitRatio).isEqualTo(expectProfitRatio),
                () -> assertThat(rewardCounts).containsExactly(0, 0, 0, 0, 2) // 5, 4, 3, 2, 1등
        );
    }
}
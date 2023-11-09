package lotto.domain.result;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import lotto.domain.lotto.Lottos;
import lotto.domain.lotto.WinningLotto;
import lotto.domain.money.LottoMoney;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningResultsTest {

    @DisplayName("당첨로또와 구매한 로또를 비교하여 결과를 생성할 수 있다.")
    @Test
    void getSortedRewardCounts() {
        // given
        WinningLotto winningLotto = WinningLotto.createFrom(List.of(1, 2, 3, 4, 5, 6), 7);
        Lottos lottos = Lottos.createFrom(() -> List.of(1, 2, 3, 4, 5, 6), LottoMoney.from(2000));

        // when
        WinningResults winningResults = WinningResults.createFrom(lottos, winningLotto);

        // then
        assertAll(
                () -> assertThat(winningResults.getSortedRewardCounts()).containsExactly(0, 0, 0, 0, 2),
                () -> assertThat(winningResults.getProfitRatio()).isEqualTo(4_000_000_000L / 2000.0 * 100)
        );
    }

}
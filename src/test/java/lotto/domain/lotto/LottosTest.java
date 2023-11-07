package lotto.domain.lotto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import lotto.domain.money.LottoMoney;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottosTest {

    @DisplayName("로또 집합의 당첨 결과 목록을 생성하고 반환합니다.")
    @Test
    void createCompareResults() {
        // given
        WinningLotto winningLotto = WinningLotto.createFrom(List.of(1, 2, 3, 4, 5, 6), 7);
        Lottos lottos = Lottos.createFrom(() -> List.of(1, 2, 3, 4, 5, 6), LottoMoney.creatFrom(2000));

        // when
        List<LottoRewardCondition> compareResults = lottos.createCompareResults(winningLotto);

        // then
        assertThat(compareResults).containsExactly(
                LottoRewardCondition.FIRST_WINNING,
                LottoRewardCondition.FIRST_WINNING
        );
    }
}
package lotto.domain.lotto;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import java.util.function.Supplier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottosTest {

    @DisplayName("로또 집합에 대한 당첨 결과 목록들을 만들 수 있다.")
    @Test
    void compareWinningLotto() {
        // given
        Supplier<List<Integer>> supplier1 = () -> List.of(1, 2, 3, 4, 5, 6);
        Supplier<List<Integer>> supplier2 = () -> List.of(1, 2, 3, 4, 5, 7);
        Lottos lottos1 = Lottos.createFrom(2, supplier1);
        Lottos lottos2 = Lottos.createFrom(2, supplier2);
        int bonusNumber = 7;
        WinningLotto winningLotto = WinningLotto.createFrom(supplier1.get(), bonusNumber);

        // when
        List<LottoRewardCondition> rewards1 = lottos1.compareWinningLotto(winningLotto);
        List<LottoRewardCondition> rewards2 = lottos2.compareWinningLotto(winningLotto);

        // then
        assertAll(
                () -> assertThat(rewards1).containsExactly(
                        LottoRewardCondition.FIRST_REWARD,
                        LottoRewardCondition.FIRST_REWARD),
                () -> assertThat(rewards2).containsExactly(
                        LottoRewardCondition.SECOND_REWARD,
                        LottoRewardCondition.SECOND_REWARD
                )
        );

    }

}
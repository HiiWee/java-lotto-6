package lotto.domain.lotto;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoRewardConditionTest {

    @DisplayName("로또 당첨 개수에 따른 결과를 찾을 수 있다.")
    @Test
    void findReward() {
        // given
        int firstWinningCount = 6;
        int secondWinningCount = 5;

        // when
        LottoRewardCondition firstReward = LottoRewardCondition.findReward(firstWinningCount, false);
        LottoRewardCondition secondReward = LottoRewardCondition.findReward(secondWinningCount, true);

        // then
        assertAll(
                () -> assertThat(firstReward).isEqualTo(LottoRewardCondition.FIRST_REWARD),
                () -> assertThat(secondReward).isEqualTo(LottoRewardCondition.SECOND_REWARD)
        );
    }
}
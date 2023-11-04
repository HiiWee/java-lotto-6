package lotto.domain.lotto;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoRewardConditionTest {

    @DisplayName("두 로또의 비교 결과를 찾을 수 있습니다.")
    @Test
    void getCompareResult() {
        // given
        int secondWinningCount = 5;
        int firstWinningCount = 6;

        // when
        LottoRewardCondition reward1 = LottoRewardCondition.findLottoReward(firstWinningCount, false);
        LottoRewardCondition reward2 = LottoRewardCondition.findLottoReward(secondWinningCount, true);

        // then

        assertAll(
                () -> assertThat(reward1).isEqualTo(LottoRewardCondition.FIRST_WINNER),
                () -> assertThat(reward2).isEqualTo(LottoRewardCondition.SECOND_WINNER)
        );
    }
}
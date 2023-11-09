package lotto.generator;

import java.util.List;
import lotto.domain.lotto.LottoRewardCondition;
import lotto.domain.result.WinningResults;
import lotto.dto.WinningReward;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningResultMessageGeneratorTest {

    @DisplayName("결과 메시지를 생성할 수 있다.")
    @Test
    void generate() {
        // given
        String expectWinningMessage = """
                3개 일치 (5,000원) - 1개
                4개 일치 (50,000원) - 0개
                5개 일치 (1,500,000원) - 0개
                5개 일치, 보너스 볼 일치 (30,000,000원) - 0개
                6개 일치 (2,000,000,000원) - 1개
                총 수익률은 100,000,250.0%입니다.""";
        WinningReward winningReward = new WinningReward(List.of(1, 0, 0, 0, 1), 100_000_250.0);

        // when
        String actualWinningMessage = WinningResultMessageGenerator.generate(winningReward);

        // then
        Assertions.assertThat(actualWinningMessage).isEqualTo(expectWinningMessage);
    }
}
package lotto.generator;

import java.util.List;
import lotto.domain.lotto.LottoRewardCondition;
import lotto.dto.WinningResults;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningResultsMessageGeneratorTest {

    @DisplayName("당첨 결과 메시지를 생성할 수 있다.")
    @Test
    void generate_winningResultMessage() {
        // given
        String expectMessage = """
                3개 일치 (5,000원) - 1개
                4개 일치 (50,000원) - 0개
                5개 일치 (1,500,000원) - 0개
                5개 일치, 보너스 볼 일치 (30,000,000원) - 1개
                6개 일치 (2,000,000,000원) - 0개
                총 수익률은 1,000,166.7%입니다.
                """;
        WinningResults winningResults = WinningResults.createFrom(List.of(
                LottoRewardCondition.SECOND_WINNING,
                LottoRewardCondition.FAIL,
                LottoRewardCondition.FIFTH_WINNING
        ));

        // when
        String message = WinningResultsMessageGenerator.generate(winningResults);

        // then
        Assertions.assertThat(message).isEqualTo(expectMessage);
    }

}
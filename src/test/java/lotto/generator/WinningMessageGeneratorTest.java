package lotto.generator;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import lotto.domain.lotto.LottoRewardCondition;
import lotto.dto.WinningResults;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningMessageGeneratorTest {


    @DisplayName("당첨 결과 메시지를 생성할 수 있다.")
    @Test
    void createWinningResultsMessage() {
        // given
        String expectResultMessage = """
                3개 일치 (5,000원) - 0개
                4개 일치 (50,000원) - 0개
                5개 일치 (1,500,000원) - 0개
                5개 일치, 보너스 볼 일치 (30,000,000원) - 1개
                6개 일치 (2,000,000,000원) - 1개
                총 수익률은 101,500,000.0%입니다.
                """;
        WinningResults winningResults = WinningResults.createFrom(
                List.of(LottoRewardCondition.FIRST_REWARD, LottoRewardCondition.SECOND_REWARD));

        // when
        String actualResultMessage = WinningMessageGenerator.generate(winningResults);

        // then
        assertThat(actualResultMessage).isEqualTo(expectResultMessage);
    }
}
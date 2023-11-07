package lotto.domain.lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.util.List;
import lotto.validator.domain.exception.DomainExceptionMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningLottoTest {

    @DisplayName("보너스 번호가 로또 번호와 중복이면 예외가 발생한다.")
    @Test
    void createWinningLotto_exception_duplicateBonusNumber() {
        // given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        int bonusNumber = 5;

        // when & then
        assertThatIllegalArgumentException().isThrownBy(() -> WinningLotto.createFrom(numbers, bonusNumber))
                .withMessageContaining(DomainExceptionMessage.DUPLICATES_LOTTO_NUMBER.message());
    }

    @DisplayName("보너스 번호가 1~45의 범위를 벗어나면 예외가 발생한다.")
    @Test
    void createWinningLotto_exception_outOfRangeBonusNumber() {
        // given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        int bonusNumber = 46;

        // when & then
        assertThatIllegalArgumentException().isThrownBy(() -> WinningLotto.createFrom(numbers, bonusNumber))
                .withMessageContaining(DomainExceptionMessage.OUT_OF_RANGE_LOTTO_NUMBER.message());
    }

    @DisplayName("단일 로또에 대한 비교 결과를 생성합니다.")
    @Test
    void createCompareResult() {
        // given
        WinningLotto winningLotto = WinningLotto.createFrom(List.of(1, 2, 3, 4, 5, 6), 7);
        Lotto lotto = Lotto.createFrom(List.of(1, 2, 3, 4, 5, 7));

        // when
        LottoRewardCondition compareResult = winningLotto.createCompareResult(lotto);

        // then
        assertThat(compareResult).isEqualTo(LottoRewardCondition.SECOND_WINNING);
    }
}
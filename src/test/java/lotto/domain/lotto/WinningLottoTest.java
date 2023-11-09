package lotto.domain.lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.util.List;
import lotto.validator.domain.exception.DomainExceptionMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class WinningLottoTest {

    @DisplayName("당첨 로또와 비교하여 당첨 결과를 생성할 수 있다.")
    @Test
    void getCompareResult() {
        // given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        int bonusNumber = 7;
        WinningLotto winningLotto = WinningLotto.createFrom(numbers, bonusNumber);
        Lotto lotto = Lotto.createFrom(numbers);

        // when
        LottoRewardCondition result = winningLotto.findCompareResult(lotto);

        // then
        assertThat(result).isEqualTo(LottoRewardCondition.FIRST_WINNER);
    }

    @DisplayName("보너스 번호가 중복이라면 예외가 발생한다.")
    @Test
    void bonusNumber_exception_duplicatesWithLotto() {
        // given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        int bonusNumber = 6;

        // when & then
        assertThatIllegalArgumentException().isThrownBy(() -> WinningLotto.createFrom(numbers, bonusNumber))
                .withMessageContaining(DomainExceptionMessage.DUPLICATES_BONUS_NUMBER.value());
    }

    @DisplayName("보너스 번호가 범위를 벗어나면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 46})
    void bonusNumber_exception_outOfRange(int bonusNumber) {
        // given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);

        // when & then
        assertThatIllegalArgumentException().isThrownBy(() -> WinningLotto.createFrom(numbers, bonusNumber))
                .withMessageContaining(DomainExceptionMessage.OUT_OF_RANGE_NUMBER.value());
    }
}
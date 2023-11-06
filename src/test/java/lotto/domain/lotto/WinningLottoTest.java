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

    @DisplayName("로또 번호와 중복된 보너스 번호를 등록하면 예외가 발생합니다.")
    @Test
    void createFrom_exception_duplicatesBonusNumber() {
        // given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        int bonusNumber = 1;

        // when & then
        assertThatIllegalArgumentException().isThrownBy(() -> WinningLotto.createFrom(numbers, 1))
                .withMessageContaining(DomainExceptionMessage.DUPLICATES_LOTTO_NUMBERS.message());
    }

    @DisplayName("로또 번호 범위를 벗어나는 보너스 번호를 입력하면 예외가 발생합니다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 46})
    void createFrom_exception_outOfRangeBonusNumber(int bonusNumber) {
        // given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);

        // when & then
        assertThatIllegalArgumentException().isThrownBy(() -> WinningLotto.createFrom(numbers, bonusNumber))
                .withMessageContaining(DomainExceptionMessage.OUT_OF_RANGE_BONUS_NUMBER.message());
    }

    @DisplayName("입력받은 로또에 대한 비교 결과를 찾을 수 있다.")
    @Test
    void findCompareResult() {
        // given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        int bonusNumber = 7;
        WinningLotto winningLotto = WinningLotto.createFrom(numbers, bonusNumber);
        Lotto lotto = Lotto.createFrom(numbers);

        // when
        LottoRewardCondition compareResult = winningLotto.findCompareResult(lotto);

        // then
        assertThat(compareResult).isEqualTo(LottoRewardCondition.FIRST_REWARD);
    }
}
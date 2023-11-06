package lotto.domain.lotto;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import lotto.domain.money.LottoMoney;
import lotto.validator.domain.exception.DomainExceptionMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoMoneyTest {

    @DisplayName("1000~1_000_000금액 사이가 아니고 1000원 단위의 돈이 아니라면 예외가 발생합니다.")
    @ParameterizedTest
    @ValueSource(ints = {999, 1_001_000, 1001, 500_200})
    void createFrom_exception_invalidInputPrice(int invalidValue) {
        // given & when & then
        assertThatIllegalArgumentException().isThrownBy(() -> LottoMoney.createFrom(invalidValue))
                .withMessageContaining(DomainExceptionMessage.INVALID_INPUT_MONEY_EXCEPTION.message());
    }
}
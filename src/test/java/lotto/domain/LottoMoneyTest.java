package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import lotto.domain.money.LottoMoney;
import lotto.validator.domain.exception.DomainExceptionMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoMoneyTest {

    @DisplayName("천원 이상 백만원 이하의 1000원 단위의 금액이 아니라면 예외가 발생합니다.")
    @ParameterizedTest
    @ValueSource(ints = {999, 10001, 1000001})
    void createLottoMoney_exception_invalidMoney(int invalidMoney) {
        // given & when & then
        assertThatIllegalArgumentException().isThrownBy(() -> LottoMoney.creatFrom(invalidMoney))
                .withMessageContaining(DomainExceptionMessage.INVALID_LOTTO_MONEY.message());
    }

}
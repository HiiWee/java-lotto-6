package lotto.domain;

import java.util.List;
import lotto.validator.domain.LottoNumbersValidator;

public class Lotto {
    private final List<Integer> numbers;

    private Lotto(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public static Lotto createFrom(final List<Integer> numbers) {
        LottoNumbersValidator.validate(numbers);
        return new Lotto(numbers);
    }
}

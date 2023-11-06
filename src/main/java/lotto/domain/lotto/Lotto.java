package lotto.domain.lotto;

import java.util.Collections;
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

    public boolean contains(final int bonusNumber) {
        return numbers.contains(bonusNumber);
    }

    public List<Integer> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }
}

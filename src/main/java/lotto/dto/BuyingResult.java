package lotto.dto;

import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.lotto.Lotto;

public record BuyingResult(List<Integer> numbers) {

    private static final String DELIMITER = ", ";
    private static final String PREFIX = "[";
    private static final String SUFFIX = "]";

    public static BuyingResult createFrom(final Lotto lotto) {
        return new BuyingResult(lotto.getNumbers());
    }

    public String createResultMessage() {
        return numbers.stream()
                .sorted()
                .map(String::valueOf)
                .collect(Collectors.joining(DELIMITER, PREFIX, SUFFIX));
    }
}

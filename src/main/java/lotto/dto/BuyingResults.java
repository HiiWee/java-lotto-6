package lotto.dto;

import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.lotto.Lottos;

public record BuyingResults(List<BuyingResult> results) {

    private static final String DELIMITER = "\n";

    public static BuyingResults createFrom(final Lottos buyingLottos) {
        return new BuyingResults(buyingLottos.lottos()
                .stream()
                .map(BuyingResult::createFrom)
                .toList());
    }

    public String createMessage() {
        return results.stream()
                .map(BuyingResult::createResultMessage)
                .collect(Collectors.joining(DELIMITER));
    }

    public int getBuyingCount() {
        return results.size();
    }
}

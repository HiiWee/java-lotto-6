package lotto.dto;

import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.lotto.Lottos;

public record BuyingResults(List<BuyingResult> buyingResults) {

    public static BuyingResults createFrom(final Lottos userLottos) {
        return new BuyingResults(
                userLottos.lottos()
                        .stream()
                        .map(BuyingResult::createFrom)
                        .toList()
        );
    }

    public String createResultMessage() {
        return buyingResults.stream()
                .map(BuyingResult::createSingleResultMessage)
                .collect(Collectors.joining("\n"));
    }

    public int getCount() {
        return buyingResults.size();
    }
}

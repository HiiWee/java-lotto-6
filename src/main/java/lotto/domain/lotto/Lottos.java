package lotto.domain.lotto;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;
import lotto.domain.money.LottoMoney;

public record Lottos(List<Lotto> lottos) {

    public static Lottos createFrom(final Supplier<List<Integer>> lottoSupplier, final LottoMoney money) {
        int buyingCount = money.calculateBuyingCount();
        return new Lottos(Stream.generate(lottoSupplier)
                .limit(buyingCount)
                .map(Lotto::createFrom)
                .toList());
    }
}

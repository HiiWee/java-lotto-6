package lotto.domain.lotto;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

public record Lottos(List<Lotto> lottos) {

    public static Lottos createFrom(final int generateCount, final Supplier<List<Integer>> numbersSupplier) {
        return new Lottos(Stream.generate(numbersSupplier)
                .limit(generateCount)
                .map(Lotto::createFrom)
                .toList());
    }

    @Override
    public List<Lotto> lottos() {
        return Collections.unmodifiableList(lottos);
    }
}

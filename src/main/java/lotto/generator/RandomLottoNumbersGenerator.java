package lotto.generator;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
import java.util.function.Supplier;
import lotto.domain.lotto.LottoCondition;

public class RandomLottoNumbersGenerator {

    private static final Supplier<List<Integer>> supplier = RandomLottoNumbersGenerator::generate;

    private RandomLottoNumbersGenerator() {
    }

    private static List<Integer> generate() {
        return Randoms.pickUniqueNumbersInRange(
                LottoCondition.MIN_RANGE.value(),
                LottoCondition.MAX_RANGE.value(),
                LottoCondition.LENGTH.value()
        );
    }

    public static Supplier<List<Integer>> getSupplier() {
        return supplier;
    }
}

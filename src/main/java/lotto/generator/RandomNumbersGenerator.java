package lotto.generator;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
import java.util.function.Supplier;
import lotto.domain.LottoCondition;

public class RandomNumbersGenerator {

    private static final Supplier<List<Integer>> supplier = RandomNumbersGenerator::generate;

    private RandomNumbersGenerator() {
    }

    private static List<Integer> generate() {
        return Randoms.pickUniqueNumbersInRange(
                LottoCondition.START_NUMBER.value(),
                LottoCondition.END_NUMBER.value(),
                LottoCondition.LOTTO_LENGTH.value()
        );
    }

    public static Supplier<List<Integer>> getGenerateSupplier() {
        return supplier;
    }
}

package lotto.domain;

import java.util.List;
import java.util.function.Supplier;

public class LottoMachine {

    private final LottoRepository lottoRepository;

    public LottoMachine(final LottoRepository lottoRepository) {
        this.lottoRepository = lottoRepository;
    }

    public void buyLottos(final int price, final Supplier<List<Integer>> lottoNumbersSupplier) {
        LottoMoney money = LottoMoney.createFrom(price);
        int count = money.calculateBuyingCount();
        Lottos lottos = Lottos.createFrom(count, lottoNumbersSupplier);
        lottoRepository.saveBuyingLottos(lottos);
    }
}

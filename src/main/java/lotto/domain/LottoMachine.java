package lotto.domain;

import java.util.List;
import java.util.function.Supplier;
import lotto.domain.lotto.LottoRepository;
import lotto.domain.lotto.Lottos;
import lotto.domain.money.LottoMoney;

public class LottoMachine {

    private final LottoRepository lottoRepository;

    public LottoMachine(final LottoRepository lottoRepository) {
        this.lottoRepository = lottoRepository;
    }

    public void buyLottos(final Supplier<List<Integer>> randomLottoSupplier, final int buyingPrice) {
        LottoMoney lottoMoney = LottoMoney.creatFrom(buyingPrice);
        Lottos buyingLottos = Lottos.createFrom(randomLottoSupplier, lottoMoney);
        lottoRepository.saveBuyingLottos(buyingLottos);
    }
}

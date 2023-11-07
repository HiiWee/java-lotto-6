package lotto.domain;

import java.util.List;
import java.util.function.Supplier;
import lotto.domain.lotto.LottoRepository;
import lotto.domain.lotto.Lottos;
import lotto.domain.lotto.WinningLotto;
import lotto.domain.money.LottoMoney;
import lotto.dto.BuyingResults;
import lotto.validator.domain.exception.DomainExceptionMessage;

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

    public BuyingResults createBuyingResults() {
        Lottos buyingLottos = findBuyingLottosObject();
        return BuyingResults.createFrom(buyingLottos);
    }

    private Lottos findBuyingLottosObject() {
        return lottoRepository.findBuyingLottos().orElseThrow(DomainExceptionMessage.NOT_FOUND_LOTTO::create);
    }

    public void addWinningLotto(final List<Integer> winningNumbers, final int bonusNumber) {
        WinningLotto winningLotto = WinningLotto.createFrom(winningNumbers, bonusNumber);
        lottoRepository.saveWinningLotto(winningLotto);
    }
}

package lotto.domain;

import java.util.List;
import java.util.function.Supplier;
import lotto.domain.lotto.LottoRepository;
import lotto.domain.lotto.LottoRewardCondition;
import lotto.domain.lotto.Lottos;
import lotto.domain.lotto.WinningLotto;
import lotto.domain.money.LottoMoney;
import lotto.dto.BuyingResults;
import lotto.dto.WinningResults;
import lotto.validator.domain.exception.DomainExceptionMessage;

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

    public BuyingResults createBuyingResults() {
        Lottos buyingLottos = findBuyingLottosObject();
        return BuyingResults.createFrom(buyingLottos);
    }

    public void addWinningLotto(final List<Integer> winingNumbers, final int bonusNumber) {
        WinningLotto winningLotto = WinningLotto.createFrom(winingNumbers, bonusNumber);
        lottoRepository.saveWinningLotto(winningLotto);
    }

    public WinningResults createWinningResults() {
        List<LottoRewardCondition> rewardResults = compareBuyingLottos();
        return WinningResults.createFrom(rewardResults);
    }

    private List<LottoRewardCondition> compareBuyingLottos() {
        Lottos buyingLottos = findBuyingLottosObject();
        WinningLotto winningLotto = findWinningLottoObjects();
        return buyingLottos.compareWinningLotto(winningLotto);
    }

    private Lottos findBuyingLottosObject() {
        return lottoRepository.findBuyingLottos()
                .orElseThrow(DomainExceptionMessage.NOT_FOUND_LOTTO::create);
    }

    private WinningLotto findWinningLottoObjects() {
        return lottoRepository.findWinningLotto()
                .orElseThrow(DomainExceptionMessage.NOT_FOUND_LOTTO::create);
    }
}

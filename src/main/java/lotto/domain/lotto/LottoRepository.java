package lotto.domain.lotto;

import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;

public class LottoRepository {

    private final Map<LottoType, Object> stores = new EnumMap<>(LottoType.class);

    public void saveBuyingLottos(final Lottos buyingLottos) {
        stores.put(LottoType.BUYING_LOTTOS, buyingLottos);
    }

    public Optional<Lottos> findBuyingLottos() {
        return Optional.ofNullable((Lottos) stores.get(LottoType.BUYING_LOTTOS));
    }

    public void saveWinningLotto(final WinningLotto winningLotto) {
        stores.put(LottoType.WINING_LOTTO, winningLotto);
    }

    public Optional<WinningLotto> findWinningLotto() {
        return Optional.ofNullable((WinningLotto) stores.get(LottoType.WINING_LOTTO));
    }
}

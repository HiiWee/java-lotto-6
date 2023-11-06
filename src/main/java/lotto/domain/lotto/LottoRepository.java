package lotto.domain.lotto;

import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;

public class LottoRepository {

    private final Map<LottoType, Object> stores = new EnumMap<>(LottoType.class);

    public void saveBuyingLottos(final Lottos lottos) {
        stores.put(LottoType.BUYING_LOTTOS, lottos);
    }

    public Optional<Lottos> findBuyingLottos() {
        return Optional.ofNullable((Lottos) stores.get(LottoType.BUYING_LOTTOS));
    }

    public void saveWinningLotto(final WinningLotto winningLotto) {
        stores.put(LottoType.WINNING_LOTTO, winningLotto);
    }
}

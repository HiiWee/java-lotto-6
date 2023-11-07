package lotto.domain.lotto;

import java.util.EnumMap;
import java.util.Map;

public class LottoRepository {

    private final Map<LottoType, Object> stores = new EnumMap<>(LottoType.class);

    public void saveBuyingLottos(final Lottos buyingLottos) {
        stores.put(LottoType.BUYING_LOTTOS, buyingLottos);
    }
}

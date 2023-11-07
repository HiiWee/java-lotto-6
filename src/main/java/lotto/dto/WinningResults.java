package lotto.dto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lotto.domain.lotto.LottoRewardCondition;

public record WinningResults(Map<LottoRewardCondition, Integer> rewardCounts) {

    public static WinningResults createFrom(final List<LottoRewardCondition> winningResults) {
        return new WinningResults(winningResults.stream()
                .collect(Collectors.groupingBy(
                        reward -> reward,
                        Collectors.collectingAndThen(Collectors.counting(), Long::intValue))
                )
        );
    }
}

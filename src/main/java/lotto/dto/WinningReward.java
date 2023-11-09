package lotto.dto;

import java.util.List;
import lotto.domain.result.WinningResults;

public record WinningReward(List<Integer> sortedRewardCounts, double profitRatio) {

    public static WinningReward createFrom(final WinningResults winningResults) {
        return new WinningReward(winningResults.getSortedRewardCounts(), winningResults.getProfitRatio());
    }
}

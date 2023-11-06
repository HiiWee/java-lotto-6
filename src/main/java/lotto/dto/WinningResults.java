package lotto.dto;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lotto.domain.lotto.LottoRewardCondition;
import lotto.domain.money.LottoMoneyCondition;

public record WinningResults(Map<LottoRewardCondition, Integer> rewardCountResults) {

    public static WinningResults createFrom(final List<LottoRewardCondition> rewardResults) {
        return new WinningResults(rewardResults.stream()
                .collect(Collectors.groupingBy(
                        reward -> reward,
                        Collectors.collectingAndThen(Collectors.counting(), Long::intValue))
                )
        );
    }

    public List<Integer> calculateWinningCounts() {
        return Arrays.stream(LottoRewardCondition.values())
                .filter(LottoRewardCondition::isNotFail)
                .map(reward -> rewardCountResults.getOrDefault(reward, 0))
                .toList();
    }

    public double calculateProfitRatio() {
        int resultCount = countResults();
        long totalPrizeMoney = calculateTotalPrize();
        return totalPrizeMoney / (double) (resultCount * LottoMoneyCondition.MONEY_UNIT.value()) * 100;
    }

    private long calculateTotalPrize() {
        return rewardCountResults.entrySet()
                .stream()
                .mapToLong(resultEntry -> resultEntry.getValue() * resultEntry.getKey().getPrizeMoney())
                .sum();
    }

    private int countResults() {
        return rewardCountResults.values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
    }
}

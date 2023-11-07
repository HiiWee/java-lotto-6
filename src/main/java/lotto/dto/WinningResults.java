package lotto.dto;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lotto.domain.lotto.LottoRewardCondition;
import lotto.domain.money.LottoMoneyCondition;

public record WinningResults(Map<LottoRewardCondition, Integer> rewardCounts) {

    public static WinningResults createFrom(final List<LottoRewardCondition> winningResults) {
        return new WinningResults(winningResults.stream()
                .collect(Collectors.groupingBy(
                        reward -> reward,
                        Collectors.collectingAndThen(Collectors.counting(), Long::intValue))
                )
        );
    }

    public List<Integer> createRewardCounts() {
        return Arrays.stream(LottoRewardCondition.values())
                .filter(LottoRewardCondition::isNotFail)
                .map(reward -> rewardCounts.getOrDefault(reward, 0))
                .toList();
    }

    public double calculateProfitRatio() {
        long totalPrizeMoney = calculateTotalPrizeMoney();
        int buyingCount = calculateBuyingCount();
        return totalPrizeMoney / (double) (buyingCount * LottoMoneyCondition.MONEY_UNIT.value()) * 100;
    }

    private long calculateTotalPrizeMoney() {
        return rewardCounts.entrySet()
                .stream()
                .mapToLong(rewardEntry -> rewardEntry.getKey().prizeMoney() * rewardEntry.getValue())
                .sum();
    }

    private int calculateBuyingCount() {
        return rewardCounts.values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
    }
}

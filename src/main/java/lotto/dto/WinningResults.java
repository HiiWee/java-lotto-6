package lotto.dto;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lotto.domain.lotto.LottoRewardCondition;
import lotto.domain.money.LottoMoneyCondition;

public record WinningResults(Map<LottoRewardCondition, Integer> results) {

    public static WinningResults createFrom(final List<LottoRewardCondition> rewards) {
        return new WinningResults(rewards.stream()
                .collect(Collectors.groupingBy(lottoReward -> lottoReward,
                        Collectors.collectingAndThen(Collectors.counting(), Long::intValue))));
    }

    public List<Integer> calculateWinningCounts() {
        return Arrays.stream(LottoRewardCondition.values())
                .filter(LottoRewardCondition::isNotFail)
                .map(condition -> results.getOrDefault(condition, 0))
                .toList();
    }

    public double getProfitRatio() {
        long totalPrizeMoney = calculateTotalPrizeMoney();
        int buyingCount = getBuyingCount();
        return calculateProfitRatio(totalPrizeMoney, buyingCount);
    }

    private int getBuyingCount() {
        return results.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    private long calculateTotalPrizeMoney() {
        return results.entrySet().stream()
                .mapToLong(result -> result.getValue() * result.getKey().getRewardMoney())
                .sum();
    }

    private double calculateProfitRatio(final long totalPrizeMoney, final int buyingCount) {
        return totalPrizeMoney / (double) (buyingCount * LottoMoneyCondition.MONEY_UNIT.value()) * 100;
    }
}

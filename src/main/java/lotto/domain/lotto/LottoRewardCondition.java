package lotto.domain.lotto;

import java.util.Arrays;

public enum LottoRewardCondition {

    FAIL(0, 0),
    FIFTH_WINNING(3, 5_000L),
    FOURTH_WINNING(4, 50_000L),
    THIRD_WINNING(5, 1_500_000L),
    SECOND_WINNING(5, 30_000_000L),
    FIRST_WINNING(6, 2_000_000_000L);

    private final int winningCount;
    private final long prizeMoney;

    LottoRewardCondition(final int winningCount, final long prizeMoney) {
        this.winningCount = winningCount;
        this.prizeMoney = prizeMoney;
    }

    public static LottoRewardCondition findReward(final int winningCount, final boolean hasBonusNumber) {
        if (winningCount == SECOND_WINNING.winningCount && hasBonusNumber) {
            return SECOND_WINNING;
        }
        return findRewardWithoutBonus(winningCount);
    }

    private static LottoRewardCondition findRewardWithoutBonus(final int winningCount) {
        return Arrays.stream(values())
                .filter(reward -> reward.winningCount == winningCount && reward != SECOND_WINNING)
                .findAny()
                .orElse(FAIL);
    }
}

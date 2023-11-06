package lotto.domain.lotto;

import java.util.Arrays;

public enum LottoRewardCondition {

    FAIL(0, 0),
    FIFTH_REWARD(3, 5_000),
    FOURTH_REWARD(4, 50_000),
    THIRD_REWARD(5, 1_500_000),
    SECOND_REWARD(5, 30_000_000),
    FIRST_REWARD(6, 2_000_000_000);

    private final int winningCount;
    private final int prizeMoney;

    LottoRewardCondition(final int winningCount, final int prizeMoney) {
        this.winningCount = winningCount;
        this.prizeMoney = prizeMoney;
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }

    public boolean isNotFail() {
        return this != FAIL;
    }

    public static LottoRewardCondition findReward(final int winningCount, final boolean hasBonusNumber) {
        if (winningCount == SECOND_REWARD.winningCount && hasBonusNumber) {
            return SECOND_REWARD;
        }
        return compareWithoutBonus(winningCount);
    }

    private static LottoRewardCondition compareWithoutBonus(final int winningCount) {
        return Arrays.stream(values())
                .filter(reward -> reward.winningCount == winningCount)
                .findAny()
                .orElse(FAIL);
    }
}

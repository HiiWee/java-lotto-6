package lotto.domain.lotto;

import java.util.Arrays;

public enum LottoRewardCondition {

    FAIL(0, false, 0),
    FIFTH_WINNER(3, false, 5_000L),
    FOURTH_WINNER(4, false, 50_000L),
    THIRD_WINNER(5, false, 1_500_000L),
    SECOND_WINNER(5, true, 30_000_000L),
    FIRST_WINNER(6, false, 2_000_000_000L);

    private final int winningCount;
    private final boolean hasBonusNumber;
    private final long rewardMoney;

    LottoRewardCondition(final int winningCount, final boolean hasBonusNumber, final long rewardMoney) {
        this.winningCount = winningCount;
        this.hasBonusNumber = hasBonusNumber;
        this.rewardMoney = rewardMoney;
    }

    public long getRewardMoney() {
        return rewardMoney;
    }

    public boolean isNotFail() {
        return this != FAIL;
    }

    public static LottoRewardCondition findLottoReward(final int winningCount, final boolean hasBonusNumber) {
        return Arrays.stream(values())
                .filter(reward -> reward.isSameCondition(winningCount, hasBonusNumber))
                .findAny()
                .orElse(FAIL);
    }

    private boolean isSameCondition(final int winningCount, final boolean hasBonusNumber) {
        return this.winningCount == winningCount && this.hasBonusNumber == hasBonusNumber;
    }
}

package lotto.generator;

import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lotto.dto.WinningResults;

public class WinningResultsMessageGenerator {

    private static final List<String> RESULT_STATISTICS_FORMATS = List.of(
            "3개 일치 (5,000원) - %d개\n",
            "4개 일치 (50,000원) - %d개\n",
            "5개 일치 (1,500,000원) - %d개\n",
            "5개 일치, 보너스 볼 일치 (30,000,000원) - %d개\n",
            "6개 일치 (2,000,000,000원) - %d개\n"
    );
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("###,##0.0");
    private static final String PROFIT_RATIO_FORMAT = "총 수익률은 %s%%입니다.\n";
    private static final String SUMMING_FORMAT = "%s%s";

    private WinningResultsMessageGenerator() {
    }

    public static String generate(final WinningResults winningResults) {
        String statisticsMessage = createStatisticsMessage(winningResults);
        String profitRatioMessage = createProfitRatioMessage(winningResults);
        return String.format(SUMMING_FORMAT, statisticsMessage, profitRatioMessage);
    }

    private static String createProfitRatioMessage(final WinningResults winningResults) {
        double profitRatio = winningResults.calculateProfitRatio();
        return String.format(PROFIT_RATIO_FORMAT, DECIMAL_FORMAT.format(profitRatio));
    }

    private static String createStatisticsMessage(final WinningResults winningResults) {
        List<Integer> rewardCounts = winningResults.createRewardCounts();
        return IntStream.range(0, RESULT_STATISTICS_FORMATS.size())
                .mapToObj(i -> String.format(RESULT_STATISTICS_FORMATS.get(i), rewardCounts.get(i)))
                .collect(Collectors.joining());
    }
}

package lotto.generator;

import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lotto.dto.WinningResults;

public class WinningMessageGenerator {

    private final static List<String> WINNING_STATISTICS_FORMAT = List.of(
            "3개 일치 (5,000원) - %d개\n",
            "4개 일치 (50,000원) - %d개\n",
            "5개 일치 (1,500,000원) - %d개\n",
            "5개 일치, 보너스 볼 일치 (30,000,000원) - %d개\n",
            "6개 일치 (2,000,000,000원) - %d개\n");
    private final static String PROFIT_RATIO_FORMAT = "총 수익률은 %s%%입니다.\n";
    private final static DecimalFormat DECIMAL_FORMAT = new DecimalFormat("###,##0.0");
    private final static String SUMMING_FORMAT = "%s%s";

    private WinningMessageGenerator() {
    }

    public static String generate(final WinningResults winningResults) {
        String winningStatistics = createWinningStatistics(winningResults);
        String profitRatio = createProfitRatio(winningResults);
        return String.format(SUMMING_FORMAT, winningStatistics, profitRatio);
    }

    private static String createWinningStatistics(final WinningResults winningResults) {
        List<Integer> winningCounts = winningResults.calculateWinningCounts();
        return IntStream.range(0, WINNING_STATISTICS_FORMAT.size())
                .mapToObj(i -> String.format(WINNING_STATISTICS_FORMAT.get(i), winningCounts.get(i)))
                .collect(Collectors.joining());
    }

    private static String createProfitRatio(final WinningResults winningResults) {
        double profitRatio = winningResults.calculateProfitRatio();
        return String.format(PROFIT_RATIO_FORMAT, DECIMAL_FORMAT.format(profitRatio));
    }
}

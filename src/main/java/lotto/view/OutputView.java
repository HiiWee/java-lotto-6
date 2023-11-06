package lotto.view;

public class OutputView {

    private static final String BUYING_COUNT_FORMAT = "%d개를 구매했습니다.";
    private static final String WINNING_STATISTICS_START_MESSAGE = """
            당첨 통계
            ---""";

    public void printBuyingLottos(final int buyingCount, final String resultMessage) {
        System.out.println(String.format(BUYING_COUNT_FORMAT, buyingCount));
        System.out.println(resultMessage);
    }

    public void printWinningResults(final String winningResultsMessage) {
        System.out.println(WINNING_STATISTICS_START_MESSAGE);
        System.out.println(winningResultsMessage);
    }
}

package lotto.view;

public class OutputView {

    private static final String BUYING_COUNT_MESSAGE = "%d개를 구매했습니다.";
    private static final String RESULT_STATISTICS_MESSAGE = """
            당첨 통계
            ---""";

    public void printBuyingResults(final int buyingCount, final String resultsMessage) {
        System.out.println(String.format(BUYING_COUNT_MESSAGE, buyingCount));
        System.out.println(resultsMessage);
    }

    public void printWinningResults(final String resultsMessage) {
        System.out.println(RESULT_STATISTICS_MESSAGE);
        System.out.println(resultsMessage);
    }
}

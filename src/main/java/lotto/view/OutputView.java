package lotto.view;

public class OutputView {

    private static final String BUYING_COUNT_MESSAGE = "%d개를 구매했습니다.";

    public void printBuyingResults(final int buyingCount, final String resultsMessage) {
        System.out.println(String.format(BUYING_COUNT_MESSAGE, buyingCount));
        System.out.println(resultsMessage);
    }
}

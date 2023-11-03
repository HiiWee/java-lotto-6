package lotto.view;

public class OutputView {

    public static final String BUYING_COUNT_FORMAT = "%d개를 구매했습니다.";


    public void printBuyingResults(final int buyingCount, final String resultMessage) {
        System.out.printf(BUYING_COUNT_FORMAT, buyingCount);
        System.out.println();
        System.out.println(resultMessage);
    }
}
package lotto.view;

public class OutputView {

    public static final String BUYING_COUNT_FORMAT = "%d개를 구매했습니다.";

    public void printBuyingLottos(final int buyingCount, final String resultMessage) {
        System.out.println(String.format(BUYING_COUNT_FORMAT, buyingCount));
        System.out.println(resultMessage);
    }
}

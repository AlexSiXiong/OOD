import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HandCards {
    private ArrayList<Integer> cardsList = new ArrayList<>();
    private int cardsMaxSum=0;

    protected int getCardMaxSum() {
        return cardsMaxSum;
    }
    public void showCardList() {
        System.out.println(Arrays.toString(cardsList.toArray())+ " Best value:" + cardsMaxSum);
    }

    private void setCardSum() {
        cardsMaxSum = getBestValue();
    }

    private List<Integer> getPossibleSum() {
        List<Integer> results = new ArrayList<>();

        int aceCount = 0;
        int resultWithoutAce = 0;
        for (int card : cardsList) {
            if (card == 1) {
                aceCount++;
            } else if (card == 11 || card == 12 || card == 13) {
                resultWithoutAce += 10;
            } else
                resultWithoutAce += card;
        }

        for (int i = 0; i <= aceCount; i++) {
            int ones = i;
            int elevens = aceCount - i;

            results.add(resultWithoutAce + ones + elevens * 11);
        }

        return results;
    }

    public int getBestValue() {
        List<Integer> results = getPossibleSum();

        int maxUnder = -1;
        for (int result : results) {
            if (result <= 21 && result > maxUnder) {
                maxUnder = result;
            }
        }
        return maxUnder;
    }

    protected void addCard(int num) {
        cardsList.add(num);
        setCardSum();
    }

}

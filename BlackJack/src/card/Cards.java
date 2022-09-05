package card;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Cards {
    List<Card> cards = new ArrayList<>();

    public Cards() {
        initialiseCards();
        washCards();
    }

    public void initialiseCards(){
        for (String s : new String[]{"♣","♠","♥","◆"}){
            for (int i = 1; i <= 13 ; i++) {
                cards.add(new Card(s,i));
            }
        }
    }

    public void washCards() {
        Random random = new Random();
        for (int i = 0; i < cards.size(); i++) {
            int toExchange = random.nextInt(cards.size());
            Card tempCard = cards.get(i);
            cards.set(i,cards.get(toExchange));
            cards.set(toExchange,tempCard);
        }
    }

//    public Card dealOneCard() {
//        return cards.remove(0);
//    }
    public int dealOneCard() {
        Card card = cards.remove(0);
        return card.rank;
    }
}

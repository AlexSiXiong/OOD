import card.Cards;

import java.util.Arrays;

abstract public class Person {

    Person(String name) {
        this.name = name;
        money = initMoney();
    }

    Person() {
        this.name = "Dealer";
        money = initMoney();
    }
    private final String name;

    private int money;
    private boolean playingStatus; // continue to choose strategy or not
    private boolean endGame = false;
    private boolean dealCardsStatus = true;
    private HandCards cardsInHand = new HandCards();


    public void updateMoney(int addOnMoney) {
        money += addOnMoney;
    }

    private boolean checkEndGame() {
        return money == 0;
    }

    public String getName() {
        return name;
    }

    public int getMoney() {
        return money;
    }

    private int initMoney() {
        if (name.equals("Dealer")) {
            return 100000000;
        } else {
            return 1000;
        }
    }
    public boolean getEndGameStatus() {
        return endGame;
    }

    public void setEndGameStatus(boolean status) {
        endGame = status;
    }

    public boolean getDealCardsStatus() {
        return dealCardsStatus;
    }

    public void setDealCardsStatus(boolean status) {
        dealCardsStatus = status;
    }


    public HandCards getCardsInHand() {
        return cardsInHand;
    }

    public int getBestValue() {
        return getCardsInHand().getBestValue();
    }
    public void receiveOneCard(Cards cards) {
        cardsInHand.addCard(cards.dealOneCard());
        checkBust();
    }
    abstract public void chooseStrategy();

    private void checkBust() {
        int bestValue = getCardsInHand().getBestValue();

        if (bestValue == -1) {
            setEndGameStatus(true);
            setDealCardsStatus(false);
        }
    }




}

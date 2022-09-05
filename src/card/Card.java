package card;


public class Card {
    String suit;  //花色
    int rank;     //大小

    public Card(String suit, int rank) {
        this.suit = suit;
        this.rank = rank;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null){
            return false;
        }
        if (!(obj instanceof Card card)){
            return false;
        }
        return this.suit.equals(card.suit) && this.rank == card.rank;
    }

    @Override
    public String toString() {
        return String.format("[%s %d]",suit,rank);  //注意一下这种输出方式
    }
}
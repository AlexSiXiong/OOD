public class Player extends Person{
    private int bet;
    private boolean isWin;

    Player(String name) {
        super(name);
    }

    public int getBet() {
        return bet;
    }

    public void setBet(int bet) {
        this.bet = bet;
        updateMoney(-bet);
    }

    public boolean getIsWin() {
        return isWin;
    }

    public void setIsWin(boolean status) {
        isWin = status;
    }


    @Override
    public void chooseStrategy() {

    }

}

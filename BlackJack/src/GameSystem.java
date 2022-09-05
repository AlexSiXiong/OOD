import card.Cards;

import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;

public class GameSystem {
    private SystemStatus sysStatus = SystemStatus.WAITING;

    private Dealer dealer = new Dealer();
    private List<Player> players = new ArrayList<>();
    public Cards cards = new Cards();

    private int totalPlayers; // if no player(at least one), game cannot start
    public Scanner scan = new Scanner(System.in);
    public void initGamingRoom() {
        System.out.println("init dealer - done.");

        waitingPlayers();
        System.out.println("waiting players - done");
        waitingBetting();
        showMoney();
        initDeal();
        System.out.println("two rounds deals - done");


        System.out.println("------------------------------------");
        deal();
        System.out.println("------------------------------------");
        duel();
        System.out.println("------------------------------------");
        moneyCounting();

        showMoney();


    }

    public void waitingPlayers() {
        while (sysStatus == SystemStatus.WAITING) {
            System.out.println("-----There are " + players.size() + " players on the table.----------");
            joinPlayer(scan);
        }

    }

    public void joinPlayer(Scanner scan) {
        // add a player in the table.
        // when num(players) >= 1, username == "" or username contains "start"
        // start the game, change game status
        if (players.size() >= 1) {
            System.out.println("-----Please enter player name or press enter to start game.");
        } else {
            System.out.println("-----Waiting players to join, please enter player name:");
        }

        if (scan.hasNextLine()) {
            String playerName = scan.nextLine();
            playerName = playerName.replace(" ","");
            if ((playerName.equals("start")|| playerName.equals("")) && players.size() >= 1) {
                System.out.println("-----No more players. Game start.");
                changeStatus(SystemStatus.RUNNING);
            }
            else if (!playerName.equals("")) {
                System.out.println("-----player "+ playerName +" joined.");
                players.add(new Player(playerName));
            }
        }
    }

    public void removePlayer() {
        // if a player has no money
        // if a player wants to quit
    }

    public void waitingBetting() {
        for (Player p: players) {
            System.out.println("-----Player: "+p.getName()+" is betting." );
            int bet = playerBetting();
            p.setBet(bet);
        }
    }

    public void initDeal() {
        // give two cards to each person
        int times = 0;
        do {
            dealer.receiveOneCard(cards);

            for (Person player : players) {
                player.receiveOneCard(cards);
            }

            times = times + 1;
        } while (times < 2);
    }

    public void deal() {
        // take cards from deck

        // deal a card to a person
        // deal players
        // deal dealer
        for (Player player: players) {

           if (player.getEndGameStatus() || !player.getDealCardsStatus()) {
               continue;
           }
           System.out.println("------------------------------------");
           while (player.getDealCardsStatus()) {
               String strategy = playerChooseStrategy();

               if (strategy.equals("hit")) {
                   player.receiveOneCard(cards); // will update the game status and card status
               }

               if (strategy.equals("stand")) {
                   player.setEndGameStatus(false);
                   player.setDealCardsStatus(false);
               }

               System.out.print(player.getName()+" ");
               player.getCardsInHand().showCardList();
           }
        }

        while (0 <= dealer.getBestValue() && dealer.getBestValue() < 17) {
            dealer.receiveOneCard(cards);
        }

        System.out.print(dealer.getName()+" ");
        dealer.getCardsInHand().showCardList();

        changeStatus(SystemStatus.CHECKING);
    }

    public String playerChooseStrategy() {
        String res = "";
        do {
            System.out.println("Choose 'hit' or 'stand'.");
            if (scan.hasNextLine()) {
                res = scan.nextLine();
            }
        } while (!(res.equals("hit") || res.equals("stand")));
        return res;
    }

    public int playerBetting() {
        int res = 0;

        do {
            System.out.println("input a number > 0");
            if (scan.hasNextInt()) {
                res = scan.nextInt();
            }
        } while (res <= 0);

        return res;
    }

    public void duel() {
        // compare the sum of cards of the dealer and a player - see who wins
        for (Player p: players) {
            if (p.getEndGameStatus()) {
                System.out.println(p.getName() + " lose");
                p.setIsWin(false);
            } else if (largerThan(p)) {
                System.out.println(p.getName() + " win");
                p.setIsWin(true);
            }
        }
    }

    private void moneyCounting() {
        for (Player p: players) {
            if (p.getIsWin()) {
                p.updateMoney(p.getBet() * 2);
                dealer.updateMoney(-p.getBet());
            } else {
                dealer.updateMoney(p.getBet());
            }
        }
    }

    public void showMoney() {
        System.out.println("---------------------");
        System.out.println("Dealer Money: "+dealer.getMoney());
        for (Player p: players) {
            System.out.println("Player "+p.getName()+" has "+p.getMoney());
        }

    }

    private boolean largerThan(Player player) {
        return player.getBestValue() >= dealer.getBestValue();
    }

    private void changeStatus(SystemStatus status) {
        sysStatus = status;
    }




}

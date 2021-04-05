import java.util.ArrayDeque;
import java.util.ArrayList;

public class Player {

    private ArrayDeque<Card> deck;
    private ArrayDeque<Card> hand = new ArrayDeque<>();
    private int lifePoints = 8000;
    private int normalSummon = 1;
    private boolean turnOver = false;

    public Player(ArrayDeque<Card> deck){
        this.deck = deck;
        for (int i = 0; i < 5 ; i++) {
            hand.add(deck.pollFirst());
        }
    }

    public Card draw(){
        hand.addFirst(deck.pollFirst());
        return hand.getFirst();
    }

    public Card search(Card c){
        Card ca = null;
        if(deck.contains(c)){
            for (Card d: deck) {
                if (d.equals(c)){
                    ca = d;
                    deck.remove(d);
                    break;
                }
            }
        }
        return ca;
    }

    public Card play(Card c){
        Card d = null;
        for (Card ca: hand) {
            if(ca.equals(c)){
                d = ca;
                hand.remove(ca);
                break;
            }
        }
        return d;
    }

    public void startTurn(){
        this.turnOver = false;
    }

    public void endTurn(){
        this.turnOver = true;
    }

    public boolean lose(){
        return lifePoints<=0 || (deck.isEmpty() && turnOver);
    }



}

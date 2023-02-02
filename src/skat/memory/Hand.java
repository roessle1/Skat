package skat.memory;

import skat.logic.CardSort;

import java.util.ArrayList;
import java.util.Arrays;

public class Hand implements IHand {

    private ArrayList<Card> hand;
    private final CardSort cardSort;

    public Hand() {
        hand = new ArrayList<>();
        cardSort = new CardSort();
    }

    @Override
    public void addCards(Card[] cards) {
        hand.addAll(Arrays.asList(cards));
        sortHand();
    }

    @Override
    public String[] getGraphicsUrl() {
        String[] url = new String[hand.size()];
        for(byte i = 0; i < hand.size(); i++) {
            url[i] = hand.get(i).getUrl();
        }
        return url;
    }

    @Override
    public void emptyHand() {
        if(!hand.isEmpty())
            hand.clear();
    }

    @Override
    public Card removeCard(String cardUrl) {
        Card card = getCard(cardUrl);
        if(card != null) {
            hand.remove(card);
        }
        return card;
    }

    @Override
    public Card getCard(String cardUrl) {
        for(Card card : hand) {
            if(card.getUrl().equals(cardUrl)) {
                return card;
            }
        }
        return null;
    }

    @Override
    public void sortHand(byte game) {
        hand = cardSort.sort(hand, game);
    }

    private void sortHand() {
        sortHand((byte) 24);
    }
}
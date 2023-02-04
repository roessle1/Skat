package skat.memory;

public class PlayedCards implements IPlayedCards {

    private Card[] playedCards;

    public PlayedCards() {
        playedCards = new Card[3];
    }

    @Override
    public Card[] getPlayedCards() {
        return playedCards;
    }

    @Override
    public void setPlayedCards(Card[] playedCards) {
        this.playedCards = playedCards;
    }

    @Override
    public Card getFirstPlayedCard() {
        return playedCards[0];
    }

    @Override
    public void addPlayedCard(Card card) {
        byte i = 0;
        while(playedCards[i] != null) {
            i++;
        }
        playedCards[i] = card;
    }

    @Override
    public boolean isComplete() {
        return playedCards[2] != null;
    }

    @Override
    public boolean isEmpty() {
        return playedCards[0] == null;
    }

    @Override
    public void removePlayedCards() {
        for(byte i = 0; i < playedCards.length; i++) {
            playedCards[i] = null;
        }
    }

    @Override
    public String[] getPlayedCardsUrl() {
        String[] playedCardsUrl = new String[3];
        for(byte i = 0; i < playedCards.length; i++) {
            if(playedCards[i] == null) {
                break;
            }
            playedCardsUrl[i] = playedCards[i].getUrl();
        }
        return playedCardsUrl;
    }
}

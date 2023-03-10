package client.logic.network;

import skat.cards.Card;
import skat.log.Log;
import client.logic.LogicEvents;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.logging.Level;

public class ClientOutgoing {

    private ObjectOutputStream out;

    public ClientOutgoing(OutputStream out) {
        try {
            this.out = new ObjectOutputStream(new BufferedOutputStream(out));
            this.out.flush();
        } catch(IOException e) {
            Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
            LogicEvents.getInstance().setErrorOccurred();
        }
    }

    public void sendOpenGameCards() {
        try {
            out.writeByte(6);
            out.flush();
        } catch(IOException e) {
            Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public void sendBidAnswer(boolean answer) {
        try {
            out.writeByte(5);
            out.writeBoolean(answer);
            out.flush();
        } catch(IOException e) {
            Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public void sendPriceStage(byte priceStage) {
        try {
            out.writeByte(4);
            out.writeByte(priceStage);
            out.flush();
        } catch(IOException e) {
            Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public void sendGameId(byte gameId) {
        try {
            out.writeByte(3);
            out.writeByte(gameId);
            out.flush();
        } catch(IOException e) {
            Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public void sendSkat(Card[] skat) {
        try {
            out.writeByte(2);
            out.writeObject(skat);
            out.flush();
        } catch(IOException e) {
            Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public void playCard(Card card) {
        try {
            out.writeByte(1);
            out.writeObject(card);
            out.flush();
        } catch(IOException e) {
            Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public void closeOutput() {
        try {
            out.writeByte(7);
            out.flush();
        } catch(IOException e) {
            Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public void playNextRound() {
        try {
            out.writeByte(8);
            out.flush();
        } catch(IOException e) {
            Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
        }
    }
}

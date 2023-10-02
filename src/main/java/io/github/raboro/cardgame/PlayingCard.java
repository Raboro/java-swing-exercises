package io.github.raboro.cardgame;

public class PlayingCard implements Comparable<PlayingCard> {

    private final Suit suit;
    private final CardValue cardValue;

    PlayingCard(Suit suit, CardValue cardValue) {
        this.suit = suit;
        this.cardValue = cardValue;
    }

    @Override
    public String toString() {
        return suit.toString() + cardValue.toString();
    }

    @Override
    public int compareTo(PlayingCard o) {
        return this.cardValue.ordinal() - o.cardValue.ordinal();
    }
}

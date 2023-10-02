package io.github.raboro.cardgame;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class CardGame {

    private final Stack<PlayingCard> cards;

    CardGame() {
        cards = new Stack<>();
        Arrays.stream(Suit.values()).
                forEach(suit -> Arrays.stream(CardValue.values()).forEach(value -> cards.add(new PlayingCard(suit, value))));
    }

    void shuffle() {
        Collections.shuffle(cards);
    }

    void sort() {
        Collections.sort(cards);
    }

    PlayingCard get() {
        return cards.pop();
    }

    List<PlayingCard> all() {
        return cards.stream().toList();
    }
}

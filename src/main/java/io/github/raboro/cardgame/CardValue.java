package io.github.raboro.cardgame;

public enum CardValue {

    SEVEN(7),
    EIGHT(8),
    NINE(9),
    JACK(10),
    QUEEN(10),
    KING(10),
    TEN(10),
    ASS(11);

    private final int value;

    CardValue(int value) {
        this.value = value;
    }
}

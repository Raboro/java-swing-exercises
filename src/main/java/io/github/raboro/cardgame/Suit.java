package io.github.raboro.cardgame;

import java.awt.*;

public enum Suit {
    KARO(Color.blue),
    HERZ(Color.red),
    PIK(Color.magenta),
    KREUZ(Color.GRAY);

    private final Color color;

    Suit(Color color) {
        this.color = color;
    }


}

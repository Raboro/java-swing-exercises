package io.github.raboro.gasstation;

public record GasPrices(double diesel, double superE5, double superE10) {

    @Override
    public String toString() {
        return String.format("Diesel: %f, SuperE5: %f, SuperE10: %f", diesel, superE5, superE10);
    }
}



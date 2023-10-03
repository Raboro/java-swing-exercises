package io.github.raboro.zoo;

public class ZooAnimal {
    private final String name;
    private final String category;
    private final String favFood;

    ZooAnimal(String name, String category, String favFood) {
        this.name = name;
        this.category = category;
        this.favFood = favFood;
    }

    String feed(String food) {
        return this + (favFood.equals(food) ? " eats ": " despises ") + food;
    }

    @Override
    public String toString() {
        return  name + "(" + category + ")";
    }

    public boolean sameName(String name) {
        return this.name.equals(name);
    }
}

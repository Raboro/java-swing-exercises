package io.github.raboro.zoo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Zoo {

    private final int max;
    private final List<ZooAnimal> animals = new ArrayList<>();

    Zoo(int max) {
        this.max = max;
    }

    void addAnimal(ZooAnimal animal) {
        if (animals.size() >= max) {
            throw new ZooCapacityException();
        }
        animals.add(animal);
        System.out.println(animal + "added to the Zoo");
    }

    ZooAnimal[] getAnimals() {
        return animals.toArray(ZooAnimal[]::new);
    }

    boolean existsAnimal(String name) {
        return animals.stream().anyMatch(animal -> animal.sameName(name));
    }

    void feed(String fodder) {
        animals.forEach(animal -> animal.feed(fodder));
    }

    void saveToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            animals.forEach(animal -> {
                try {
                    writer.write(animal.toString());
                    writer.newLine();
                } catch (IOException e) {
                    throw new ZooFileException();
                }
            });
        } catch (IOException e) {
            throw new ZooFileException();
        }
    }

    public static void main(String[] args) {
        Zoo z = new Zoo(10);
        z.addAnimal(new Predator("Tiger", "Fred"));
        z.addAnimal(new Predator ("Tiger", "Lisa"));
        z.addAnimal(new Predator ("Lion", "Simba"));
        z.addAnimal(new Songbird("Nuthatch", "Hansi"));
        z.addAnimal(new Songbird ("Backbird", "Sina"));
        z.addAnimal(new Songbird ("Wren", "Henry"));
        z.saveToFile("ZooAnimals.txt");
        z.feed("grains");

    }
}

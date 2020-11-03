package io.iskaldvind.animals;

import io.iskaldvind.tools.Plate;

public abstract class Animal {

    public static int count = 0;
    protected final int runLimit;
    protected final int swimLimit;
    protected final String name;
    protected final int appetite;
    protected boolean satiety;

    public Animal(String name, int runLimit, int swimLimit, int appetite) {
        this.name = name;
        this.runLimit = runLimit;
        this.swimLimit = swimLimit;
        this.appetite = appetite;
        count++;
    }

    protected void eat(Plate plate, int amount) {
        if (satiety) {
            System.out.println(name + " is not hungry and eats nothing");
        } else if (plate.takeFood(amount)) {
            satiety = true;
            System.out.println(name + " eats " + amount + " food from the plate and now is happy");
        } else {
            System.out.println(name + " doesn't found " + amount + " food on the plate and now is sad");
        }
    }

    public void logSatiety() {
        String hungerState = satiety ? " is feed and happy" : " is still hungry and sad";
        System.out.println(name + hungerState);
    }

    protected abstract void run(int distance);
    protected abstract void swim(int distance);
}
